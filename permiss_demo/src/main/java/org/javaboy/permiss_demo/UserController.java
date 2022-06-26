package org.javaboy.permiss_demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

    @GetMapping("/add")
    @PreAuthorize("hasPermission('/add','system:user:add')")
    public String addUser() {
        return "add";
    }
    @GetMapping("/delete")
    @PreAuthorize("hasPermission('/delete','system:user:delete')")
    public String deleteUser() {
        return "delete";
    }
    @GetMapping("/update")
    @PreAuthorize("hasPermission('/update','system:user:update')")
    public String updateUser() {
        return "update";
    }
    @GetMapping("/select")
    @PreAuthorize("hasPermission('/select','system:user:select')")
    public String selectUser() {
        return "select";
    }
}
