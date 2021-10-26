package org.javaboy.demo;

import org.javaboy.demo.config.JavaConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class UserDemo2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService2 us2 = ctx.getBean(UserService2.class);
        us2.transfer4();
    }
}
