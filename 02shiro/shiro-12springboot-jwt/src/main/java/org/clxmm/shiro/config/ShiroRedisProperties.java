package org.clxmm.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-25 8:34 下午
 */
@Data
@ConfigurationProperties(prefix = "shiro.redis")
@Component
public class ShiroRedisProperties implements Serializable {


    /**
     * redis连接地址
     */
    private String nodes;

    /**
     * 获取连接超时时间
     */
    private int connectTimeout;

    /**
     * 连接池大小
     */
    private int connectPoolSize;

    /**
     * 初始化连接数
     */
    private int connectionMinimumidleSize;

    /**
     * 等待数据返回超时时间
     */
    private int timeout;

    /**
     * 全局超时时间
     */
    private long globalSessionTimeout;


}
