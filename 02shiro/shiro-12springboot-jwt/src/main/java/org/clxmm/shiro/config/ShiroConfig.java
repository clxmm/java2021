package org.clxmm.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.clxmm.shiro.core.ShiroDbRealm;
import org.clxmm.shiro.core.impl.JwtTokenManager;
import org.clxmm.shiro.core.impl.RedisSessionDao;
import org.clxmm.shiro.core.impl.ShiroDbRealmImpl;
import org.clxmm.shiro.core.impl.ShiroSessionManager;
import org.clxmm.shiro.filter.*;
import org.clxmm.shiro.properties.PropertiesUtil;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author clxmm
 * @Description 权限管理配置类
 * @create 2021-05-22 8:13 下午
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({ShiroRedisProperties.class})
public class ShiroConfig {


    @Autowired
    ShiroRedisProperties shiroRedisProperties;

    @Resource(name = "redissonClientForShiro")
    RedissonClient redissonClient;

    @Autowired
    JwtTokenManager jwtTokenManager;


    //创建cookie对象
    @Bean(name = "simpleCookie")
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("ShiroSession");
        return simpleCookie;
    }



    /**
     * @Description 自定义session会话存储的实现类 ，使用Redis来存储共享session，达到分布式部署目的
     */
    @Bean("redisSessionDao")
    public SessionDAO redisSessionDao(){
        RedisSessionDao sessionDAO =   new RedisSessionDao();
        sessionDAO.setGlobalSessionTimeout(shiroRedisProperties.getGlobalSessionTimeout());
        return sessionDAO;
    }

    //创建权限管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //管理realm
        securityManager.setRealm(shiroDbRealm());
        //管理会话
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }

    //自定义realm
    @Bean("shiroDbRealm")
    public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealmImpl();
    }


    // 替换 自定义的ShiroSessionManager 作用是加入对jwtToken的支持
    //会话管理器
    @Bean("sessionManager")
    public ShiroSessionManager sessionManager() {
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        // 指定自定义的 redis 的
        sessionManager.setSessionDAO(redisSessionDao());
        //关闭会话更新
        sessionManager.setSessionValidationSchedulerEnabled(false);
        //生效cookie
        sessionManager.setSessionIdCookieEnabled(true);
        //指定cookie的生成策略
        sessionManager.setSessionIdCookie(simpleCookie());
        //指定全局会话超时时间
        sessionManager.setGlobalSessionTimeout(3600000);


        return sessionManager;

    }


    //创建生命周期的管理
    @Bean("lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //aop增强（使用注解鉴权方式）

    /**
     * @Description AOP式方法级权限检查
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


    /**
     * @Description 配合DefaultAdvisorAutoProxyCreator事项注解权限校验
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(defaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * @Description 过滤器链定义
     */
    private Map<String, String> filterChainDefinitionMap() {
        List<Object> list = PropertiesUtil.propertiesShiro.getKeyList();
        Map<String, String> map = new LinkedHashMap<>();
        for (Object o : list) {
            String key = o.toString();
            String val = PropertiesUtil.getShiroValue(key);
            map.put(key, val);
        }
        return map;
    }

    /**
     * @Description 加载自定义过滤器
     */
    private Map<String, Filter> filters() {
        Map<String, Filter> map = new HashMap<>();
        map.put("roles-or", new RolesOrAuthorizationFilter());
        map.put("kickedOut", new KickedOutAuthorizationFilter(redissonClient, redisSessionDao(), sessionManager()));

        //
        map.put("jwt-authc", new JwtAuthcFilter(jwtTokenManager));
        map.put("jwt-perms", new JwtPermsFilter());
        map.put("jwt-roles", new JwtRolesFilter());
        return map;
    }

    //shiro过滤器管理
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        //过滤器
        shiroFilterFactoryBean.setFilters(filters());

        //过滤器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        return shiroFilterFactoryBean;
    }


}
