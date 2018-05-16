package com.djcps.wms.inneruser.model.result;

/**
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 14:32.
 */
public class OrgUserInfoVO {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 头像
     */
    private String userImage;

    /**
     * 手机
     */
    private String userPhone;

    /**
     * 部门
     */
    private String department;

    /**
     * 全拼
     */
    private String wholeSpell;

    /**
     * 职务
     */
    private String job;

    /**
     * 首字母
     */
    private String firstSpell;

    /**
     * 职位
     */
    private String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getWholeSpell() {
        return wholeSpell;
    }

    public void setWholeSpell(String wholeSpell) {
        this.wholeSpell = wholeSpell;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFirstSpell() {
        return firstSpell;
    }

    public void setFirstSpell(String firstSpell) {
        this.firstSpell = firstSpell;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "OrgUserInfoVO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", department='" + department + '\'' +
                ", wholeSpell='" + wholeSpell + '\'' +
                ", job='" + job + '\'' +
                ", firstSpell='" + firstSpell + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
