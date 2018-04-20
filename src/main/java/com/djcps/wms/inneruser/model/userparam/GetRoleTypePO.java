package com.djcps.wms.inneruser.model.userparam;

/**
 * 获取的角色信息
 * @author  wzy
 * @param
 * @return
 * @date  2018/4/16 15:54
 **/
public class GetRoleTypePO {
    /**
     * 角色id
     */
    private String rid;
    /**
     * 合作方id
     */
    private String partnerId;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色名称
     */
    private String roleName;

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

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "GetRoleTypePO{" +
                "rid='" + rid + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
