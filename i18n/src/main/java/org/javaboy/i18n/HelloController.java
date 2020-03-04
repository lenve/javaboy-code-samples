package org.javaboy.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @作者 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 * @Date 2020/3/4 10:45
 */
@RestController
public class HelloController {
    @Autowired
    MessageSource messageSource;
    @GetMapping("/hello")
    public String hello() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("user.name", null, locale);
    }
}
