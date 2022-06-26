package org.javaboy.storage.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import org.javaboy.common.RespBean;
import org.javaboy.common.feign.StorageServiceApi;
import org.javaboy.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class StorageController implements StorageServiceApi {

    @Autowired
    StorageService storageService;

    @Override
    public boolean deduct(BusinessActionContext actionContext, String productId, Integer count) {
        return storageService.prepareDeduct(productId, count);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return storageService.commitDeduct(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return storageService.rollbackDeduct(actionContext);
    }
}
