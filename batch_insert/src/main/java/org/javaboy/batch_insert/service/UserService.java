package org.javaboy.batch_insert.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.javaboy.batch_insert.mapper.UserMapper;
import org.javaboy.batch_insert.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

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
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    /**
     * 一条一条插入
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserOneByOne(List<User> users) {
        long startTime = System.currentTimeMillis();
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserMapper um = session.getMapper(UserMapper.class);
        for (User user : users) {
            um.addUserOneByOne(user);
        }
        session.flushStatements();
        long endTime = System.currentTimeMillis();
        logger.info("一条条插入 SQL 耗费时间 {}", (endTime - startTime));
    }

    /**
     * 合并成一条 SQL 插入
     * @param users
     */
    @Transactional(rollbackFor = Exception.class)
    public void addByOneSQL(List<User> users) {
        long startTime = System.currentTimeMillis();
        userMapper.addByOneSQL(users);
        long endTime = System.currentTimeMillis();
        logger.info("合并成一条 SQL 插入耗费时间 {}", (endTime - startTime));
    }
}
