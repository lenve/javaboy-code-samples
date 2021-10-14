package org.javaboy.delay_queue;

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
    public static final String JAVABOY_QUEUE_NAME = "javaboy_queue_name";
    public static final String JAVABOY_EXCHANGE_NAME = "javaboy_exchange_name";
    public static final String JAVABOY_ROUTING_KEY = "javaboy_routing_key";
    public static final String DLX_QUEUE_NAME = "dlx_queue_name";
    public static final String DLX_EXCHANGE_NAME = "dlx_exchange_name";
    public static final String DLX_ROUTING_KEY = "dlx_routing_key";

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue dlxQueue() {
        return new Queue(DLX_QUEUE_NAME, true, false, false);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME, true, false);
    }

    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange())
                .with(DLX_ROUTING_KEY);
    }

    /**
     * 普通消息队列
     * @return
     */
    @Bean
    Queue javaboyQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息过期时间
        args.put("x-message-ttl", 1000*10);
        //设置死信交换机
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        //设置死信 routing_key
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return new Queue(JAVABOY_QUEUE_NAME, true, false, false, args);
    }

    /**
     * 普通交换机
     * @return
     */
    @Bean
    DirectExchange javaboyExchange() {
        return new DirectExchange(JAVABOY_EXCHANGE_NAME, true, false);
    }

    /**
     * 绑定普通队列和与之对应的交换机
     * @return
     */
    @Bean
    Binding javaboyBinding() {
        return BindingBuilder.bind(javaboyQueue())
                .to(javaboyExchange())
                .with(JAVABOY_ROUTING_KEY);
    }
}
