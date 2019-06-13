package org.javaboy.mybatis;

import org.javaboy.mybatis.mapper1.UserMapper1;
import org.javaboy.mybatis.mapper2.UserMapper2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    @Autowired
    UserMapper1 userMapper1;
    @Autowired
    UserMapper2 userMapper2;
    @Test
    public void contextLoads() {
        System.out.println(userMapper1.getAllUser());
        System.out.println(userMapper2.getAllUser());
    }

}
