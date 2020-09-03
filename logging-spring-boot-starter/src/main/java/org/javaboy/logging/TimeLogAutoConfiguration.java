package org.javaboy.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨 spring
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
@Aspect
@EnableAspectJAutoProxy
@ConditionalOnProperty(prefix = "time.log", name = "enable", havingValue = "true", matchIfMissing = true)
public class TimeLogAutoConfiguration {

    private static final Logger logger = getLogger(TimeLogAutoConfiguration.class);

    @Around("@annotation(org.javaboy.logging.TimeLog)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getSignature().toLongString().split(" ")[2];
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("方法 {} 耗时 {} ms", name, endTime - startTime);
        return result;
    }
}
