package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;

/**
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/18 13:34.
 */
public class UpdateUserStatusBO implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 工作状态 空闲、忙碌、休息中
     */
    private Integer workStatus;

    /**
     * 合作方id
     */
    private String partnerId;

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

    @Override
    public String toString() {
        return "UpdateUserStatusBO{" +
                "userId='" + userId + '\'' +
                ", workStatus=" + workStatus +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
