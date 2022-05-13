package org.javaboy.data_scope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
            "userId=" + userId +
            ", roleId=" + roleId +
        "}";
    }
}
