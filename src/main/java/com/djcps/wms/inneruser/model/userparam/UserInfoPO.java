package com.djcps.wms.inneruser.model.userparam;

import java.util.List;

/**
 * 用户信息标准参数
 * @author:wzy
 * @date:2018/4/18
 **/
public class UserInfoPO {
    private String phone;

    private String password;

    private String userName;

    /**
     * 入职时间
     */
    private String induction;

    /**
     * 出生年月
     */
    private String birthday;

    /**
     * 所属部门id
     */
    private String departmentId;

    /**
     * 所属部门名称
     */
    private String departmentName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * //性别  1男 ，2女
     */
    private String sex;

    /**
     * 员工状态
     */
    private String userStatus;

    /**
     * 职位
     */
    private String positionName;

    /**
     * 短号
     */
    private String shortPhone;

    /**
     * 职务
     */
    private String job;

    /**
     * 学历
     */
    private String education;

    /**
     * 婚姻状态
     */
    private String maritalStatus;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 民族
     */
    private String nation;

    /**
     * 户籍地址
     */
    private String placeOrigin;

    /**
     * 政治面貌
     */
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
     * 毕业学校
     */
    private String graduateSchool;

    /**
     * 仓库id列表
     */
    private List<String> warehouseIdList;

    public List<String> getWarehouseIdList() {
        return warehouseIdList;
    }

    public void setWarehouseIdList(List<String> warehouseIdList) {
        this.warehouseIdList = warehouseIdList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInduction() {
        return induction;
    }

    public void setInduction(String induction) {
        this.induction = induction;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getShortPhone() {
        return shortPhone;
    }

    public void setShortPhone(String shortPhone) {
        this.shortPhone = shortPhone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool;
    }

    public String getRegisteredResidence() {
        return registeredResidence;
    }

    public void setRegisteredResidence(String registeredResidence) {
        this.registeredResidence = registeredResidence;
    }

    @Override
    public String toString() {
        return "UserInfoPO{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", induction='" + induction + '\'' +
                ", birthday='" + birthday + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", positionName='" + positionName + '\'' +
                ", shortPhone='" + shortPhone + '\'' +
                ", job='" + job + '\'' +
                ", education='" + education + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", idCard='" + idCard + '\'' +
                ", nation='" + nation + '\'' +
                ", placeOrigin='" + placeOrigin + '\'' +
                ", politicalOutlook='" + politicalOutlook + '\'' +
                ", registeredResidence='" + registeredResidence + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", graduateSchool='" + graduateSchool + '\'' +
                ", warehouseIdList=" + warehouseIdList +
                '}';
    }
}
