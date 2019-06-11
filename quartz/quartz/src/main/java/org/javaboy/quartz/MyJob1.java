package org.javaboy.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-06-04 15:12
 */
@Component
public class MyJob1 {
    public void sayHello() {
        System.out.println("MyJob1>>>"+new Date());
    }
}