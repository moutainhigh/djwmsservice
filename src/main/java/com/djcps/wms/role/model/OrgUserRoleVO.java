package com.djcps.wms.role.model;

/**
 * 用户返回类
 * @author Chengw
 * @create 2018/5/8 15:18.
 * @since 1.0.0
 */
public class OrgUserRoleVO {

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * id
     */
    private String roleId;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "OrgUserRoleVO{" +
                "roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
