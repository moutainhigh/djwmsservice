package com.djcps.wms.inneruser.model.result;

/**
 * 根据roleId 返回用户信息
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 14:36.
 */
public class OrgUserInfoByRoleIdPO {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 部门名称
     */
    private String depName;

    /**
     * 头像
     */
    private String uimage;

    /**
     * 手机号
     */
    private String uphone;

    /**
     * 部门
     */
    private String ufdepartment;

    /**
     * 拼音
     */
    private String uwholespell;

    /**
     * 职务
     */
    private String ujob;

    /**
     * 首字母
     */
    private String ufirstspell;

    /**
     * 职位
     */
    private String uposition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUfdepartment() {
        return ufdepartment;
    }

    public void setUfdepartment(String ufdepartment) {
        this.ufdepartment = ufdepartment;
    }

    public String getUwholespell() {
        return uwholespell;
    }

    public void setUwholespell(String uwholespell) {
        this.uwholespell = uwholespell;
    }

    public String getUjob() {
        return ujob;
    }

    public void setUjob(String ujob) {
        this.ujob = ujob;
    }

    public String getUfirstspell() {
        return ufirstspell;
    }

    public void setUfirstspell(String ufirstspell) {
        this.ufirstspell = ufirstspell;
    }

    public String getUposition() {
        return uposition;
    }

    public void setUposition(String uposition) {
        this.uposition = uposition;
    }

    @Override
    public String toString() {
        return "OrgUserInfoByRoleIdPO{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                ", depName='" + depName + '\'' +
                ", uimage='" + uimage + '\'' +
                ", uphone='" + uphone + '\'' +
                ", ufdepartment='" + ufdepartment + '\'' +
                ", uwholespell='" + uwholespell + '\'' +
                ", ujob='" + ujob + '\'' +
                ", ufirstspell='" + ufirstspell + '\'' +
                ", uposition='" + uposition + '\'' +
                '}';
    }
}
