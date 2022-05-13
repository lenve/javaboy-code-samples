package org.javaboy.data_scope.service.impl;

import org.javaboy.data_scope.entity.UserRole;
import org.javaboy.data_scope.mapper.UserRoleMapper;
import org.javaboy.data_scope.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
