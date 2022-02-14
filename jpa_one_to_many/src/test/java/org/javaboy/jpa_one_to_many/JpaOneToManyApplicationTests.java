package org.javaboy.jpa_one_to_many;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JpaOneToManyApplicationTests {

    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    ClazzRepository clazzRepository;

    @Test
    void test03() {
        List<Clazz> list = clazzRepository.findAll();
        System.out.println("list = " + list);
    }

    @Test
    void test02() {
        Clazz c = new Clazz();
        c.setCid(1);
        c.setName("三年级二班");
        List<Student> students = new ArrayList<>();
        Student s1 = new Student();
        s1.setSid(1);
        s1.setName("javaboy");
        students.add(s1);
        Student s2 = new Student();
        s2.setSid(2);
        s2.setName("张三");
        students.add(s2);
        c.setStudents(students);
        clazzRepository.save(c);
    }

    @Test
    void test01() {
        List<School> list = schoolRepository.findSchoolByAddress_Province("黑龙江");
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
