package org.javaboy.rabbitmq_ttl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
public class QueueConfig {

    public static final String JAVABOY_QUEUE_DEMO = "javaboy_queue_demo";
    public static final String JAVABOY_EXCHANGE_DEMO = "javaboy_exchange_demo";
    public static final String HELLO_ROUTING_KEY = "hello_routing_key";
    public static final String DLX_EXCHANGE_NAME = "dlx_exchange_name";
    public static final String DLX_QUEUE_NAME = "dlx_queue_name";
    public static final String DLX_ROUTING_KEY = "dlx_routing_key";

    @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息过期时间
        args.put("x-message-ttl", 0);
        //设置死信交换机
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        //设置死信 routing_key，可以不设置，不设置就使用原来的 routing_key
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return new Queue(JAVABOY_QUEUE_DEMO, true, false, false, args);
    }

    /**
     * 配置死信交换机
     *
     * @return
     */
    @Bean
    DirectExchange dlxDirectExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME, true, false);
    }

    /**
     * 配置死信队列
     * @return
     */
    @Bean
    Queue dlxQueue() {
        return new Queue(DLX_QUEUE_NAME);
    }

    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxDirectExchange())
                .with(DLX_ROUTING_KEY);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(JAVABOY_EXCHANGE_DEMO, true, false);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(directExchange())
                .with(HELLO_ROUTING_KEY);
    }
}
