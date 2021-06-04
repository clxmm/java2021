package org.clxmm.shiro.core.impl;


import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.clxmm.shiro.constant.CacheConstant;
import org.clxmm.shiro.core.SimpleCacheService;
import org.clxmm.shiro.utils.ShiroRedissionSerialize;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;






/**
 * @author clxmm
 * @Description
 * @create 2021-05-25 9:04 下午
 */
@Component
public class SimpleCacheServiceImpl implements SimpleCacheService {


    @Resource(name = "redissonClientForShiro")
    RedissonClient redissonClient;

    @Override
    public void createCache(String name, Cache<Object, Object> cache){
        RBucket<String> bucket =  redissonClient.getBucket(CacheConstant.GROUP_CAS+name);
        bucket.trySet(ShiroRedissionSerialize.serialize(cache), SecurityUtils.getSubject().getSession().getTimeout()/1000, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Cache<Object, Object> getCache(String name) throws CacheException {
        RBucket<String> bucket =  redissonClient.getBucket(CacheConstant.GROUP_CAS+name);
        return (Cache<Object, Object>) ShiroRedissionSerialize.deserialize(bucket.get());
    }

    @Override
    public void removeCache(String name) throws CacheException {
        RBucket<String> bucket =  redissonClient.getBucket(CacheConstant.GROUP_CAS+name);
        bucket.delete();
    }

    @Override
    public void updateCahce(String name, Cache<Object, Object> cache){
        RBucket<String> bucket =  redissonClient.getBucket(CacheConstant.GROUP_CAS+name);
        bucket.set(ShiroRedissionSerialize.serialize(cache), SecurityUtils.getSubject().getSession().getTimeout()/1000, TimeUnit.MILLISECONDS);
    }
}
