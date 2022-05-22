package org.javaboy.order.service;

import org.javaboy.common.RespBean;
import org.javaboy.order.feign.AccountFeign;
import org.javaboy.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AccountFeign accountFeign;

    public boolean createOrder(String account, String productId, Integer count) {
        //扣款，每件商品 100 块钱
        RespBean respBean = accountFeign.deductAccount(account, count * 100.0);
        int order = orderMapper.createOrder(account, productId, count);
        return order == 1 && respBean.getStatus() == 200;
    }
}
