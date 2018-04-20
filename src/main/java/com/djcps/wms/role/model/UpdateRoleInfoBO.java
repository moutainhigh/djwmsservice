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
    private String roleId;
    /**
     * 角色类型编码
     */
    private String roleTypeCode;
    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 权限id
     */
    private String perId;

    public String getRoleId() {
        return roleId;
    }

    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public String getPerId() {
        return perId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public void setPerId(String perId) {
        this.perId = perId;
    }

    @Override
    public String toString() {
        return "UpdateRoleInfoBO [roleId=" + roleId + ", roleTypeCode=" + roleTypeCode + ", partnerId=" + partnerId
                + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", perId=" + perId + "]";
    }

}
