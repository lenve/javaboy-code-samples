package org.javaboy.rabbitmqdemo;

import org.javaboy.rabbitmqdemo.hello.HelloWorldConfig;
import org.javaboy.rabbitmqdemo.pubsub.RabbitFanoutConfig;
import org.javaboy.rabbitmqdemo.pubsub.RabbitHeaderConfig;
import org.javaboy.rabbitmqdemo.pubsub.RabbitTopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqdemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    void contextLoads() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(HelloWorldConfig.HELLO_WORLD_QUEUE_NAME, "hello");
        }
    }

    @Test
    public void directTest() {
        rabbitTemplate.convertAndSend("hello-queue", "hello direct!");
    }

    @Test
    public void fanoutTest() {
        rabbitTemplate
                .convertAndSend(RabbitFanoutConfig.FANOUTNAME,
                        null, "hello fanout!");
    }

    @Test
    public void topicTest() {
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "xiaomi.news","小米新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "huawei.news","华为新闻..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "xiaomi.phone","小米手机..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "huawei.phone","华为手机..");
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "phone.news","手机新闻..");
    }

    @Test
    public void headerTest() {
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue".getBytes())
                .setHeader("name", "sang").build();
        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue".getBytes())
                .setHeader("age", "99").build();
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, nameMsg);
    }



}
