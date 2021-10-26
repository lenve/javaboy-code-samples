package org.javaboy.spring_tran04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class UserService2 {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.NEVER)
    public void update() {
        jdbcTemplate.update("update user set money = ? where username=?;", 1000, "lisi");
//        int i = 1 / 0;
    }
}
