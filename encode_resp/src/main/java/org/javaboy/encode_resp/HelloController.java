package org.javaboy.encode_resp;

import org.javaboy.encode_resp.model.User;
import org.javaboy.encrypt.starter.anno.Decrypt;
import org.javaboy.encrypt.starter.anno.Encrypt;
import org.javaboy.encrypt.starter.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class HelloController {
    @GetMapping("/user")
    @Encrypt
    public RespBean getUser() {
        User user = new User();
        user.setId((long) 99);
        user.setUsername("javaboy");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/user")
    public RespBean addUser(@RequestBody @Decrypt User user) {
        System.out.println("user = " + user);
        return RespBean.ok("ok", user);
    }
}
