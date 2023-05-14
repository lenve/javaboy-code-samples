package org.javaboy.producer;

import org.javaboy.producer.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitConfig.JAVABOY_EXCHANGE_NAME, RabbitConfig.JAVABOY_QUEUE_NAME, "hello " + i);
        }
    }

}
