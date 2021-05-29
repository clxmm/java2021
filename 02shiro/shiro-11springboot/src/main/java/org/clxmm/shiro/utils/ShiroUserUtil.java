package org.clxmm.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.clxmm.shiro.constant.SuperConstant;
import org.clxmm.shiro.core.base.ShiroUser;

/**
 * @author clxmm
 * @Description shiroUser工具类
 * @create 2021-05-23 11:45 上午
 */
public class ShiroUserUtil {

    /**
     * @Description 返回当前登录用户封装对象
     * @param
     * @return
     */
    public static ShiroUser getShiroUser() {
        //System.out.println(SecurityUtils.getSubject());
        if (!EmptyUtil.isNullOrEmpty(ThreadContext.getSubject()) && !EmptyUtil.isNullOrEmpty(SecurityUtils.getSubject().getPrincipal())) {
            return (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        }else {
            return new ShiroUser(SuperConstant.ANON_ID, SuperConstant.ANON_LOGIN_NAME);
        }
    }

    /**
     * @Description 获得shiroUserId
     * @param
     * @return
     */
    public static String getShiroUserId() {
        ShiroUser shiroUser = ShiroUserUtil.getShiroUser();
        if (EmptyUtil.isNullOrEmpty(shiroUser)) {
            return null;
        }else {
            return shiroUser.getId();
        }
    }


    /**
     * @Description 更新登录用户信息  用户id 和用户登录名不更新
     * @param shiroUser 需要修改对象
     * @return
     */
    public static void updateShiroUser(ShiroUser shiroUser){
        String subjectKey = "org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";
        Subject subject = SecurityUtils.getSubject();
        Object ooo = subject.getSession().getAttribute(subjectKey);
        SimplePrincipalCollection collection = (SimplePrincipalCollection)ooo;
        ShiroUser user = (ShiroUser)collection.getPrimaryPrincipal();
        subject.getSession().setAttribute(subjectKey, collection);
    }


}
