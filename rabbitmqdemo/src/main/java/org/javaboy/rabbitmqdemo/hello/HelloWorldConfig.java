package org.javaboy.rabbitmqdemo.hello;

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
public class HelloWorldConfig {

    public static final String HELLO_WORLD_QUEUE_NAME = "hello_world_queue";

    @Bean
    Queue queue1() {
        return new Queue(HELLO_WORLD_QUEUE_NAME);
    }
}
