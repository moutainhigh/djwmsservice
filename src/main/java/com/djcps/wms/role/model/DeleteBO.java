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
     * 角色编号
     */
    private String id;
    /**
     * /** 合作方id
     */
    @NotBlank
    private String partnerId;
    /**
     * 角色类型
     */
    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeleteBO [id=" + id + ", partnerId=" + partnerId + ", roleType=" + roleType + "]";
    }

}
