package org.clxmm.shiro.core.impl;

import io.jsonwebtoken.Claims;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.clxmm.shiro.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @author clxmm
 * @Description 自定义会话管理器
 * @create 2021-06-01 7:54 下午
 */
public class ShiroSessionManager extends DefaultWebSessionManager {


    /**
     * @deprecated 从请求中获取sessionId的key
     */
    private static final String AUTHORIZATION = "jwtToken";

    /**
     * @deprecated 自定义注入的资源类型的名称
     */
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    @Autowired
    JwtTokenManager jwtTokenManager;

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 判断request请求中是否带有jwtToken的key
        String jwtToken = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (EmptyUtil.isNullOrEmpty(jwtToken)) {
            // 如果没有走默认的cookie机制
            return super.getSessionId(request, response);
        }
        //如果请求头中有 authToken 则其值为jwtToken，然后解析出会话session

        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
        Claims decode = jwtTokenManager.decodeToken(jwtToken);
        String id = (String) decode.get("jti");
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
        request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        return id;
    }
}
