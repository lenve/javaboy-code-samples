package org.javaboy.data_scope.service.impl;

import org.javaboy.data_scope.annotation.DataScope;
import org.javaboy.data_scope.entity.Dept;
import org.javaboy.data_scope.mapper.DeptMapper;
import org.javaboy.data_scope.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    @DataScope(deptAlias = "d")
    public List<Dept> getAllDeps(Dept dept) {
        return deptMapper.getAllDeps(dept);
    }
}
