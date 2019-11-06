package org.javaboy.dockerjib;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-06 11:13
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello jib";
    }
}
