package org.javaboy.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-04 14:25
 */
@Component
public class HelloComponent {
    /**
     * @Scheduled 注解表示起开定时任务
     *
     * fixedDelay  属性表示下一个任务在本次任务执行结束 2000 ms 后开始
     * fixedRate 表示下一个任务在本次任务开始 2000ms 之后开始
     * initialDelay 表示启动延迟时间
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void hello() {
        System.out.println("hello:"+new Date());
    }
}
