package org.javaboy.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    MsgService msgService;
    @Test
    void contextLoads() throws IOException {
        msgService.send();
    }

}
