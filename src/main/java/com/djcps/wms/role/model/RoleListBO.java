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
    private String roleId;
    /**
     * 角色类型编码
     */
    private String roleTypeCode;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 公司id
     */
    private String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public String toString() {
        return "RoleListBO [roleId=" + roleId + ", roleTypeCode=" + roleTypeCode + ", roleName=" + roleName
                + ", partnerId=" + partnerId + ", companyId=" + companyId + "]";
    }

}
