package org.javaboy.spring_tran04;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SpringTran04ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() throws IOException {
        userService.transfer();
    }

}
