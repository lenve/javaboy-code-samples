package org.javaboy.jpa_method_name;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户地址查询用户
     * @param address
     * @return
     */
    List<User> getUserByAddress(String address);

    /**
     * 查询某个日期之后出生的用户
     * @param birthday
     * @return
     */
    List<User> readUserByBirthdayAfter(LocalDate birthday);

    /**
     * 查询某个日期之前出生的用户
     * @param birthday
     * @return
     */
    List<User> queryUserByBirthdayBefore(LocalDate birthday);

    /**
     * 根据性别查询用户
     * @param gender
     * @return
     */
    List<User> searchUserByGender(String gender);

    /**
     * 根据地址查询用户，返回 stream 流
     * @param address
     * @return
     */
    Stream<User> findUserByAddress(String address);

    /**
     * 统计某个地址有多少用户
     * @param address
     * @return
     */
    Long countByAddress(String address);

    /**
     * 去重统计某个地址有多少用户
     * @param address
     * @return
     */
    Long countDistinctByAddress(String address);

    /**
     * 判断某个地址是否存在用户
     * @param address
     * @return
     */
    Boolean existsUserByAddress(String address);

    /**
     * 根据地址删除用户
     * @param address
     * @return
     */
    Integer deleteUserByAddress(String address);

    /**
     * 根据地址删除用户
     * @param address
     * @return
     */
    Integer removeUserByAddress(String address);
}
