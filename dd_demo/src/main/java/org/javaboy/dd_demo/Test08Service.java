package org.javaboy.dd_demo;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class Test08Service {

    @Autowired
    UserMapper userMapper;
//    @DS("master")
    public void addUser() {
        userMapper.addUser("maba22", 9999);
    }
}
