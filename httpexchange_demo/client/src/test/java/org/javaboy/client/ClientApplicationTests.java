package org.javaboy.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

    @Autowired
    ToDoService toDoService;

    @Test
    void contextLoads() {
        String hello = toDoService.hello("javaboy666");
        System.out.println("hello = " + hello);
    }

}
