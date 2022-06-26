package org.javaboy.business.feign;

import org.javaboy.common.RespBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.POST;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@FeignClient("order")
public interface OrderFeignClient {
    @PostMapping("/createOrder")
    RespBean createOrder(@RequestParam("acount") String account, @RequestParam("count") Integer count, @RequestParam("productId") String productId);
}
