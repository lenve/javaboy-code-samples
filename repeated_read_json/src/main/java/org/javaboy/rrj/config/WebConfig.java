package org.javaboy.rrj.config;

import org.javaboy.rrj.filter.RepeatableFilter;
import org.javaboy.rrj.interceptor.IdempotenceInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IdempotenceInterceptor()).addPathPatterns("/**");
    }

    @Bean
    FilterRegistrationBean<RepeatableFilter> repeatableFilterBean() {
        FilterRegistrationBean<RepeatableFilter> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/*");
        bean.setFilter(new RepeatableFilter());
        return bean;
    }
}
