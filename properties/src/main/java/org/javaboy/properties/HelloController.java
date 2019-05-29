package org.javaboy.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 江南一点雨
 * @Date 2019-05-28 9:19
 */
@RestController
public class HelloController {
    @Autowired
    Book book;
    @GetMapping("/hello")
    public String hello() {
        System.out.println(book);
        return "hello";
    }
}
