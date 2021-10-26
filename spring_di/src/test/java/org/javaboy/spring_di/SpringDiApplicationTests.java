package org.javaboy.spring_di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDiApplicationTests {

    @Autowired
    AService aService;
    @Test
    void contextLoads() {
        System.out.println("aService.getbService() = " + aService.getbService());
    }

}
