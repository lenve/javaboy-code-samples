package org.javaboy.order.service;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.common.feign.AccountServiceApi;
import org.javaboy.order.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    AccountServiceApi accountServiceApi;

    @Autowired
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean prepareCreateOrder(BusinessActionContext actionContext, String userId, String productId, Integer count) {
        //先去扣款，假设每个产品100块钱
        boolean resp = accountServiceApi.prepare(actionContext, userId, count * 100.0);
        logger.info("{} 用户购买的 {} 商品共计 {} 件，预下单成功", userId, productId, count);
        return resp;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean commitOrder(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        int i = orderMapper.addOrder(userId, productId, count, count * 100.0);
        logger.info("{} 用户购买的 {} 商品共计 {} 件，下单成功", userId, productId, count);
        return i==1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollbackOrder(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        logger.info("{} 用户购买的 {} 商品共计 {} 件，订单回滚成功", userId, productId, count);
        return true;
    }
}
