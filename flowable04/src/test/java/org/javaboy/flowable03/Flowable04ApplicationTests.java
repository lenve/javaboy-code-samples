package org.javaboy.flowable03;

import org.flowable.engine.IdentityService;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class Flowable04ApplicationTests {

    @Autowired
    IdentityService identityService;

    @Test
    void contextLoads() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("1");
        identityService.saveUser(user);
    }

}
