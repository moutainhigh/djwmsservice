package com.djcps.wms.inneruser.model.userparam;

/**
 * 获取公司所有的部门和职务参数类
 * @author:wzy
 * @date:2018/4/13
 **/
public class OrgGetDepartmentBO {


    private String operator;


    private String ip;


    private String bussion;


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

    public String getBussion() {
        return bussion;
    }

    @Override
    public String toString() {
        return "OrgGetDepartmentBO{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", bussion='" + bussion + '\'' +
                ", companyID='" + companyID + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public void setBussion(String bussion) {
        this.bussion = bussion;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
