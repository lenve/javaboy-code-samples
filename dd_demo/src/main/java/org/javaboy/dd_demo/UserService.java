package org.javaboy.dd_demo;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    Test08Service test08Service;

    @Autowired
    Test09Service test09Service;

    @Transactional
    @DS("master")
    public Integer count() {
        return userMapper.getCount();
    }

    @Transactional
    @DS("slave")
    public void addUser2() {
        test08Service.addUser();
        int i = 1 / 0;
        test09Service.addUser();
    }

    @Transactional
    @DS("master")
    public Integer addUser() {
        return userMapper.addUser("fengqi", 101);
    }
}
