package org.javaboy.shirodemo.service;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Service;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class HelloService {
    /**
     * 登录之后就可以访问该方法，无论是通过用户名/密码的方式登录还是通过记住我的方式登录
     * @return
     */
    @RequiresUser
    public String hello() {
        return "hello";
    }
    @RequiresRoles("admin")
    public String admin() {
        return "admin";
    }
}
