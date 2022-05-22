package org.javaboy.tips;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
class TipsApplicationTests {

    @Value("${book.name}")
    String name;

    @Test
    void contextLoads() {
        System.out.println("name = " + name);

        List<String> list = new ArrayList<>();
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                return;
            }
        });
    }

}
