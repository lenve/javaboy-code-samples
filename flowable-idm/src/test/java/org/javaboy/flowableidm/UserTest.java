package org.javaboy.flowableidm;

import org.flowable.engine.IdentityService;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@SpringBootTest
public class UserTest {

    @Autowired
    IdentityService identityService;

    @Test
    void test03() {
        identityService.createMembership("zhangsan", "leader");
        identityService.createMembership("lisi", "leader");
    }

    @Test
    void test02() {
        UserEntityImpl user1 = new UserEntityImpl();
        user1.setId("lisi");
        user1.setDisplayName("李四");
        user1.setPassword("123");
        user1.setFirstName("li");
        user1.setLastName("si");
        user1.setEmail("lisi@qq.com");
        user1.setRevision(0);
        identityService.saveUser(user1);

        UserEntityImpl user2 = new UserEntityImpl();
        user2.setId("zhangsan");
        user2.setDisplayName("张三");
        user2.setPassword("123");
        user2.setFirstName("zhang");
        user2.setLastName("san");
        user2.setEmail("zhangsan@qq.com");
        user2.setRevision(0);
        identityService.saveUser(user2);
    }

    @Test
    void test01() {
        GroupEntityImpl g = new GroupEntityImpl();
        g.setName("组长");
        g.setId("leader");
        g.setRevision(0);
        identityService.saveGroup(g);
    }
}
