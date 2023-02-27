package org.javaboy.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/server/hello")
    public String hello(String name) {
        return "hello " + name;
    }

}
