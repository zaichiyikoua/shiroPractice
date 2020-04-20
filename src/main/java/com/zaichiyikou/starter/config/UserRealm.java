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
import org.apache.shiro.util.ByteSource;

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
        // 加盐 这里面的参数应该是生成用户时的salt字段值，这里用password代替
        ByteSource credentialsSalt = ByteSource.Util.bytes(password);
        // 密码认证，由shiro自己做 最重要的就是第二个参数，由密文比对器进行比对
        // return new SimpleAuthenticationInfo("", password, realmName);
        // 如果有盐salt，那么用多一个参数
        return new SimpleAuthenticationInfo(username, password, credentialsSalt, getName());
    }

}
