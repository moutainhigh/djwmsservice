package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;

/**
 * 获取公司所有的部门和职务参数类
 * @author wzy
 * @date 2018/4/13
 **/
public class OrgGetDepartmentBO implements Serializable{


    private String operator;


    private String ip;


    private String business;


    private String companyID;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    @Override
    public String toString() {
        return "OrgGetDepartmentBO{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", companyID='" + companyID + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
