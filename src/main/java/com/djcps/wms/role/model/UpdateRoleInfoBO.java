package com.djcps.wms.role.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * 更新角色信息
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class UpdateRoleInfoBO extends BaseOrgBO {

    /**
     * 
     */
    private static final long serialVersionUID = 8457599473088186595L;
    /**
     * 角色id
     */
    private String id;
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
    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    public String getRdesc() {
        return rdesc;
    }

    public String getPid() {
        return pid;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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
        return "UpdateRoleInfoBO [id=" + id + ", roleType=" + roleType + ", roleName=" + roleName + ", rdesc=" + rdesc
                + ", pid=" + pid + ", partnerId=" + partnerId + "]";
    }

}
