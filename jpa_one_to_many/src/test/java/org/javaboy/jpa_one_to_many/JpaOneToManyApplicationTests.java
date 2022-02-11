package org.javaboy.jpa_one_to_many;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaOneToManyApplicationTests {

    @Autowired
    SchoolRepository schoolRepository;

    @Test
    void test01() {
        List<School> list = schoolRepository.findSchoolByAddressProvince("黑龙江");
        System.out.println("list = " + list);
    }

    @Test
    void contextLoads() {
        School school = new School();
        school.setSid(1);
        school.setName("哈佛大学");
        Address address = new Address();
        address.setAid(1);
        address.setProvince("黑龙江");
        address.setCity("哈尔滨");
        address.setArea("某地");
        address.setPhone("123456");
        school.setAddress(address);
        schoolRepository.save(school);
    }

}
