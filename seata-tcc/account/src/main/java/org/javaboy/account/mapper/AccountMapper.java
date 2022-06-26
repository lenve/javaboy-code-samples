package org.javaboy.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.account.model.Account;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Mapper
public interface AccountMapper {

    @Select("select * from account_tbl where userId=#{userId}")
    Account getAccountByUserId(String userId);

    @Update("update account_tbl set freezeMoney=#{freezeMoney},money=#{money} where userId=#{userId}")
    Integer updateAccount(Account account);
}
