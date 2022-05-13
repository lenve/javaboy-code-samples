package org.javaboy.data_scope.controller;

import org.javaboy.data_scope.entity.Role;
import org.javaboy.data_scope.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService iRoleService;

    @GetMapping("/")
    public List<Role> getAllRoles(Role role) {
        return iRoleService.getAllRoles(role);
    }
}
