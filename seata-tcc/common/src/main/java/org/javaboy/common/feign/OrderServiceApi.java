package org.javaboy.common.feign;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.javaboy.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@LocalTCC
public interface OrderServiceApi {
    @PostMapping("/order/create/prepare")
    @TwoPhaseBusinessAction(name = "orderServiceApi", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(@RequestBody BusinessActionContext actionContext, @RequestParam("userId") @BusinessActionContextParameter(paramName = "userId") String userId, @RequestParam("productId") @BusinessActionContextParameter(paramName = "productId") String productId, @RequestParam("count") @BusinessActionContextParameter(paramName = "count") Integer count);

    @RequestMapping("/order/create/commit")
    boolean commit(@RequestBody BusinessActionContext actionContext);

    @RequestMapping("/order/create/rollback")
    boolean rollback(@RequestBody BusinessActionContext actionContext);
}
