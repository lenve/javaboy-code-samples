package org.javaboy.rabbitmqdemo.pubsub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
public class HeaderReceiver {
    @RabbitListener(queues = "name-queue")
    public void handler1(byte[] msg) {
        System.out.println("HeaderReceiver:name:"
                + new String(msg, 0, msg.length));
    }
    @RabbitListener(queues = "age-queue")
    public void handler2(byte[] msg) {
        System.out.println("HeaderReceiver:age:"
                + new String(msg, 0, msg.length));
    }
}
