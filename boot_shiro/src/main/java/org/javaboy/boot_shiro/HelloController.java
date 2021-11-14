package org.javaboy.boot_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello shiro";
    }
    @GetMapping("/admin")
    @RequiresRoles("admin")
    public String admin() {
        return "hello admin";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username,String password) {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
            return "success";
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return "failed";
    }
}
