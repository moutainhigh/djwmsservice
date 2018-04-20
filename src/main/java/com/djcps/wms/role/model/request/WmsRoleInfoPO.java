package com.djcps.wms.role.model.request;

import java.io.Serializable;
import java.util.List;

/**
 * WMS角色列表信息实体类
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class WmsRoleInfoPO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6873138815646253068L;
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
     * 权限信息
     */
    private List<OrgPerssionsInfoPO> perssions;

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

    public List<OrgPerssionsInfoPO> getPerssions() {
        return perssions;
    }

    public void setPerssions(List<OrgPerssionsInfoPO> perssions) {
        this.perssions = perssions;
    }

    @Override
    public String toString() {
        return "WmsRoleInfoPO [roleId=" + roleId + ", roleTypeName=" + roleTypeName + ", roleTypeCode=" + roleTypeCode
                + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", perssions=" + perssions + "]";
    }

}
