package org.javaboy.order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    Queue orderLockedQueue() {
        return new Queue("order:locked");
    }


    @Bean
    Queue orderFinishQueue() {
        return new Queue("order:finish");
    }


    @Bean
    Queue orderFailQueue() {
        return new Queue("order:fail");
    }


    @Bean
    Queue orderNewQueue() {
        return new Queue("order:new");
    }

    @Bean
    Queue orderTicketMoveQueue() {
        return new Queue("order:ticket_move");
    }

    @Bean
    Queue orderTicketErrorQueue() {
        return new Queue("order:ticket_error");
    }

    @Bean
    Queue orderPayQueue() {
        return new Queue("order:pay");
    }

}
