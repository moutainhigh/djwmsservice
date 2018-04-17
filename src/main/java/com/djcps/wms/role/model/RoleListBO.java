package com.djcps.wms.role.model;


import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * 获取角色列表信息参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class RoleListBO extends BaseOrgBO {

    /**
     * 
     */
    private static final long serialVersionUID = 3313869523907335726L;
    /**
     * 角色id
     */
    private String rid;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * /** 合作方id
     */
    @NotBlank
    private String partnerId;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPartnerId() {
        return partnerId;
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
        return "RoleListBO [rid=" + rid + ", roleType=" + roleType + ", roleName=" + roleName + ", partnerId="
                + partnerId + "]";
    }

}
