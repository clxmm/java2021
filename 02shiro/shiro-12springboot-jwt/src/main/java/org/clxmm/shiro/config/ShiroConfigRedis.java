package org.clxmm.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-26 8:29 下午
 */
@Configuration
@EnableConfigurationProperties({ShiroRedisProperties.class})
@Slf4j
public class ShiroConfigRedis {
    /**
     * 配置shiro redis bean
     ***************************/
    @Autowired
    ShiroRedisProperties shiroRedisProperties;


    /**
     * @return
     * @deprecated redission 客户端
     */
    @Bean(name = "redissonClientForShiro")
    public RedissonClient redissonClient(){
        //获取当前redis节点信息
        String[] nodes = shiroRedisProperties.getNodes().split(",");
        //创建配置信息：1、单机redis配置 2、集群redis配置
        Config config = new Config();
        if (nodes.length==1){
            //1、单机redis配置
            config.useSingleServer().setAddress(nodes[0])
                    .setConnectTimeout(shiroRedisProperties.getConnectTimeout())
                    .setConnectionMinimumIdleSize(shiroRedisProperties.getConnectionMinimumidleSize())
                    .setConnectionPoolSize(shiroRedisProperties.getConnectPoolSize())
                    .setTimeout(shiroRedisProperties.getTimeout());
        }else if(nodes.length>1) {
            //2、集群redis配置
            config.useClusterServers().addNodeAddress(nodes)
                    .setConnectTimeout(shiroRedisProperties.getConnectTimeout())
                    .setMasterConnectionMinimumIdleSize(shiroRedisProperties.getConnectionMinimumidleSize())
                    .setMasterConnectionPoolSize(shiroRedisProperties.getConnectPoolSize())
                    .setTimeout(shiroRedisProperties.getTimeout());
        }else {
            return null;
        }
        //创建redission的客户端，交于spring管理
        RedissonClient client = Redisson.create(config);
        return client;
    }

    /*****************************/
}
