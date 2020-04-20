package com.zaichiyikou.starter.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    // shiro三大核心对象就是对应了一下三个
    //配置的时候从下往上配置
    
    private static final String hashAlgorithmName = "md5";
    private static final int hashIterations = 100;

    // shiroFilterBean
    @Bean(name = "ShiroFilterFactoryBean") 
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
    public UserRealm userRealm(@Qualifier("HashedCredentialsMatcher")HashedCredentialsMatcher matcher) {
        UserRealm realm = new UserRealm();
        // 配置密文比对器
        realm.setCredentialsMatcher(matcher);
        return realm;
    }
    
    // 密文比对器，又叫做凭证比对器
    // 由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理，需要对加密细节进行配置
    @Bean(name = "HashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密算法设定
        matcher.setHashAlgorithmName(hashAlgorithmName);
        // 加密迭代次数
        matcher.setHashIterations(hashIterations);
        // 是否加盐  官方建议不要再使用该方法
//        matcher.setHashSalted(hashSalted);
        // 是否存储密文是十进制 true是十进制 false是base64格式
//        matcher.setStoredCredentialsHexEncoded();
        return matcher;
    }
}
