package org.javaboy.https;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-10 20:02
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello https!";
    }
}
