package org.javaboy.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
    }

}
