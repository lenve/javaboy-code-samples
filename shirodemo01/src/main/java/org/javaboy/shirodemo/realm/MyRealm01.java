package org.javaboy.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.util.ByteSource;
import org.javaboy.shirodemo.mapper.UserMapper;
import org.javaboy.shirodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨  ssm
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */

/**
 * 继承自 AuthenticatingRealm 可以实现认证功能
 */
@Component
public class MyRealm01 extends AuthenticatingRealm {

    @Autowired
    UserMapper userMapper;

    /**
     * 核心的方法就是这个
     * <p>
     * 这个方法就干一件事：根据用户输入的用户名去数据库查询用户信息并返回
     *
     * @param token 参数中包含了用户登录时输入的用户名密码等信息
     * @return 返回值是从数据库中查询到的用户信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取用户登录时候的用户名
        String username = usernamePasswordToken.getUsername();
        User fromDB = userMapper.getUserByUsername(username);
        if (fromDB == null) {
            //说明用户登录时用户名写错了
            throw new UnknownAccountException("用户名输入错误");
        }
        //这里返回从数据库中查询到的用户信息
//        return new SimpleAuthenticationInfo(fromDB.getUsername(), fromDB.getPassword(), getName());
        //返回带盐的 SimpleAuthenticationInfo 对象
        return new SimpleAuthenticationInfo(fromDB.getUsername(), fromDB.getPassword(), ByteSource.Util.bytes(fromDB.getUsername()), getName());
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }
}
