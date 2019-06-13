package org.javaboy.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-05-30 15:49
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        int i = 1 / 0;
        return "hello";
    }
}
