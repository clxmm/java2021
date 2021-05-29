package org.clxmm.service.shiro.service;

import org.apache.shiro.authz.AuthorizationInfo;
import org.clxmm.service.shiro.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.clxmm.shiro.core.base.ShiroUser;
import org.clxmm.shiro.vo.LoginVo;
import org.clxmm.shiro.vo.UserVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author clxmm
 * @since 2021-05-20
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User findUserByLoginName(String username);

    /**
     * 鉴权方法
     * @param shiroUser
     * @return
     */
    AuthorizationInfo getAuthorizationInfo(ShiroUser shiroUser);


    /**
     * 登陆
     * @param loginVo
     */
    Map<String,String> login(LoginVo loginVo);

    boolean saveNewPassword(String oldPassword, String newPassword);


    /**
     * @Description 密码加密
     * @param
     * @return
     */
    public void entryptPassword(UserVo userVo);

    boolean saveOrUpdateUser(UserVo userVo);
}
