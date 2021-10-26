package org.javaboy.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class UserDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService us = ctx.getBean(UserService.class);
        us.transfer3();
    }
}
