package org.javaboy.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoApplication.class)
public class DemoApplicationTests {

    public DemoApplicationTests() {
    }

    @Autowired
    IUserService iUserService;

    @Test
    public void contextLoads() {
        System.out.println("aaa");
    }

}
