package org.sang.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @CrossOrigin("http://localhost:8081")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
