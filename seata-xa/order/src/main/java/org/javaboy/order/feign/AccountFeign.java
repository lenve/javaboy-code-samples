package org.javaboy.order.feign;

import org.apache.ibatis.annotations.Param;
import org.javaboy.common.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
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
@FeignClient("account")
public interface AccountFeign {
    @PostMapping("/deductAccount")
    RespBean deductAccount(@RequestParam("account") String account, @RequestParam("money") Double money);
}
