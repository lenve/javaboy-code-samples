package org.javaboy.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
@EnableAspectJAutoProxy
@Aspect
@Component
public class LogAspect {
    @Before("execution(* org.javaboy.demo.UserServiceImpl.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("jp.getSignature().getName() = " + jp.getSignature().getName());
    }
}
