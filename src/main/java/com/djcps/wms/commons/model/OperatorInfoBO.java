package com.djcps.wms.commons.model;

/**
 * 操作人信息
 * 
 * @author Chengw
 * @create 2018/4/17 10:18.
 * @since 1.0.0
 */
public class OperatorInfoBO {

    /**
     * 操作人
     */
    private String operator;

    /**
     * ip
     */
    private String ip;

    /**
     * 业务
     */
    private String business;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
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

    @Override
    public String toString() {
        return "OperatorInfoBO [operator=" + operator + ", ip=" + ip + ", business=" + business + "]";
    }

}
