package org.javaboy.order.controller;

import org.javaboy.common.RespBean;
import org.javaboy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/createOrder")
    public RespBean createOrder(@RequestParam("acount") String account, @RequestParam("count") Integer count, @RequestParam("productId") String productId) {
        if (orderService.createOrder(account, productId, count)) {
            return RespBean.ok("下单成功");
        }
        return RespBean.error("下单失败");
    }
}
