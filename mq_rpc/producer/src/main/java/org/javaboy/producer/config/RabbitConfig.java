package org.javaboy.producer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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

    public static final String RPC_QUEUE1 = "queue_1";
    public static final String RPC_QUEUE2 = "queue_2";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    /**
     * 设置同步RPC队列
     */
    @Bean
    Queue msgQueue() {
        return new Queue(RPC_QUEUE1);
    }

    /**
     * 设置返回队列
     */
    @Bean
    Queue replyQueue() {
        return new Queue(RPC_QUEUE2);
    }

    /**
     * 设置交换机
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RPC_EXCHANGE);
    }

    /**
     * 请求队列和交换器绑定
     */
    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue()).to(exchange()).with(RPC_QUEUE1);
    }

    /**
     * 返回队列和交换器绑定
     */
    @Bean
    Binding replyBinding() {
        return BindingBuilder.bind(replyQueue()).to(exchange()).with(RPC_QUEUE2);
    }


    /**
     * 使用 RabbitTemplate发送和接收消息
     * 并设置回调队列地址
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RPC_QUEUE2);
        template.setReplyTimeout(6000);
        return template;
    }


    /**
     * 给返回队列设置监听器
     */
    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RPC_QUEUE2);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}