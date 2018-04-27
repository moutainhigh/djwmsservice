package com.djcps.wms.inneruser.model.userparam;

import org.hibernate.validator.constraints.NotBlank;

public class OrgGetUserInfoById {

    /**
     * 合作方id
     */
    @NotBlank
    private String operator;

    /**
     * 请求者ip
     */
    @NotBlank
    private String ip;

    /**
     * 调用的业务系统WMS
     */
    @NotBlank
    private String business;

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    private String id;

    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrgGetUserInfoById{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
