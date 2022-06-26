package org.javaboy.order.feign;

import org.javaboy.common.feign.AccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

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
public interface AccountServiceApiImpl extends AccountServiceApi {
}
