package org.javaboy.data_scope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 角色和部门关联表
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@TableName("sys_role_dept")
public class RoleDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "RoleDept{" +
            "roleId=" + roleId +
            ", deptId=" + deptId +
        "}";
    }
}
