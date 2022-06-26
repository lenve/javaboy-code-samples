package org.javaboy.account.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.javaboy.account.model.Account;
import org.javaboy.account.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountMapper accountMapper;

    /**
     * 预扣款阶段
     * 检查账户余额
     *
     * @param userId
     * @param money
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareDeduct(String userId, Double money) {
        Account account = accountMapper.getAccountByUserId(userId);
        if (account == null) {
            throw new RuntimeException("账户不存在");
        }
        if (account.getMoney() < money) {
            throw new RuntimeException("余额不足，预扣款失败");
        }
        account.setFreezeMoney(account.getFreezeMoney() + money);
        account.setMoney(account.getMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        logger.info("{} 账户预扣款 {} 元", userId, money);
        return i == 1;
    }

    /**
     * 实际扣款阶段
     *
     * @param actionContext
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean commitDeduct(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        Double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() < money) {
            throw new RuntimeException("余额不足，扣款失败");
        }
        account.setFreezeMoney(account.getFreezeMoney() - money);
        Integer i = accountMapper.updateAccount(account);
        logger.info("{} 账户扣款 {} 元", userId, money);
        return i == 1;
    }

    /**
     * 账户回滚阶段
     *
     * @param actionContext
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean rollbackDeduct(BusinessActionContext actionContext) {
        String userId = (String) actionContext.getActionContext("userId");
        Double money = ((BigDecimal) actionContext.getActionContext("money")).doubleValue();
        Account account = accountMapper.getAccountByUserId(userId);
        if (account.getFreezeMoney() >= money) {
            account.setMoney(account.getMoney() + money);
            account.setFreezeMoney(account.getFreezeMoney() - money);
            Integer i = accountMapper.updateAccount(account);
            logger.info("{} 账户释放冻结金额 {} 元", userId, money);
            return i == 1;
        }
        logger.info("{} 账户资金已释放",userId);
        //说明prepare中抛出异常，未冻结资金
        return true;
    }
}