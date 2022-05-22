package org.javaboy.data_scope.aspect;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.javaboy.data_scope.annotation.DataScope;
import org.javaboy.data_scope.entity.BaseEntity;
import org.javaboy.data_scope.entity.Role;
import org.javaboy.data_scope.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.SQLPermission;
import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Aspect
@Component
public class DataScopeAspect {

    @Before("@annotation(dataScope)")
    public void before(JoinPoint jp, DataScope dataScope) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> roles = user.getRoles();
        StringBuffer sql = new StringBuffer();
        for (Role role : roles) {
            if (role.getDataScope().equals("1")) {
            } else if (role.getDataScope().equals("2")) {
                sql.append(String.format(" and %s.dept_id in(select dept_id from sys_role_dept srd where srd.role_id=%d)", dataScope.deptAlias(), role.getRoleId()));
            } else if (role.getDataScope().equals("3")) {
                sql.append(String.format(" and %s.dept_id=%d", dataScope.deptAlias(), user.getDeptId()));
            } else if (role.getDataScope().equals("4")) {
                sql.append(String.format(" and %s.dept_id in(select dept_id from sys_dept where sys_dept.dept_id=%d or find_in_set(%d,ancestors))", dataScope.deptAlias(), user.getDeptId(), user.getDeptId()));
            } else if (role.getDataScope().equals("5")) {
                if (!dataScope.userAlias().equals("")) {
                    sql.append(String.format(" and %s.user_id=%d", dataScope.userAlias(), user.getDeptId()));
                } else {
                    sql.append(" and 1=0");
                }
            }
        }
        BaseEntity arg = (BaseEntity) jp.getArgs()[0];
        arg.getParams().put("data_scope", sql.toString());
    }
}
