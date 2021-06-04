package org.clxmm.shiro.core.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import org.apache.shiro.util.ByteSource;
import org.clxmm.service.shiro.entity.User;
import org.clxmm.service.shiro.service.ResourceService;
import org.clxmm.service.shiro.service.UserService;
import org.clxmm.shiro.constant.CacheConstant;
import org.clxmm.shiro.constant.SuperConstant;
import org.clxmm.shiro.core.ShiroDbRealm;
import org.clxmm.shiro.core.SimpleCacheService;
import org.clxmm.shiro.core.base.ShiroUser;
import org.clxmm.shiro.core.base.SimpleToken;
import org.clxmm.shiro.utils.BeanConv;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-19 9:06 下午
 */
public class ShiroDbRealmImpl extends ShiroDbRealm {

    @Autowired
    SimpleCacheService simpleCacheService;


    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;


    @Resource(name = "redissonClientForShiro")
    RedissonClient redissonClient;

    /**
     * 认真方法
     *
     * @param token token对象
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //token令牌信息
        SimpleToken simpleToken = (SimpleToken) token;
        //查询user对象
        User user = userService.findUserByLoginName(simpleToken.getUsername());
        ShiroUser shiroUser = BeanConv.toBean(user, ShiroUser.class);

//        shiroUser.setResourceIds();
        //构建认证令牌对象
        String salt = shiroUser.getSalt();
        String passWord = shiroUser.getPassWord();
        //构建认证信息对象:1、令牌对象 2、密文密码  3、加密因子 4、当前realm的名称
        return new SimpleAuthenticationInfo(shiroUser, passWord, ByteSource.Util.bytes(salt), getName());
    }

    /**
     * 鉴权
     *
     * @param principals 令牌对象
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        AuthorizationInfo authorizationInfo = userService.getAuthorizationInfo(shiroUser);
        return authorizationInfo;
    }

    @Override
    public void initCredentialsMatcher() {
        // RetryLimitCredentialsMatcher 使用自定义的密码比较器
        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitCredentialsMatcher(SuperConstant.HASH_ALGORITHM,redissonClient);
        //指定密码算法
        hashedCredentialsMatcher.setHashIterations(SuperConstant.HASH_INTERATIONS);
        //生效密码比较器
        setCredentialsMatcher(hashedCredentialsMatcher);

    }


    @Override
    protected void doClearCache(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();

        String loginNamekey = CacheConstant.FIND_USER_BY_LOGINNAME + shiroUser.getLoginName();

        simpleCacheService.removeCache(loginNamekey);
        super.doClearCache(principals);

    }
}
