package org.javaboy.ratelimiter.controller;

import org.javaboy.ratelimiter.annotation.RateLimiter;
import org.javaboy.ratelimiter.enums.LimitType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
    @RateLimiter(time = 5,count = 3,limitType = LimitType.IP)
    public String hello() {
        return "hello>>>"+new Date();
    }
}
