package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;
import java.util.List;

/**
 *wms用户关联信息参数
 * @author wzy
 * @date 2018/4/12
 **/
public class UserRelevanceBO implements Serializable{

    /**
     * 用户id
     */
    private String userId;

    private String partnerId;

    /**
     * 用户名称
     */
    private String userName ;

    /**
     * 工作状态
     */
    private Integer workStatus ;

    /**
     * 最后登录时间
     */
    private String lastLoginTime ;

    /**
     * 登录次数
     */
    private String loginCount ;

    /**
     * 角色类型
     */
    private String roleType  ;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 联系方式
     */
    private String  contactWay;

    /**
     * 所属仓库id集合
     */
    private List<String> warehouseIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public String getUserId() {
        return userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }


    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public List<String> getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(List<String> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    @Override
    public String toString() {
        return "UserRelevanceBO{" +
                "userId='" + userId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", userName='" + userName + '\'' +
                ", workStatus=" + workStatus +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", loginCount='" + loginCount + '\'' +
                ", roleType='" + roleType + '\'' +
                ", roleId='" + roleId + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", warehouseIds=" + warehouseIds +
                '}';
    }
}
