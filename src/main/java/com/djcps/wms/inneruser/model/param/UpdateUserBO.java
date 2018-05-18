package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 修改用户信息参数
 * @author wzy
 * @date 2018/4/13
 **/
public class UpdateUserBO implements Serializable{

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    /**
     * 工作状态 空闲、忙碌、休息中
     */
    private Integer workStatus ;

    private String partnerId;

    /**
     * 用户名称
     */
    private String userName ;

    /**
     * 最后登录时间
     */
    private String lastLoginTime ;

    /**
     * 登录次数
     */
    private Integer loginCount ;

    private String warehouseId;

    /**
     * 仓库id集合
     */
    private List<String> warehouseIds;

    /**
     * 角色类型
     */
    private String roleType  ;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public List<String> getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(List<String> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    @Override
    public String toString() {
        return "UpdateUserBO{" +
                "userId='" + userId + '\'' +
                ", workStatus=" + workStatus +
                ", partnerId='" + partnerId + '\'' +
                ", userName='" + userName + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", loginCount='" + loginCount + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
