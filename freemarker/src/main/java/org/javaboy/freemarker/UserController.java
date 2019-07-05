package org.javaboy.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-07-04 10:01
 */
@Controller
public class UserController {
    @GetMapping("/index")
    public String index(Model model) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId((long) i);
            user.setUsername("javaboy>>>>" + i);
            user.setAddress("www.javaboy.org>>>>" + i);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "index";
    }
}
