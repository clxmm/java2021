package org.clxmm.shiro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author clxmm
 * @Description
 * @create 2021-05-31 8:34 下午
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "clxmm.jwt")
public class JwtProperties implements Serializable {

    /**
     * @Description 签名密码
     */
    private String base64EncodedSecretKey;
}
