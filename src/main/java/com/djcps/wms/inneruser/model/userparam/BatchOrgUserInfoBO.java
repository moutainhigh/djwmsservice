package com.djcps.wms.inneruser.model.userparam;

/**
 * 批量获取用户信息(包括已删除的)
 * @author:wzy
 * @date:2018/4/12
 **/
public class BatchOrgUserInfoBO {

    /**
     * 合作方id
     */
    private String operator;

    /**
     * 请求者ip
     */
    private String ip;

    /**
     * 调用的业务系统WMS
     */
    private String business;

    /**
     * 逗号隔开的用户id
     */
    private String userids;

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

    public String getUserids() {
        return userids;
    }

    public void setUserids(String userids) {
        this.userids = userids;
    }

    @Override
    public String toString() {
        return "BatchOrgUserInfoBO{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", userids='" + userids + '\'' +
                '}';
    }
}
