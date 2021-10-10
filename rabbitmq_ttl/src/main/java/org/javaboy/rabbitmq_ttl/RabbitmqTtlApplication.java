package org.javaboy.rabbitmq_ttl;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqTtlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqTtlApplication.class, args);
    }

}
