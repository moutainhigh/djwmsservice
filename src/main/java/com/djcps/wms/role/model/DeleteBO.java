package com.djcps.wms.role.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseOrgBO;

/**
 * 删除角色信息信息参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class DeleteBO extends BaseOrgBO {

    /**
     * 
     */
    private static final long serialVersionUID = -3717534663639108351L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * /** 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 公司id
     */
    private String companyId;
    /**
     * 请求人
     */
    private String userId;
    /**
     * 角色类型编码
     */
    private String roleTypeCode;

    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "DeleteBO [roleId=" + roleId + ", partnerId=" + partnerId + ", companyId=" + companyId + ", userId="
                + userId + ", roleTypeCode=" + roleTypeCode + "]";
    }

}
