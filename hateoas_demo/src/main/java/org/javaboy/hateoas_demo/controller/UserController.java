package org.javaboy.hateoas_demo.controller;

import org.javaboy.hateoas_demo.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public CollectionModel<User> list(HttpServletRequest req) {
        List<User> list = new ArrayList<>();
        User u1 = new User(1);
        u1.setUsername("javaboy");
        u1.setAddress("www.javaboy.org");
        list.add(u1);
        User u2 = new User(2);
        u2.setUsername("itboy");
        u2.setAddress("www.itboyhub.com");
        list.add(u2);
        CollectionModel<User> users = CollectionModel.of(list);
        users.add(WebMvcLinkBuilder.linkTo(UserController.class).withRel("users"));
        return users;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getOne(@PathVariable Integer id) throws NoSuchMethodException {
        User u = new User(id);
        u.setUsername("javaboy");
        u.setAddress("深圳");
        u.add(Link.of("http://localhost:8080/users/"+id, "getOne"));
        Link users = WebMvcLinkBuilder.linkTo(UserController.class).withRel("users");
        u.add(users);
        Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(u.getId()).withSelfRel();
        u.add(link);
        Method method = UserController.class.getMethod("getOne", Integer.class);
        Link link2 = WebMvcLinkBuilder.linkTo(method, id).withSelfRel();
        u.add(link2);
        return EntityModel.of(u);
    }
}
