package org.javaboy.flowable03.controller;

import org.javaboy.flowable03.model.RespBean;
import org.javaboy.flowable03.model.User;
import org.javaboy.flowable03.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class UserController {
    @Autowired
    MyUserDetailsService userDetailsService;

    @GetMapping("/users")
    public RespBean getUsers() {
        return RespBean.ok("OK",userDetailsService.getAllUsers());
    }
}
