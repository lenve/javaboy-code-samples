package org.javaboy.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class RabbitConfig {
    public static final String MY_QUEUE_NAME = "my_queue_name";
    public static final String MY_EXCHANGE_NAME = "my_exchange_name";
    public static final String MY_ROUTING_KEY = "my_queue_name";

    @Bean
    Queue queue() {
        return new Queue(MY_QUEUE_NAME, true, false, false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(MY_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(directExchange())
                .with(MY_ROUTING_KEY);
    }
}
