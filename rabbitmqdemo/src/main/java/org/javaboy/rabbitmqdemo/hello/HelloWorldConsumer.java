package org.javaboy.rabbitmqdemo.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class HelloWorldConsumer {
    @RabbitListener(queues = HelloWorldConfig.HELLO_WORLD_QUEUE_NAME)
    public void receive(Message message,Channel channel) throws IOException {
        System.out.println("receive="+message.getPayload());
        channel.basicAck(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)),true);
    }

    @RabbitListener(queues = HelloWorldConfig.HELLO_WORLD_QUEUE_NAME, concurrency = "10")
    public void receive2(Message message, Channel channel) throws IOException {
        System.out.println("receive2 = " + message.getPayload() + "------->" + Thread.currentThread().getName());
        channel.basicReject(((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG)), true);
    }
}
