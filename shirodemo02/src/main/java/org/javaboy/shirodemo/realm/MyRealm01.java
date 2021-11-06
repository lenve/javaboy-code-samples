package org.javaboy.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.stereotype.Component;

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

    /**
     * 这个 Realm 不做认证工作，单纯只是为了和大家测试多 Realm 的认证策略
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //返回的密码就是用户登录时输入的密码，所以无论用户用什么密码都能登录成功
        return new SimpleAuthenticationInfo("javaboy", usernamePasswordToken.getCredentials(),  getName());
    }
}
