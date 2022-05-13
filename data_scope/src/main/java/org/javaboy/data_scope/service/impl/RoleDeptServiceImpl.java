package org.javaboy.data_scope.service.impl;

import org.javaboy.data_scope.entity.RoleDept;
import org.javaboy.data_scope.mapper.RoleDeptMapper;
import org.javaboy.data_scope.service.IRoleDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和部门关联表 服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@Service
public class RoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, RoleDept> implements IRoleDeptService {

}
