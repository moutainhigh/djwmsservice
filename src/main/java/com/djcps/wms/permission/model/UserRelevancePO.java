package com.djcps.wms.permission.model;

import java.io.Serializable;

/**
 * 用户关联信息表
 * @author:zhq
 * @date:2018/4/12
 **/
public class UserRelevancePO implements Serializable{
	private static final long serialVersionUID = -8308578112971409035L;

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
     * 工作状态 空闲、忙碌、休息中
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

    private String warehouseId;

    private String warehouseName;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    @Override
    public String toString() {
        return "UserRelevancePO{" +
                "userId='" + userId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", userName='" + userName + '\'' +
                ", workStatus=" + workStatus +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", loginCount='" + loginCount + '\'' +
                ", roleType='" + roleType + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}
