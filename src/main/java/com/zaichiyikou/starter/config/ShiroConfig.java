package com.zaichiyikou.starter.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    // shiro三大核心对象就是对应了一下三个
    //配置的时候从下往上配置
    
    // shiroFilterBean
    @Bean 
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        factoryBean.setSecurityManager(securityManager);
        //添加shrio的内置过滤器
        /*
         * anon：无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须拥有“记住我”才能用
         * perms：拥有某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        filterMap.put("/user/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);
        // 如果没有权限，设置登录页面
        factoryBean.setLoginUrl("/login");
        
        return factoryBean;
    }
    // defaultWebSecuriryManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 需要关联realm
        manager.setRealm(userRealm);
        return manager;
    }
    // 创建realm对象
    // 用户认证一般都会在这个realm对象中
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
