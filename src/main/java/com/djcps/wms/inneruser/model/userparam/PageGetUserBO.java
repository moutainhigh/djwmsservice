package com.djcps.wms.inneruser.model.userparam;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 条件获取用户列表
 * @author wzy
 * @date 2018/4/13
 **/
public class PageGetUserBO {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 工作状态 空闲、忙碌、休息中
     */
    private Integer workStatus ;

    /**
     * 当前操作者
     */
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
    private String business;

    private String pageSize;

    private String pageNo;

    private String partnerId;

    private String roleType;

    private String userName;

    private String roleTypeCode;


    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

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

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "PageGetUserBO{" +
                "userId='" + userId + '\'' +
                ", workStatus=" + workStatus +
                ", operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", business='" + business + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageNo='" + pageNo + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", roleType='" + roleType + '\'' +
                ", userName='" + userName + '\'' +
                ", roleTypeCode='" + roleTypeCode + '\'' +
                '}';
    }
}
