package org.javaboy.flowable03;

import org.flowable.engine.IdentityService;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Flowable03ApplicationTests {

    @Autowired
    IdentityService identityService;

    @Test
    void contextLoads() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("1");
        identityService.saveUser(user);
    }

}
