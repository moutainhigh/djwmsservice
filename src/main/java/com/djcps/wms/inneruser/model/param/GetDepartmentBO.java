package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取所有部门参数类
 * @author wzy
 * @date 2018/4/18
 **/
public class GetDepartmentBO {

    private String operator;


    private String ip;


    private String business;

    @NotBlank
    private String companyId;

    @NotBlank
    private String type;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GetDepartmentBO{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", companyId='" + companyId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
