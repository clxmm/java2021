package org.clxmm.shiro.core.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.clxmm.shiro.constant.CacheConstant;
import org.clxmm.shiro.utils.ShiroRedissionSerialize;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @Description 实现shiro session的memcached集中式管理~
 * @create 2021-05-27 8:12 下午
 */
@Slf4j
public class RedisSessionDao extends AbstractSessionDAO {


    @Resource(name = "redissonClientForShiro")
    RedissonClient redissonClient;

    private Long globalSessionTimeout;


    /**
     * @param session
     * @return
     * @deprecated 创建session 会话对象，
     */
    @Override
    protected Serializable doCreate(Session session) {
        // 创建唯一标识的 sessionid
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
//		log.info("=============创建sessionId:{}",sessionId);
        RBucket<String> sessionIdRBucket = redissonClient.getBucket(CacheConstant.GROUP_CAS + sessionId.toString());
        sessionIdRBucket.trySet(ShiroRedissionSerialize.serialize(session), globalSessionTimeout, TimeUnit.SECONDS);
        // 为session会话指定唯一的sessionid


        return sessionId;
    }

    /**
     * @deprecated 读取 seesion
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        RBucket<String> sessionIdRBucket = redissonClient.getBucket(CacheConstant.GROUP_CAS+sessionId.toString());
        Session session = (Session) ShiroRedissionSerialize.deserialize(sessionIdRBucket.get());
		log.info("=============读取sessionId:{}",session.getId().toString());
        return session;

    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String key = CacheConstant.GROUP_CAS + session.getId().toString();

        RBucket<String> bucket = redissonClient.getBucket(key);
        System.out.println(session);
        bucket.set(ShiroRedissionSerialize.serialize(session),globalSessionTimeout,TimeUnit.SECONDS);


    }

    /**
     * @deprecated  删除
     * @param session
     */
    @Override
    public void delete(Session session) {
        String key = CacheConstant.GROUP_CAS + session.getId().toString();
        RBucket<String> bucket = redissonClient.getBucket(key);
        bucket.delete();
    }

    /**
     * @deprecated 统计当前活跃用户
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.emptySet();
    }


    public void setGlobalSessionTimeout(Long globalSessionTimeout) {
        this.globalSessionTimeout = globalSessionTimeout;
    }
}
