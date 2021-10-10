package org.javaboy.rabbitmq_ttl;

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
public class ConsumerDemo {
    @RabbitListener(queues = QueueConfig.JAVABOY_QUEUE_DEMO)
    public void handle(String msg) {
        System.out.println("msg = " + msg);
    }

    @RabbitListener(queues = QueueConfig.DLX_QUEUE_NAME)
    public void dlxHandle(String msg) {
        System.out.println("dlx msg = " + msg);
    }
}
