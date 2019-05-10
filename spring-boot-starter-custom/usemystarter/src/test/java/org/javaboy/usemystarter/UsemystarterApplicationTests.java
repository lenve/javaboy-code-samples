package org.javaboy.usemystarter;

import org.javaboy.mystarter.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsemystarterApplicationTests {

    @Autowired
    HelloService helloService;
    @Test
    public void contextLoads() {
        System.out.println(helloService.sayHello());
    }

}
