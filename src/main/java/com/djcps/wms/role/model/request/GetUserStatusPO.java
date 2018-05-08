package com.djcps.wms.role.model.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改用户工作状态参数
 * 
 * @author:wyb
 * @date:2018/4/13
 **/
public class GetUserStatusPO {

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    /**
     * 工作状态 空闲、忙碌、休息中
     */
    private Integer workStatus;
    /**
     * 用户名称
     */
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "GetUserStatusPO [userId=" + userId + ", workStatus=" + workStatus + ", userName=" + userName + "]";
    }

}
