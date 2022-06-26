package org.javaboy.storage.mapper;

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
//@Mapper
public interface StorageMapper {
    @Update("update storage_tbl set count=count-#{count} where commodity_code=#{productId}")
    int deduct(@Param("productId") String productId, @Param("count") Integer count);

    @Select("select count from storage_tbl where commodity_code=#{productId}")
    int getCountByProductId(String productId);
}
