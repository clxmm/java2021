package realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import service.SecurityService;
import service.impl.SecurityServiceImpl;
import tools.DigestsUtil;

import java.util.List;
import java.util.Map;


/**
 * @author clxmm
 * @Description
 * @create 2021-05-15 6:01 下午
 */
public class DefinitionRealm extends AuthorizingRealm {


    public DefinitionRealm() {
        // 指定密码匹配方式
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(DigestsUtil.SHA1);
        // 密码迭代次数
        hashedCredentialsMatcher.setHashIterations(DigestsUtil.ITERATIONS);
        // 使用父层方法是匹配方式生效
        setCredentialsMatcher(hashedCredentialsMatcher);


    }

    /**
     * 鉴权方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 拿到用户凭证信息
        String loginName = (String) principalCollection.getPrimaryPrincipal();
        // 从数据库中查询对应的角色和权限
        SecurityService securityService = new SecurityServiceImpl();
        List<String> roles = securityService.findRoleByloginName(loginName);
        List<String> permissions = securityService.findPermissionByloginName(loginName);
        //构建资源校验对象

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String loginName = (String) token.getPrincipal();

        SecurityService securityService = new SecurityServiceImpl();

        Map<String, String> map = securityService.findPasswordByLoginName(loginName);

        if (map.isEmpty()) {
            throw new UnknownAccountException("账号不存在");
        }


        String salt = map.get("salt");
        String password = map.get("password");


        return new SimpleAuthenticationInfo(loginName, password, ByteSource.Util.bytes(salt), getName());
    }
}
