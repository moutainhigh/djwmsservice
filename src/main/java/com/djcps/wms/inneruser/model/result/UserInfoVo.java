package com.djcps.wms.inneruser.model.result;

/**
 * 内部用户信息
 * @author Chengw
 * @since 2017/12/4 16:09.
 */
public class UserInfoVo{

    /**
     * 用户id，可用与用户权限
     */
    private int id;
    /**
     * 用户邮箱
     */
    private String uemail;
    /**
     * 用户账号名称
     */
    private String uname;
    /**
     * 工号
     */
    private String uids;

    /**
     * 密码
     */
    private String password;
    /**
     * 平台名称
     */
    private String appname;
    /**
     * 邮箱
     */
    private String email;
    /**
     *部门
     */
    private String department;
    /**
     * 部门id
     */
    private int departmentId;
    /**
     * ip
     */
    private String ip;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别
     */
    private int sex;
    /**
     * 公司ID
     */
    private String ucompany;

    /**
     * 公司名称
     */
    private String oname;

    /**
     * 区域Code
     */
    private String ocode;

    /**
     * 省
     */
    private String oprovince;
    /**
     * 市
     */
    private String ocity;

    /**
     * 区
     */
    private String oarea;

    /**
     * 获取 用户id，可用与用户权限
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 用户id，可用与用户权限
     */
    public UserInfoVo setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 用户邮箱
     */
    public String getUemail() {
        return this.uemail;
    }

    /**
     * 设置 用户邮箱
     */
    public UserInfoVo setUemail(String uemail) {
        this.uemail = uemail;
        return this;
    }

    /**
     * 获取 用户账号名称
     */
    public String getUname() {
        return this.uname;
    }

    /**
     * 设置 用户账号名称
     */
    public UserInfoVo setUname(String uname) {
        this.uname = uname;
        return this;
    }

    /**
     * 获取 工号
     */
    public String getUids() {
        return this.uids;
    }

    /**
     * 设置 工号
     */
    public UserInfoVo setUids(String uids) {
        this.uids = uids;
        return this;
    }

    /**
     * 获取 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置 密码
     */
    public UserInfoVo setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * 获取 平台名称
     */
    public String getAppname() {
        return this.appname;
    }

    /**
     * 设置 平台名称
     */
    public UserInfoVo setAppname(String appname) {
        this.appname = appname;
        return this;
    }

    /**
     * 获取 邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 邮箱
     */
    public UserInfoVo setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     *部门
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     *部门
     */
    public UserInfoVo setDepartment(String department) {
        this.department = department;
        return this;
    }

    /**
     * 获取 部门id
     */
    public int getDepartmentId() {
        return this.departmentId;
    }

    /**
     * 设置 部门id
     */
    public UserInfoVo setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    /**
     * 获取 ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置 ip
     */
    public UserInfoVo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * 获取 手机号码
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置 手机号码
     */
    public UserInfoVo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * 获取 性别
     */
    public int getSex() {
        return this.sex;
    }

    /**
     * 设置 性别
     */
    public UserInfoVo setSex(int sex) {
        this.sex = sex;
        return this;
    }

    /**
     * 获取 公司ID
     */
    public String getUcompany() {
        return this.ucompany;
    }

    /**
     * 设置 公司ID
     */
    public void setUcompany(String ucompany) {
        this.ucompany = ucompany;
    }

    /**
     * 获取 区域查分
     */
    public String getOcode() {
        return this.ocode;
    }

    /**
     * 设置 区域查分
     */
    public void setOcode(String ocode) {
        this.ocode = ocode;
    }

    /**
     * 获取 公司名称
     */
    public String getOname() {
        return this.oname;
    }

    /**
     * 设置 公司名称
     */
    public void setOname(String oname) {
        this.oname = oname;
    }


    /**
     * 获取 省
     */
    public String getOprovince() {
        return this.oprovince;
    }

    /**
     * 设置 省
     */
    public void setOprovince(String oprovince) {
        this.oprovince = oprovince;
    }

    /**
     * 获取 市
     */
    public String getOcity() {
        return this.ocity;
    }

    /**
     * 设置 市
     */
    public void setOcity(String ocity) {
        this.ocity = ocity;
    }

    /**
     * 获取 区
     */
    public String getOarea() {
        return this.oarea;
    }

    /**
     * 设置 区
     */
    public void setOarea(String oarea) {
        this.oarea = oarea;
    }
}
