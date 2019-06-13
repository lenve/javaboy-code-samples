package org.javaboy.jdbcmulti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcmultiApplicationTests {

    @Resource(name = "jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;
    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;
    @Test
    public void contextLoads() {
        List<User> list = jdbcTemplateOne.query("select * from t_user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list);
        List<User> list2 = jdbcTemplateTwo.query("select * from t_user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list2);
    }

}
