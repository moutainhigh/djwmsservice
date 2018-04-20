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
        return "DeleteBO [roleId=" + roleId + ", partnerId=" + partnerId + "]";
    }

}
