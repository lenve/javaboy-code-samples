package org.javaboy.data_scope.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author javaboy
 * @since 2022-05-13
 */
@TableName("sys_role")
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }
    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }
    public Boolean getMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(Boolean menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }
    public Boolean getDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(Boolean deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
            "roleId=" + roleId +
            ", roleName=" + roleName +
            ", roleKey=" + roleKey +
            ", roleSort=" + roleSort +
            ", dataScope=" + dataScope +
            ", menuCheckStrictly=" + menuCheckStrictly +
            ", deptCheckStrictly=" + deptCheckStrictly +
            ", status=" + status +
            ", delFlag=" + delFlag +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
            ", remark=" + remark +
        "}";
    }
}
