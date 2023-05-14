package org.javaboy.consumer.consumer;

import org.javaboy.consumer.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {

    public final static Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME,concurrency = "10")
    public void handleMsg(String msg) {
        logger.info("msg:{}", msg);
    }
//    @RabbitListener(queues = RabbitConfig.JAVABOY_QUEUE_NAME)
//    public void handleMsg2(String msg) {
//        logger.info("msg2:{}", msg);
//    }
}
