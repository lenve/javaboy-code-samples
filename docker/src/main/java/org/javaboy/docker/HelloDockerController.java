package org.javaboy.docker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-23 11:57
 */
@RestController
public class HelloDockerController {
    @GetMapping("/hello")
    public String hello() {
        return "hello docker!123";
    }
}
