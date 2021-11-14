package org.javaboy.boot_shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class MyRealm02 extends AuthorizingRealm {

    private final Map<String, User> users = new HashMap<>();

    public MyRealm02() {
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("zhangsan");
        u1.setPassword("123");
        users.put("zhangsan", u1);
        User u2 = new User();
        u2.setId(2);
        u2.setUsername("lisi");
        u2.setPassword("123");
        users.put("lisi", u2);
    }

    /**
     *
     * 正常，应该在这里，根据用户名去数据库中查询用户的角色
     *
     * 在这个方法中，返回用户的角色/权限
     * @param principals 这个参数中保存着用户的登录信息
     * @return 返回值就是用户的角色/权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //创建角色集合
        Set<String> roles = new HashSet<>();
        //获取登录用户名
        String username = (String) principals.getPrimaryPrincipal();
        if ("zhangsan".equals(username)) {
            roles.add("admin");
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        return simpleAuthorizationInfo;
    }

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
        String username = usernamePasswordToken.getUsername();
        User fromDB = users.get(username);
        if (fromDB == null) {
            throw new UnknownAccountException("用户名输入错误");
        }
        return new SimpleAuthenticationInfo(fromDB.getUsername(), fromDB.getPassword(), getName());
    }

}
