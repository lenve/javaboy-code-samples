package org.javaboy.data_scope.service;

import org.javaboy.data_scope.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> getAllDeps(Dept dept);
}
