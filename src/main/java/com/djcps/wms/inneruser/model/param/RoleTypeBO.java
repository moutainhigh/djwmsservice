package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;
import java.util.List;

/**
 * 获取角色信息参数
 * @author wzy
 * @date 2018/4/16
 **/
public class RoleTypeBO implements Serializable{

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色类型code
     */
    private String roleTypeCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 合作方id
     */
    private String partnerId;
    /**
     * 角色id集合
     */
    private List<String> list;

    /**
     * 角色类型id集合
     */
    private List<String> typeCodeList;

    public List<String> getTypeCodeList() {
        return typeCodeList;
    }

    public void setTypeCodeList(List<String> typeCodeList) {
        this.typeCodeList = typeCodeList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "RoleTypeBO{" +
                "userId='" + userId + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleTypeCode='" + roleTypeCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", list=" + list +
                ", typeCodeList=" + typeCodeList +
                '}';
    }
}
