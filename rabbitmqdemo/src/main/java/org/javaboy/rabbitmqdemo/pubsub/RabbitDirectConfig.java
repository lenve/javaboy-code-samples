package org.javaboy.rabbitmqdemo.pubsub;

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
public class RabbitDirectConfig {
    public final static String DIRECTNAME = "javaboy-direct";
    @Bean
    Queue queue() {
        return new Queue("hello-queue");
    }
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECTNAME, true, false);
    }
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(directExchange()).with("direct");
    }
}