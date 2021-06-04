package org.clxmm.shiro.core.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.clxmm.shiro.config.JwtProperties;
import org.clxmm.shiro.utils.EmptyUtil;
import org.clxmm.shiro.utils.EncodesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clxmm
 * @Description 自定义jwttoken管理者
 * @create 2021-05-31 8:37 下午
 */
@Service("jwtTokenManager")
@EnableConfigurationProperties({JwtProperties.class})
public class JwtTokenManager {

    @Autowired
    JwtProperties jwtProperties;


    /**
     * @param iss       签发人
     * @param ttlMillis 有效时间
     * @param claims    jwt中存储的一些非隐私信息
     * @return
     * @Description 签发令牌
     * jwt字符串包括三个部分
     * 1. header
     * -当前字符串的类型，一般都是“JWT”
     * -哪种算法加密，“HS256”或者其他的加密算法
     * 所以一般都是固定的，没有什么变化
     * 2. payload
     * 一般有四个最常见的标准字段（下面有）
     * iat：签发时间，也就是这个jwt什么时候生成的
     * jti：JWT的唯一标识
     * iss：签发人，一般都是username或者userId
     * exp：过期时间
     */
    public String IssuedToken(String iss, long ttlMillis, String sessionId, Map<String, Object> claims) {

        if (EmptyUtil.isNullOrEmpty(claims)) {
            claims = new HashMap<>();
        }

        // 获取当前时间
        long timeMillis = System.currentTimeMillis();
        // 获取加密签名
        String hexEncodedSecretKey = jwtProperties.getBase64EncodedSecretKey();
        String encodeHex = EncodesUtil.encodeHex(hexEncodedSecretKey.getBytes());
        //构建令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                //2. 这个是JWT的唯一标识，一般设置成唯一的，这个方法可以生成唯一标识,此时存储的为sessionId,登录成功后回写
                .setId(sessionId)
                // //1. 这个地方就是以毫秒为单位，换算当前系统时间生成的iat
                .setIssuedAt(new Date(timeMillis))
                //3. 签发人，也就是JWT是给谁的（逻辑上一般都是username或者userId）
                .setSubject(iss)
                //这个地方是生成jwt使用的算法和秘钥
                .signWith(SignatureAlgorithm.HS256, encodeHex);

        if (ttlMillis >= 0) {
            long expMillis = timeMillis + ttlMillis;
            //4. 过期时间，这个也是使用毫秒生成的，使用当前时间+前面传入的持续时间生成
            Date exp = new Date(expMillis);
            jwtBuilder.setExpiration(exp);
        }

        return jwtBuilder.compact();

    }


    /**
     * @param jwtToken 令牌
     * @return
     * @Description 解析令牌
     */
    public Claims decodeToken(String jwtToken) {
        // 获取加密签名
        String base64EncodedSecretKey = EncodesUtil.encodeHex(jwtProperties.getBase64EncodedSecretKey().getBytes());
        // 带着密码去解析字符串
        Claims claims = Jwts.parser()
                .setSigningKey(base64EncodedSecretKey)
                // 设置签名的秘钥
                .parseClaimsJws(jwtToken)
                // 设置需要解析的 jwt
                .getBody();

        return claims;
    }


    /**
     * @Description 判断令牌是否合法,校验令牌:1、头部信息和荷载信息是否被篡改 2、校验令牌是否过期
     * @param jwtToken 令牌
     * @return
     */
    public boolean isVerifyToken(String jwtToken) {

        String base64EncodedSecretKey = EncodesUtil.encodeHex(jwtProperties.getBase64EncodedSecretKey().getBytes());

        //这个是官方的校验规则，这里只写了一个”校验算法“，可以自己加
        Algorithm algorithm = Algorithm.HMAC256(EncodesUtil.decodeBase64(base64EncodedSecretKey));
        JWTVerifier verifier = JWT.require(algorithm).build();
        // 校验不通过会抛出异常
        verifier.verify(jwtToken);
        //判断合法的标准：1. 头部和荷载部分没有篡改过。2. 没有过期
        return true;
    }
}
