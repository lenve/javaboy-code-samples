package org.javaboy.idempotent.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.javaboy.idempotent.exception.IdempotentException;
import org.javaboy.idempotent.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.javaboy.idempotent.annotation.AutoIdempotent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 *
 * Spring Boot 自定义注解：
 *
 * 1. 通过拦截器解析
 * 2. 通过 AOP 解析
 *
 * idempotent2
 */
@Component
@Aspect
public class IdempotentAspect {
    @Autowired
    TokenService tokenService;

    @Pointcut("@annotation(org.javaboy.idempotent.annotation.AutoIdempotent)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws IdempotentException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            tokenService.checkToken(request);
        } catch (IdempotentException e) {
            throw e;
        }
    }
}
