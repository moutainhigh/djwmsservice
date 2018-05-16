package com.djcps.wms.inneruser.model.param;

/**
 * 获取org 用户信息
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 13:20.
 */
public class OrgUserRoleBO {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 公司id
     */
    private String companyID;

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 操作人  系统 -1
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    @Override
    public String toString() {
        return "OrgUserRoleBO{" +
                "roleId='" + roleId + '\'' +
                ", companyID='" + companyID + '\'' +
                ", keyword='" + keyword + '\'' +
                ", operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                '}';
    }
}
