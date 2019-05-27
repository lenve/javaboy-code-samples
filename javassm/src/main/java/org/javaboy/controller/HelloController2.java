package org.javaboy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 江南一点雨
 * @Date 2019-05-27 11:13
 */
@Controller
public class HelloController2 {
    @GetMapping("/hello2")
    public String hello() {
        return "hello";
    }
}
