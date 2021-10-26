package org.javaboy.batch_insert;

import org.javaboy.batch_insert.mapper.UserMapper;
import org.javaboy.batch_insert.model.User;
import org.javaboy.batch_insert.service.IUserService;
import org.javaboy.batch_insert.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BatchInsertApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(BatchInsertApplicationTests.class);
    @Autowired
    UserService userService;

    /**
     *
     * 单元测试加事务的目的是为了插入之后自动回滚，避免影响下一次测试结果
     * 一条一条插入
     */
    @Test
    @Transactional
    void addUserOneByOne() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User u = new User();
            u.setAddress("广州：" + i);
            u.setUsername("张三：" + i);
            u.setPassword("123：" + i);
            users.add(u);
        }
        userService.addUserOneByOne(users);
    }


    /**
     * mp 批量插入
     */
    @Test
    @Transactional
    void mpBatchInsert() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User u = new User();
            u.setAddress("广州：" + i);
            u.setUsername("张三：" + i);
            u.setPassword("123：" + i);
            users.add(u);
        }
        long startTime = System.currentTimeMillis();
        userService.saveBatch(users,50000);
        long endTime = System.currentTimeMillis();
        logger.info("MyBatis Plus 批量插入耗时 {}", (endTime - startTime));
    }


    /**
     * 合并成一条 SQL 插入
     */
    @Test
    @Transactional
    void addByOneSQL() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            User u = new User();
            u.setAddress("广州：" + i);
            u.setUsername("张三：" + i);
            u.setPassword("123：" + i);
            users.add(u);
        }
        userService.addByOneSQL(users);
    }

}
