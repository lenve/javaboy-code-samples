package org.javaboy.account.service;

import org.javaboy.account.mapper.AccountMapper;
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
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public boolean deductAccount(String account, Double money) {
        accountMapper.updateAccount(account, money);
        Double m = accountMapper.getMoneyByAccount(account);
        if (m >= 0) {
            return true;
        }else{
            throw new RuntimeException("账户余额不足");
        }
    }
}
