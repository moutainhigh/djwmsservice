package com.djcps.wms.commons.base;

import java.io.Serializable;

/**
 * ORG请求参数基础类
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class BaseOrgBO extends BaseListBO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 4637005264237201929L;
    /**
     * 请求者前端用户id,如是系统级调用，请传-1
     */
    private String operator;
    /**
     * 请求者的ip地址
     */
    private String ip;
    /**
     * 调用的业务系统OA、CRM、BI、CRM、OMS、WMS、TMS
     */
    private String business;
    /**
     * 公司id
     */
    private String companyID;
    /**
     * 请求人
     */
    private String userid;
    /**
     * 组织id
     */
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOperator() {
        return operator;
    }

    public String getIp() {
        return ip;
    }

    public String getCompanyID() {
        return companyID;
    }

    public String getUserid() {
        return userid;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "BaseOrgBO [operator=" + operator + ", ip=" + ip + ", business=" + business + ", companyID=" + companyID
                + ", userid=" + userid + ", oid=" + oid + "]";
    }

}
