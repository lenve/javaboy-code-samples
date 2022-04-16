package org.javaboy.mybatis_gen.service.impl;

import org.javaboy.mybatis_gen.entity.Employee;
import org.javaboy.mybatis_gen.mapper.EmployeeMapper;
import org.javaboy.mybatis_gen.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author javaboy
 * @since 2022-04-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
