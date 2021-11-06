package org.javaboy.shirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨 ssm  shiro
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class LoginController {
    @PostMapping(value = "/doLogin", produces = "text/html;charset=utf-8")
    public String doLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //执行登录操作
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            //获取登录成功的用户信息
            List list = subject.getPrincipals().asList();
            for (Object o : list) {
                System.out.println(o.getClass() + "=====>" + o);
            }
        } catch (AuthenticationException e) {
            return "登录失败：" + e.getMessage();
        }
        return "success";
    }
}
