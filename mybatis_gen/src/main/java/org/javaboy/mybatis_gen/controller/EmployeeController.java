package org.javaboy.mybatis_gen.controller;

import org.javaboy.mybatis_gen.entity.Employee;
import org.javaboy.mybatis_gen.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author javaboy
 * @since 2022-04-13
 */
@RestController
public class EmployeeController {

    @Autowired
    IEmployeeService iEmployeeService;

    @GetMapping("/emps")
    public List<Employee> getAllEmps() {
        List<Employee> list = iEmployeeService.list();
        return list;
    }
}
