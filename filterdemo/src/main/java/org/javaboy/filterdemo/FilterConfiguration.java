package org.javaboy.filterdemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Configuration
public class FilterConfiguration {
    @Bean
    FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());
        bean.setOrder(-1);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
    @Bean
    FilterRegistrationBean<MyFilter2> myFilterFilterRegistrationBean2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter2());
        bean.setOrder(-2);
        bean.setUrlPatterns(Arrays.asList("/hello"));
        return bean;
    }
}
