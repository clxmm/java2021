package org.clxmm.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.clxmm.shiro.service.SecurityService;
import org.clxmm.shiro.service.impl.SecurityServiceImpl;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-15 6:01 下午
 */
public class DefinitionRealm extends AuthorizingRealm {
    /**
     * 鉴权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String loginName = (String) token.getPrincipal();

        SecurityService securityService = new SecurityServiceImpl();

        String password = securityService.findPasswordByLoginName(loginName);

        if ("".equals(password)) {
            throw new UnknownAccountException("账号不存在");
        }


        return new SimpleAuthenticationInfo(loginName, password, getName());
    }
}
