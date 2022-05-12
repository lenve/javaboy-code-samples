package org.javaboy.tips;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TipsApplicationTests {

    @Value("${book.name}")
    String name;

    @Test
    void contextLoads() {
        System.out.println("name = " + name);
    }

}
