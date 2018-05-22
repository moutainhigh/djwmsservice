package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 获取部门信息
 * @author
 */
public class OrgPositionBO implements Serializable {

    @NotBlank
    private String operator;

    @NotBlank
    private String ip;

    /**
     * 调用的业务系统OA、CRM、BI、CRM、OMS、WMS、TMS
     */
    @NotBlank
    private String business;

    /**
     * 部门id
     */
    @NotBlank
    private String departmentId;

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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "OrgPositionBO{" +
                "operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}
