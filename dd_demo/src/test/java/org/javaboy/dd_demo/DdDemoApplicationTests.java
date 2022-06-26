package org.javaboy.dd_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DdDemoApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
//        System.out.println("userService.count() = " + userService.count());
//        System.out.println("userService.addUser() = " + userService.addUser());
        userService.addUser2();
    }



}
