package com.djcps.wms.inneruser.model.result;

import java.util.List;

/**
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/16 10:22.
 */
public class UserRelevancePO {

    /**
     * 用户id
     */
    private String userId;

    private String partnerId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 工作状态
     */
    private Integer workStatus;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 登录次数
     */
    private String loginCount;

    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 所属部门
     */
    private String department;


    /**
     * 所属仓库
     */
    private String warehouse;

    /**
     * 联系方式
     */
    private String contactWay;

    private String warehouseId;

    private String warehouseName;

    private List<String> warehouseNameList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getWarehouseNameList() {
        return warehouseNameList;
    }

    public void setWarehouseNameList(List<String> warehouseNameList) {
        this.warehouseNameList = warehouseNameList;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
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
                ", department='" + department + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseNameList=" + warehouseNameList +
                '}';
    }
}
