package org.javaboy.shirodemo.controller;

import org.javaboy.shirodemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨  ssm
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }
    @GetMapping("/admin")
    public String admin() {
        return helloService.admin();
    }
    @GetMapping("/unauthorizedUrl")
    public String unauthorizedUrl() {
        return "unauthorized";
    }
}
