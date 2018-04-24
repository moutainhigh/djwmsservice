package com.djcps.wms.role.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * 新增角色信息参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class SaveBO extends BaseOrgBO {

    /**
     * 
     */
    private static final long serialVersionUID = 696280916702489605L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色类型名
     */
    private String roleTypeName;
    /**
     * 角色类型编码
     */
    private String roleTypeCode;
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
    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;

    public String getRoleId() {
        return roleId;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public String getRoleTypeCode() {
        return roleTypeCode;
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

    public String getPartnerId() {
        return partnerId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
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

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "SaveBO [roleId=" + roleId + ", roleTypeName=" + roleTypeName + ", roleTypeCode=" + roleTypeCode
                + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", perId=" + perId + ", partnerId=" + partnerId
                + "]";
    }

}
