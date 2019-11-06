package org.javaboy.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-09-12 15:29
 */
@Component
public class MyJob1 {
    public void hello() {
        System.out.println("MyJob1>>>"+new Date());
    }
}
