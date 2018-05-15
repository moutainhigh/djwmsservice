package com.djcps.wms.inneruser.model.userparam;

import java.util.List;

/**
 * 获取角色信息参数
 * @author wzy
 * @date 2018/4/16
 **/
public class RoleTypeBO {

    /**
     * 用户id
     */
    private String userId;

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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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
                ", rid='" + rid + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", list=" + list +
                ", typeCodeList=" + typeCodeList +
                '}';
    }
}
