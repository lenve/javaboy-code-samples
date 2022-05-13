package org.javaboy.data_scope.service.impl;

import org.javaboy.data_scope.annotation.DataScope;
import org.javaboy.data_scope.entity.Role;
import org.javaboy.data_scope.mapper.RoleMapper;
import org.javaboy.data_scope.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    @DataScope(deptAlias = "sd")
    public List<Role> getAllRoles(Role role) {
        return roleMapper.getAllRoles(role);
    }
}
