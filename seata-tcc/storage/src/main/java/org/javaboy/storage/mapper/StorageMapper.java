package org.javaboy.storage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.javaboy.storage.model.Storage;

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
public interface StorageMapper {

    @Update("update storage_tbl set freezeCount=#{freezeCount},count=#{count} where productId=#{productId}")
    int updateStorage(Storage storage);

    @Select("select * from storage_tbl where productId=#{productId}")
    Storage getStorageByProductId(String productId);
}
