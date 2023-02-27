package org.javaboy.flowableidm;

import org.flowable.common.engine.api.management.TableMetaData;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.IdmManagementService;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.*;
import org.flowable.spring.security.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class FlowableIdmApplicationTests {

    @Autowired
    IdentityService identityService;

    @Autowired
    IdmManagementService idmManagementService;
    @Test
    void contextLoads() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
        user.setDisplayName("江南一点雨");
        user.setPassword("123");
        user.setFirstName("java");
        user.setLastName("boy");
        user.setEmail("javaboy@qq.com");
        user.setRevision(0);
        identityService.saveUser(user);
    }

    @Test
    void test02() {
        UserEntityImpl user = new UserEntityImpl();
        user.setId("javaboy");
        user.setPassword("666");
        user.setEmail("111@qq.com");
        user.setRevision(2);
        identityService.updateUserPassword(user);
    }

    @Test
    void test03() {
        User u = identityService.createUserQuery().userId("javaboy").singleResult();
        u.setDisplayName("itboy");
        identityService.updateUserPassword(u);
    }
    @Test
    void test04() {
        User u = identityService.createUserQuery().userId("javaboy").singleResult();
        u.setDisplayName("itboy");
        u.setPassword("888");
        identityService.saveUser(u);
    }

    @Test
    void test05() {
        identityService.deleteUser("javaboy");

    }

    @Test
    void test06() {
        List<User> list = identityService.createUserQuery().userDisplayNameLike("张%").orderByUserId().asc().list();
        for (User user : list) {
            System.out.println("user.getDisplayName() = " + user.getDisplayName());
        }

    }

    @Test
    void test07() {
        List<User> list = identityService.createUserQuery().list();
        for (User user : list) {
            System.out.println("user.getId() = " + user.getId());
        }

    }

    @Test
    void test08() {
        List<User> list = identityService.createNativeUserQuery().sql("select * from "+idmManagementService.getTableName(User.class)+" where DISPLAY_NAME_ like #{name}").parameter("name", "李%").list();
        for (User user : list) {
            System.out.println("user.getId() = " + user.getId());
        }

    }

    @Test
    void test09() {
        GroupEntityImpl g = new GroupEntityImpl();
        g.setName("组长");
        g.setId("leader");
        g.setRevision(0);
        identityService.saveGroup(g);
    }

    @Test
    void test10() {
        identityService.createMembership("zhangsan", "leader");
        identityService.createMembership("lisi", "leader");
    }

    @Test
    void test11() {
        identityService.deleteMembership("zhangsan","leader");
    }

    @Test
    void test12() {
        Group g = identityService.createGroupQuery().groupId("leader").singleResult();
        g.setName("主管");
        identityService.saveGroup(g);
    }

    @Test
    void test13() {
        identityService.deleteGroup("leader");
    }

    @Test
    void test14() {
        //根据 id 查询组信息
        Group g1 = identityService.createGroupQuery().groupId("leader").singleResult();
        System.out.println("g1.getName() = " + g1.getName());
        //根据 name 查询组信息
        Group g2 = identityService.createGroupQuery().groupName("组长").singleResult();
        System.out.println("g2.getId() = " + g2.getId());
        //根据用户查询组信息（组里包含该用户）
        List<Group> list = identityService.createGroupQuery().groupMember("zhangsan").list();
        for (Group group : list) {
            System.out.println("group.getName() = " + group.getName());
        }
    }

    @Test
    void test15() {
        //获取系统信息
        Map<String, String> properties = idmManagementService.getProperties();
        System.out.println("properties = " + properties);
        //获取表的详细信息
        TableMetaData tableMetaData = idmManagementService.getTableMetaData(idmManagementService.getTableName(User.class));
        //获取表名
        System.out.println("tableMetaData.getTableName() = " + tableMetaData.getTableName());
        //获取列名
        System.out.println("tableMetaData.getColumnNames() = " + tableMetaData.getColumnNames());
        //获取列的类型
        System.out.println("tableMetaData.getColumnTypes() = " + tableMetaData.getColumnTypes());
    }

}
