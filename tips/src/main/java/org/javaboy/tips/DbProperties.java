package org.javaboy.tips;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@PropertySource("classpath:data.properties")
@ConfigurationProperties(prefix = "db")
public class DbProperties {
    private String username;
    private String password;
    private String url;
}
