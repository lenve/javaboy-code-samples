package org.javaboy.business.service;

import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.javaboy.common.feign.OrderServiceApi;
import org.javaboy.common.feign.StorageServiceApi;
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
public class BusinessService {
    @Autowired
    StorageServiceApi storageServiceApi;
    @Autowired
    OrderServiceApi orderServiceApi;

    @GlobalTransactional
    public void purchase(String account, String productId, Integer count) {
        String xid = RootContext.getXID();
        BusinessActionContext actionContext = new BusinessActionContext();
        actionContext.setXid(xid);
        storageServiceApi.deduct(actionContext, productId, count);
        orderServiceApi.prepare(actionContext, account, productId, count);
    }
}