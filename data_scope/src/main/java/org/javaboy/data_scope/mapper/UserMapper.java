package org.javaboy.data_scope.mapper;

import org.javaboy.data_scope.entity.Role;
import org.javaboy.data_scope.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
public interface UserMapper extends BaseMapper<User> {

    List<Role> getRolesByUid(Long uid);
}
