package org.javaboy.storage.controller;

import org.javaboy.common.RespBean;
import org.javaboy.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StorageController {

    @Autowired
    StorageService storageService;

    @PostMapping("/deduct")
    public RespBean deduct(@RequestParam("productId") String productId, @RequestParam("count") Integer count) {
        if (storageService.deduct(productId, count)) {
            return RespBean.ok("扣库存成功");
        }
        return RespBean.error("扣库存失败");
    }
}
