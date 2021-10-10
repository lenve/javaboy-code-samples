package org.javaboy.scheduling.taskdemo;

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
public class SchedulingTaskDemo {
    public void taskWithParams(String params) {
        System.out.println("执行带参数的定时任务..." + params);
    }
    public void taskWithoutParams() {
        System.out.println("执行不带参数的定时任务...");
    }
}
