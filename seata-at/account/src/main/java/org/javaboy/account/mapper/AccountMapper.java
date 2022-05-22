package org.javaboy.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Update("update account_tbl set money=money-#{money} where user_id=#{account}")
    int updateAccount(@Param("account") String account, @Param("money") Double money);

    @Select("select money from account_tbl where user_id=#{account}")
    Double getMoneyByAccount(String account);
}
