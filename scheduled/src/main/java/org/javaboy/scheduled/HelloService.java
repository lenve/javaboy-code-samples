package org.javaboy.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-09-12 15:16
 */
@Component
public class HelloService {

//    @Scheduled(fixedRate = 2000)
    @Scheduled(cron = "0 0 2 ? * WED")
    public void hello() {
        System.out.println("hello:" + new Date());
    }
}
