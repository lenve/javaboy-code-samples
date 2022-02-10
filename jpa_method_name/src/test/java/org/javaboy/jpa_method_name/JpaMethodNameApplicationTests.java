package org.javaboy.jpa_method_name;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class JpaMethodNameApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional(readOnly = true)
    void test01() {
        List<User> list = userRepository.findUserByAddress("深圳").map(u -> {
            u.setAddress("中国 " + u.getAddress());
            return u;
        }).collect(Collectors.toList());
        System.out.println("list = " + list);
    }

    @Test
    @Transactional
    @Rollback(false)
    void contextLoads() {
        System.out.println("userRepository.findUserByUsername(\"javaboy\") = " + userRepository.findUserByUsername("javaboy"));
        System.out.println("userRepository.getUserByAddress(\"深圳\") = " + userRepository.getUserByAddress("深圳"));
        System.out.println("userRepository.queryUserByBirthdayBefore(LocalDate.of(2001, 1, 1)) = " + userRepository.queryUserByBirthdayBefore(LocalDate.of(2001, 1, 1)));
        System.out.println("userRepository.readUserByBirthdayAfter(LocalDate.of(2000,1,1)) = " + userRepository.readUserByBirthdayAfter(LocalDate.of(2000, 1, 1)));
        System.out.println("userRepository.searchUserByGender(\"男\") = " + userRepository.searchUserByGender("男"));
        System.out.println("userRepository.countByAddress(\"深圳\") = " + userRepository.countByAddress("深圳"));
        System.out.println("userRepository.countDistinctByAddress(\"深圳\") = " + userRepository.countDistinctByAddress("深圳"));
        System.out.println("userRepository.existsUserByAddress(\"深圳\") = " + userRepository.existsUserByAddress("深圳"));
        System.out.println("userRepository.existsUserByAddress(\"北京\") = " + userRepository.existsUserByAddress("北京"));

        System.out.println("userRepository.deleteUserByAddress(\"深圳\") = " + userRepository.deleteUserByAddress("深圳"));
        System.out.println("userRepository.removeUserByAddress(\"广州\") = " + userRepository.removeUserByAddress("广州"));
    }

}
