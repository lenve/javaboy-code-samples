package org.javaboy.mybatis.mapper1;

import org.javaboy.mybatis.bean.User;

import java.util.List;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-05-31 16:36
 */
//@Mapper
public interface UserMapper1 {
//    @Select("select * from t_user")
    List<User> getAllUser();
}
