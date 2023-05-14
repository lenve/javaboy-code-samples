package org.javaboy.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String JAVABOY_QUEUE_NAME = "javaboy_queue_name";
    public static final String JAVABOY_EXCHANGE_NAME = "javaboy_exchange_name";

    @Bean
    Binding javaboyBinding() {
        return BindingBuilder.bind(javaboyQueue())
                .to(javaboyExchange())
                .withQueueName();
    }

    @Bean
    DirectExchange javaboyExchange() {
        return new DirectExchange(JAVABOY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Queue javaboyQueue() {
        return new Queue(JAVABOY_QUEUE_NAME, true, false, false);
    }
}
