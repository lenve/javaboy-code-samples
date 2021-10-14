package org.javaboy.delay_queue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DelayQueueApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        System.out.println(new Date());
        rabbitTemplate.convertAndSend(QueueConfig.JAVABOY_EXCHANGE_NAME, QueueConfig.JAVABOY_ROUTING_KEY, "hello javaboy!");
    }

}
