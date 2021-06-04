package org.clxmm.service.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.clxmm.service.shiro.entity.*;
import org.clxmm.service.shiro.mapper.UserMapper;
import org.clxmm.service.shiro.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clxmm.shiro.constant.CacheConstant;
import org.clxmm.shiro.constant.ShiroConstant;
import org.clxmm.shiro.constant.SuperConstant;
import org.clxmm.shiro.core.SimpleCacheService;
import org.clxmm.shiro.core.base.BaseResponse;
import org.clxmm.shiro.core.base.ShiroUser;
import org.clxmm.shiro.core.base.SimpleMapCache;
import org.clxmm.shiro.core.base.SimpleToken;
import org.clxmm.shiro.core.impl.JwtTokenManager;
import org.clxmm.shiro.utils.*;
import org.clxmm.shiro.vo.LoginVo;
import org.clxmm.shiro.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserRoleService userRoleService;


    @Autowired
    RoleResourceService roleResourceService;

    @Autowired
    RoleService roleService;


    @Autowired
    ResourceService resourceService;


    @Autowired
    SimpleCacheService simpleCacheService;


    @Autowired
    JwtTokenManager jwtTokenManager;

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByLoginName(String username) {

        String key = CacheConstant.FIND_USER_BY_LOGINNAME + username;

        //获取缓存
        Cache<Object, Object> cache = simpleCacheService.getCache(key);

        // 如果缓存存在的情况
        if (!EmptyUtil.isNullOrEmpty(cache)) {
            return (User) cache.get(key);
        }

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(User::getLoginName, username)
                .eq(User::getEnableFlag, "1");
        List<User> users = baseMapper.selectList(queryWrapper);

        if (CollectionUtils.isEmpty(users)) {
            return null;
        }


        User user = users.get(0);

        // 缓存不存在 切用户存在，把用户放到缓存中
        Map<Object, Object> map = new HashMap<>();
        map.put(key, user);
        SimpleMapCache simpleMapCache = new SimpleMapCache(key, map);
        simpleCacheService.createCache(key, simpleMapCache);

        return user;

    }

    /**
     * 鉴权方法
     *
     * @param shiroUser
     * @return
     */
    @Override
    public AuthorizationInfo getAuthorizationInfo(ShiroUser shiroUser) {
        // 查询用户对应的角色
        List<UserRole> roles = userRoleService.getRolesByUserId(shiroUser.getId());
        // 角色ids
        List<String> rolesIds = roles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 查询用户对应的资源

        List<Role> roleList = roleService.getRolesList(rolesIds);
        List<String> roleNames = roleList.stream().map(Role::getRoleName).collect(Collectors.toList());

        List<RoleResource> roleResourceList = roleResourceService.getResourceIdsByRolesIds(rolesIds);
        // 资源ids
        List<String> resourceIds = roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList());

        List<Resource> resourceList = resourceService.getListByIds(resourceIds);
        List<String> resourceLabels = resourceList.stream().map(Resource::getLabel).collect(Collectors.toList());
        // 构建鉴权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleNames);
        simpleAuthorizationInfo.addStringPermissions(resourceLabels);

        return simpleAuthorizationInfo;
    }

    /**
     * 登陆
     *
     * @param loginVo
     */
    @Override
    public Map<String, String> login(LoginVo loginVo) {

        Map<String, String> map = new HashMap<>();

        try {
            SimpleToken token = new SimpleToken(null, loginVo.getLoginName(), loginVo.getPassWord());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (UnknownAccountException ex) {
            log.error("登陆异常:{}", ex);
            throw new UnknownAccountException(ex);
        } catch (IncorrectCredentialsException exception) {
            log.error("登陆异常:{}", exception);
            throw new IncorrectCredentialsException(exception);

        }

        map.put("authorizationKey", ShiroUtil.getShiroSessionId());
        return map;
    }

    @Override
    public boolean saveNewPassword(String oldPassword, String newPassword) {

        String userId = ShiroUserUtil.getShiroUserId();
        User user = baseMapper.selectById(userId);

        // 对user中的salt进行散列
        oldPassword = DigestsUtil.sha1(oldPassword, user.getSalt());

        if (!user.getPassWord().equals(oldPassword)) {
            return false;
        }

        UserVo userVo = new UserVo();

        userVo.setPlainPassword(newPassword);
        entryptPassword(userVo);


        try {

            user.setPassWord(userVo.getPassWord());
            user.setSalt(userVo.getSalt());

            baseMapper.updateById(user);

        } catch (Exception e) {
            log.error("更新用户密码失败！{}", ExceptionsUtil.getStackTraceAsString(e));
            return false;
        }


        return true;
    }


    @Override
    public void entryptPassword(UserVo userVo) {
        Map<String, String> map = DigestsUtil.entryptPassword(userVo.getPlainPassword());
        userVo.setSalt(map.get("salt"));
        userVo.setPassWord(map.get("password"));
    }

    @Override
    public boolean saveOrUpdateUser(UserVo userVo) {


        try {

            if (!EmptyUtil.isNullOrEmpty(userVo.getPlainPassword())) {
                entryptPassword(userVo);
            }

            User user = BeanConv.toBean(userVo, User.class);
            if (StringUtils.isNotBlank(userVo.getId())) {
                baseMapper.updateById(user);

            } else {
                user.setEnableFlag(SuperConstant.YES);
                baseMapper.insert(user);
            }

        } catch (Exception e) {
            log.error("保存用户出错{}", ExceptionsUtil.getStackTraceAsString(e));
            return false;
        }

        return true;
    }

    @Override
    public BaseResponse routeForJwt(LoginVo loginVo) {
        Map<String, String> map = new HashMap<>();
        String jwtToken = null;


        try {
            SimpleToken token = new SimpleToken(null, loginVo.getLoginName(), loginVo.getPassWord());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String shiroSessionId = ShiroUserUtil.getShiroSessionId();
            //登录后颁发的令牌
            ShiroUser shiroUser = ShiroUserUtil.getShiroUser();
            Map<String, Object> claims = new HashMap<>();
            claims.put("shiroUser", JSONObject.toJSONString(shiroUser));
            jwtToken = jwtTokenManager.IssuedToken("system", subject.getSession().getTimeout(),shiroSessionId,claims);
            map.put("jwtToken",jwtToken );
            log.info("jwtToken:{}",map.toString());
            //创建缓存
//            this.loadAuthorityToCache();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            BaseResponse baseResponse = new BaseResponse(ShiroConstant.LOGIN_FAILURE_CODE, ShiroConstant.LOGIN_FAILURE_MESSAGE);
            return baseResponse;
        }

        BaseResponse baseResponse = new BaseResponse(ShiroConstant.LOGIN_SUCCESS_CODE,ShiroConstant.LOGIN_SUCCESS_MESSAGE,jwtToken);
        return baseResponse;



    }

}
