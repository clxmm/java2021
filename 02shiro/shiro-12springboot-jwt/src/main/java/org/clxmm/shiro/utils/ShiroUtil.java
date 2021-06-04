package org.clxmm.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author clxmm
 * @Description shiro工具类
 * @create 2021-05-23 11:45 上午
 */
public class ShiroUtil {



    /**
     * @Description 获得shiro的session
     * @param
     * @return
     */
    public static Session getShiroSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * @Description 获得shiro的sessionId
     * @param
     * @return
     */
    public static String getShiroSessionId() {
        return getShiroSession().getId().toString();
    }

    /**
     * @Description 是否登陆
     * @param
     * @return
     */
    public static Boolean isAuthenticated(){
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }
}
