package com.djcps.wms.role.model.request;

/**
 * 角色列表信息实体类
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class RoleListPO {
    /**
     * 角色id
     */
    private String rid;
    /**
     * 合作方id
     */
    private String partnerId;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String rdesc;
    /**
     * 权限id
     */
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRoleType() {
        return roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleListPO [rid=" + rid + ", partnerId=" + partnerId + ", roleType=" + roleType + ", roleName="
                + roleName + ", rdesc=" + rdesc + ", pid=" + pid + "]";
    }

}
