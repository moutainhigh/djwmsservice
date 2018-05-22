package com.djcps.wms.inneruser.model.param;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * wms专用的用户信息保存参数
 * @author wzy
 * @date 2018/4/18
 **/
public class WmsSaveUserBO implements Serializable{

    private String operator;

    /**
     * 调用的业务系统OA、CRM、BI、CRM、OMS、WMS、TMS
     */
    private String business;

    /**
     * 请求者的ip地址
     */
    private String ip;

    private String partnerId;

    /**
     * 所属仓库id
     */
    private List<String> warehouseIds;

    private String warehouseName;

    private String phone;
    /**
     * 角色类型
     */
    private String roleType;

    @NotBlank
    private String userName;

    private String userId;

    private String departmentId;
    private String departmentName;
    private String positionId;
    private String positionName;
    private String job;
    private String shortPhone;
    private String idCard;
    private String nation;
    private String email;
    private String education;
    private String maritalStatus;
    private String politicalOutlook;

    /**
     * 户籍地址
     */
    private String registeredResidence;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 在线员工
     */
    private String onlineUserId;

    /**
     * 员工状态 试用员工/正式员工
     */
    @NotBlank
    private String userStatus;

    /**
     * 入职时间
     */
    private String induction;

    /**
     * 毕业学校
     */
    private String graduatSchool;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 权限类型
     */
    private String roleIds;

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(String onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInduction() {
        return induction;
    }

    public void setInduction(String induction) {
        this.induction = induction;
    }

    public String getGraduatSchool() {
        return graduatSchool;
    }

    public void setGraduatSchool(String graduatSchool) {
        this.graduatSchool = graduatSchool;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getShortPhone() {
        return shortPhone;
    }

    public void setShortPhone(String shortPhone) {
        this.shortPhone = shortPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public List<String> getWarehouseIds() {
        return warehouseIds;
    }

    public void setWarehouseIds(List<String> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    @Override
    public String toString() {
        return "WmsSaveUserBO{" +
                "operator='" + operator + '\'' +
                ", business='" + business + '\'' +
                ", ip='" + ip + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseIds=" + warehouseIds +
                ", warehouseName='" + warehouseName + '\'' +
                ", phone='" + phone + '\'' +
                ", roleType='" + roleType + '\'' +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", job='" + job + '\'' +
                ", shortPhone='" + shortPhone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", nation='" + nation + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", politicalOutlook='" + politicalOutlook + '\'' +
                ", registeredResidence='" + registeredResidence + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", onlineUserId='" + onlineUserId + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", induction='" + induction + '\'' +
                ", graduatSchool='" + graduatSchool + '\'' +
                ", companyId='" + companyId + '\'' +
                ", roleIds='" + roleIds + '\'' +
                '}';
    }
}
