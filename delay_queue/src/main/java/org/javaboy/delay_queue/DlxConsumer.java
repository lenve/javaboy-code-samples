package org.javaboy.delay_queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DlxConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DlxConsumer.class);

    @RabbitListener(queues = QueueConfig.DLX_QUEUE_NAME)
    public void handle(String msg) {
        logger.info(msg);
    }
}
