package org.javaboy.business.controller;

import org.javaboy.business.service.BusinessService;
import org.javaboy.common.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @PostMapping("/order")
    public RespBean order(String account, String productId, Integer count) {
        try {
            businessService.purchase(account, productId, count);
            return RespBean.ok("下单成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("下单失败", e.getMessage());
        }
    }
}
