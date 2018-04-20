package com.djcps.wms.inneruser.model.userparam;

import org.hibernate.validator.constraints.NotBlank;

/**
 * org删除用户参数类
 * @author:wzy
 * @date:2018/4/13
 **/
public class OrgDeleteUserBO {

    /**
     * 给org的用户id
     */
    @NotBlank
    private String userId;

    /**
     * org用户状态 0启用，1禁用
     */
    private Integer status;

    private String operator;

    @NotBlank
    private String ip;

    @NotBlank
    private String bussion;

    private String partnerId;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getBussion() {
        return bussion;
    }

    public void setBussion(String bussion) {
        this.bussion = bussion;
    }

    @Override
    public String toString() {
        return "OrgDeleteUserBO{" +
                "userId='" + userId + '\'' +
                ", status=" + status +
                ", operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", bussion='" + bussion + '\'' +
                ", partnerId='" + partnerId + '\'' +
                '}';
    }
}
