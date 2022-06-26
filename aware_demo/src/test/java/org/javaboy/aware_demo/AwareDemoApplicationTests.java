package org.javaboy.aware_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AwareDemoApplicationTests {

    @Test
    void contextLoads() {
        UserService userService = BeanUtils.getBean("userService");
        userService.hello();
    }

}
