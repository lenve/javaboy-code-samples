package org.javaboy.account.controller;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.account.service.AccountService;
import org.javaboy.common.RespBean;
import org.javaboy.common.feign.AccountServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class AccountController implements AccountServiceApi {
    @Autowired
    AccountService accountService;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String userId, Double money) {
        return accountService.prepareDeduct(userId, money);
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return accountService.commitDeduct(actionContext);
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return accountService.rollbackDeduct(actionContext);
    }
}
