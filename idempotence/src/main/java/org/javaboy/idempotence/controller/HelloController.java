package org.javaboy.idempotence.controller;

import org.javaboy.idempotence.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/hello")
    @RepeatSubmit(interval = 100000)
    public String hello(@RequestBody String msg) {
        System.out.println("msg = " + msg);
        return "hello";
    }
}
