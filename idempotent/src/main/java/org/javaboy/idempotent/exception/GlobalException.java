package org.javaboy.idempotent.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨 springboot
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IdempotentException.class)
    public String idempotentException(IdempotentException e) {
        return e.getMessage();
    }
}
