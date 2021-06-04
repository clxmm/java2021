package org.clxmm.shiro.core.impl;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;

import java.util.concurrent.TimeUnit;

/**
 * @author clxmm
 * @Description 自定义密码比较器
 * @create 2021-05-29 4:47 下午
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {


    private RedissonClient redissonClient;

    /**
     * 允许重试的次数
     */
    private static Long RETRY_LIMIT_NUM = 4L;


    public RetryLimitCredentialsMatcher(String hashAlgorithmName, RedissonClient redissonClient) {
        super(hashAlgorithmName);
        this.redissonClient = redissonClient;
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //1、获取系统中是否已有登录次数缓存,缓存对象结构预期为："用户名--登录次数"。
        String loginName = (String) token.getPrincipal();

        RAtomicLong atomicLong = redissonClient.getAtomicLong(loginName);
        long l = atomicLong.get();  // 当前的次数

        //2、如果之前没有登录缓存，则创建一个登录次数缓存。


        //3、如果缓存次数已经超过限制，则驳回本次登录请求。

        if (l > RETRY_LIMIT_NUM) {
            //超过次数设计10分钟后重试
            atomicLong.expire(1000, TimeUnit.SECONDS);
            throw new ExcessiveAttemptsException("密码错误5次，请10分钟以后再试");
        }


        //4、将缓存记录的登录次数加1,设置指定时间内有效
        atomicLong.incrementAndGet();
        atomicLong.expire(1000, TimeUnit.SECONDS);
        //5、验证用户本次输入的帐号密码，如果登录登录成功，则清除掉登录次数的缓存
        //密码校验
        boolean flag = super.doCredentialsMatch(token, info);
        if (flag) {
            //校验成功删除限制
            atomicLong.delete();
        }
        return flag;
    }
}
