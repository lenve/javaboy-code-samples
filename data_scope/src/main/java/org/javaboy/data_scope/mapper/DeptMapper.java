package org.javaboy.data_scope.mapper;

import org.javaboy.data_scope.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> getAllDeps(Dept dept);
}
