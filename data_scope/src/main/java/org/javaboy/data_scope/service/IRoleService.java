package org.javaboy.data_scope.service;

import org.javaboy.data_scope.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
public interface IRoleService extends IService<Role> {

    List<Role> getAllRoles(Role role);
}
