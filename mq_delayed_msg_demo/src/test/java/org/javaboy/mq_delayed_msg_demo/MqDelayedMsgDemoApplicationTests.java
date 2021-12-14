package org.javaboy.mq_delayed_msg_demo;

import org.javaboy.mq_delayed_msg_demo.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@SpringBootTest
class MqDelayedMsgDemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
        Message msg = MessageBuilder.withBody(("hello 江南一点雨"+new Date()).getBytes("UTF-8")).setHeader("x-delay", 3000).build();
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.QUEUE_NAME, msg);
    }

}
