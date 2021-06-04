package org.clxmm.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.clxmm.shiro.utils.EmptyUtil;
import org.clxmm.shiro.utils.ShiroUserUtil;
import org.clxmm.shiro.utils.ShiroUtil;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author clxmm
 * @Description 自定义踢出过滤器
 * @create 2021-05-29 8:26 下午
 */
@Slf4j
public class KickedOutAuthorizationFilter extends AccessControlFilter {

    private RedissonClient redissonClient;

    private SessionDAO redisSessionDao;

    private DefaultWebSessionManager sessionManager;


    public KickedOutAuthorizationFilter(RedissonClient redissonClient, SessionDAO redisSessionDao, DefaultWebSessionManager sessionManager) {
        this.redissonClient = redissonClient;
        this.redisSessionDao = redisSessionDao;
        this.sessionManager = sessionManager;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        //1、只针对登录用户处理，首先判断是否登录
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        //2、使用RedissionClien创建队列
        String sessionId = ShiroUtil.getShiroSessionId();

        String LoginName = ShiroUserUtil.getShiroUser().getLoginName();
        RDeque<String> queue = redissonClient.getDeque("KickedOutAuthorizationFilter:"+LoginName);
        //3、判断当前sessionId是否存在于此用户的队列=key:登录名 value：多个sessionId


        boolean contains = queue.contains(sessionId);
        //4、不存在则放入队列尾端==>存入sessionId
        if (!contains) {
            queue.addLast(sessionId);
        }

        //5、判断当前队列大小是否超过限定此账号的可在线人数
        if (queue.size() > 1) {
            //6、超过：
            //*从队列头部拿到用户sessionId
            //  *从sessionManger根据sessionId拿到session
            //*从sessionDao中移除session会话
            sessionId = queue.getFirst();
            queue.removeFirst();
            Session session = null;
            try {
                session = sessionManager.getSession(new DefaultSessionKey(sessionId));
            }catch (UnknownSessionException ex){
                log.info("session已经失效");
            }catch (ExpiredSessionException expiredSessionException){
                log.info("session已经过期");
            }
            if (!EmptyUtil.isNullOrEmpty(session)){
                redisSessionDao.delete(session);
            }

        }

        //7、未超过：放过操作

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
