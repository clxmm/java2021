package org.clxmm.shiro.core;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.PostConstruct;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-19 9:04 下午
 */
public  abstract class ShiroDbRealm extends AuthorizingRealm {

    /**
     * @Description 认证方法
     * @param token token对象
     * @return 认证信息
     */
    @Override
    protected abstract AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;

    /**
     * @Description 授权方法
     * @param principals 令牌对象
     * @return 授权信息
     */
    @Override
    protected abstract AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals);


    /**
     * @Description 自定义密码比较器
     * @param
     * @return
     */
    @PostConstruct
    public abstract void initCredentialsMatcher();
}
