package org.javaboy.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Component
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;

    @Transactional
    public void transfer3() {
        jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
        int i = 1 / 0;
    }

    public void transfer2() {
        //配置隔离性
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_NESTED);
        transactionTemplate.setReadOnly(true);
        transactionTemplate.setTimeout(3000);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
//                    int i = 1 / 0;
                } catch (DataAccessException e) {
                    //设置当前事务回滚
                    status.setRollbackOnly();
                    e.printStackTrace();
                }
            }
        });
    }

    public void transfer() {
        //1. 定义默认的事务属性
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        //2. 获取 TransactionStatus
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            jdbcTemplate.update("update user set money = ? where username=?;", 9, "zhangsan");
            int i = 1 / 0;
            //提交事务
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            e.printStackTrace();
            //回滚事务
            transactionManager.rollback(status);
        }
    }
}
