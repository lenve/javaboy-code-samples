package org.javaboy.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class ConsumerDemo {
//    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME)
//    public void handle(Channel channel, Message message) {
//        //获取消息编号
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
//            //拒绝消息
//            channel.basicReject(deliveryTag, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME)
//    public void handle2(String msg) {
//        System.out.println("msg = " + msg);
//    }

    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME)
    public void handle3(Message message,Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //消息消费的代码写到这里
            String s = new String(message.getBody());
            System.out.println("s = " + s);
            //消费完成后，手动 ack
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //手动 nack
            try {
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
