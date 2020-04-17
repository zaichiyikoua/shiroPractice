package com.zaichiyikou.starter.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

// 自定义的 userRealm 需要去继承认证的这个AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    private static final String realmName = null;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        System.out.println("执行了授权  doGetAuthorizationInfo");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // TODO Auto-generated method stub
        System.out.println("执行了认证  doGetAuthorizationInfo");
        // 主要认证的逻辑都在这个realm中编写

        // 拿到用户名和密码 数据库中取出
        // 模拟数据
        String username = "root";
        String password = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // 逻辑处理
        if (!userToken.getUsername().equals(username)) {
            // 这里就会抛出异常 用户名不存在
            return null;
        }
        // 密码认证，由shiro自己做
        return new SimpleAuthenticationInfo("", password, "");
    }

}
