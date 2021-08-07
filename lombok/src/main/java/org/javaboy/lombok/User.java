package org.javaboy.lombok;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Getter
@Setter
@Slf4j
public class User {
    private Long id;
    private String name;
}
