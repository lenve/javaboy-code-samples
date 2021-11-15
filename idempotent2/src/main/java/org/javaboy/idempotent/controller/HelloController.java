package org.javaboy.idempotent.controller;

import org.javaboy.idempotent.annotation.AutoIdempotent;
import org.javaboy.idempotent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TokenService tokenService;

    @GetMapping("/gettoken")
    public String getToken() {
        return tokenService.createToken();
    }

    @PostMapping("/hello")
    @AutoIdempotent
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello2")
    public String hello2() {
        return "hello2";
    }
}
