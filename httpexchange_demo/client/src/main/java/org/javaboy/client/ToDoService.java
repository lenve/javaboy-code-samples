package org.javaboy.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(value = "/server")
public interface ToDoService {
    @GetExchange("/hello")
    String hello(@RequestParam String name);
}
