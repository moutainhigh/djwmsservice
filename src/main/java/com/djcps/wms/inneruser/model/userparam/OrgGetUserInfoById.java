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
    private String bussion;

    /**
     * 用户id
     */
    @NotBlank
    private String id;

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

    public String getBussion() {
        return bussion;
    }

    public void setBussion(String bussion) {
        this.bussion = bussion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrgGetUserInfoById{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", bussion='" + bussion + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
