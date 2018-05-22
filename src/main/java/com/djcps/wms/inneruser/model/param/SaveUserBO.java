package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;

/**
 * 保存用户信息
 * @author wzy
 * @date 2018/4/16
 **/
public class SaveUserBO implements Serializable{

    private String operator;

    /**
     * 调用的业务系统OA、CRM、BI、CRM、OMS、WMS、TMS
     */
    private String business;

    /**
     * 请求者的ip地址
     */
    private String ip;

    /**
     * 所属仓库id
     */
    private String warehouseId;

    private String warehouseName;

    /**
     * 角色类型
     */
    private String roleType;

    private String uids;

    private String uphone;

    private String upassword;

    private String uip;

    private String uname;

    private String ufirstspell;

    private String uwholespell;

    private String uimage;

    private String uinduction;

    private String ubirthday;

    private String fdepartment_id;

    private String fdepartment;

    private String uemail;

    private String usex;

    private String utype;

    private String ucompany;

    private String ucode;

    private String userStatus;

    private String udataAuthorityString;

    private String udataAuthority;

    private String cmp_user_position__id;

    private String uposition_name;

    private String ucompany_id;

    private String udepartment;

    private String udepartment_id;

    private String ushort_phone;

    private String ujob;

    private String cmp_user_appearance__id;

    private String ueducation;

    /**
     * 公司编号
     */
    private String companyID;

    /**
     * 婚姻状态
     */
    private String umarital_status;

    private String uid_card;

    private String unation;

    private String uplace_origin;

    private String uentry_date;

    private String upositive_date;

    private String uleave_date;

    /**
     * 政治面貌
     */
    private String upolitical_outlook;

    private String uregistered_residence;

    private String uhome_address;

    private String ugraduate_school;

    private String cmp_worktime_config__id;

    private String am_start;

    private String am_end;

    private String pm_start;

    private String pm_end;

    private String company_id;

    private String type;

    private String rule_name;

    private String roleids;

    /**
     * 新增时返回的用户id
     */
    private String id;

    private String positionId;

    private String isdel;

    private String isdel_per;

    private String create_time;

    private String update_time;

    private String onlineUserId;

    public String getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(String onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public String getRoleids() {
        return roleids;
    }

    public void setRoleids(String roleids) {
        this.roleids = roleids;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
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

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUip() {
        return uip;
    }

    public void setUip(String uip) {
        this.uip = uip;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUfirstspell() {
        return ufirstspell;
    }

    public void setUfirstspell(String ufirstspell) {
        this.ufirstspell = ufirstspell;
    }

    public String getUwholespell() {
        return uwholespell;
    }

    public void setUwholespell(String uwholespell) {
        this.uwholespell = uwholespell;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUinduction() {
        return uinduction;
    }

    public void setUinduction(String uinduction) {
        this.uinduction = uinduction;
    }

    public String getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(String ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getFdepartment_id() {
        return fdepartment_id;
    }

    public void setFdepartment_id(String fdepartment_id) {
        this.fdepartment_id = fdepartment_id;
    }

    public String getFdepartment() {
        return fdepartment;
    }

    public void setFdepartment(String fdepartment) {
        this.fdepartment = fdepartment;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public String getUcompany() {
        return ucompany;
    }

    public void setUcompany(String ucompany) {
        this.ucompany = ucompany;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUdataAuthorityString() {
        return udataAuthorityString;
    }

    public void setUdataAuthorityString(String udataAuthorityString) {
        this.udataAuthorityString = udataAuthorityString;
    }

    public String getUdataAuthority() {
        return udataAuthority;
    }

    public void setUdataAuthority(String udataAuthority) {
        this.udataAuthority = udataAuthority;
    }

    public String getCmp_user_position__id() {
        return cmp_user_position__id;
    }

    public void setCmp_user_position__id(String cmp_user_position__id) {
        this.cmp_user_position__id = cmp_user_position__id;
    }

    public String getUposition_name() {
        return uposition_name;
    }

    public void setUposition_name(String uposition_name) {
        this.uposition_name = uposition_name;
    }

    public String getUcompany_id() {
        return ucompany_id;
    }

    public void setUcompany_id(String ucompany_id) {
        this.ucompany_id = ucompany_id;
    }

    public String getUdepartment() {
        return udepartment;
    }

    public void setUdepartment(String udepartment) {
        this.udepartment = udepartment;
    }

    public String getUdepartment_id() {
        return udepartment_id;
    }

    public void setUdepartment_id(String udepartment_id) {
        this.udepartment_id = udepartment_id;
    }

    public String getUshort_phone() {
        return ushort_phone;
    }

    public void setUshort_phone(String ushort_phone) {
        this.ushort_phone = ushort_phone;
    }

    public String getUjob() {
        return ujob;
    }

    public void setUjob(String ujob) {
        this.ujob = ujob;
    }

    public String getCmp_user_appearance__id() {
        return cmp_user_appearance__id;
    }

    public void setCmp_user_appearance__id(String cmp_user_appearance__id) {
        this.cmp_user_appearance__id = cmp_user_appearance__id;
    }

    public String getUeducation() {
        return ueducation;
    }

    public void setUeducation(String ueducation) {
        this.ueducation = ueducation;
    }

    public String getUmarital_status() {
        return umarital_status;
    }

    public void setUmarital_status(String umarital_status) {
        this.umarital_status = umarital_status;
    }

    public String getUid_card() {
        return uid_card;
    }

    public void setUid_card(String uid_card) {
        this.uid_card = uid_card;
    }

    public String getUnation() {
        return unation;
    }

    public void setUnation(String unation) {
        this.unation = unation;
    }

    public String getUplace_origin() {
        return uplace_origin;
    }

    public void setUplace_origin(String uplace_origin) {
        this.uplace_origin = uplace_origin;
    }

    public String getUentry_date() {
        return uentry_date;
    }

    public void setUentry_date(String uentry_date) {
        this.uentry_date = uentry_date;
    }

    public String getUpositive_date() {
        return upositive_date;
    }

    public void setUpositive_date(String upositive_date) {
        this.upositive_date = upositive_date;
    }

    public String getUleave_date() {
        return uleave_date;
    }

    public void setUleave_date(String uleave_date) {
        this.uleave_date = uleave_date;
    }

    public String getUpolitical_outlook() {
        return upolitical_outlook;
    }

    public void setUpolitical_outlook(String upolitical_outlook) {
        this.upolitical_outlook = upolitical_outlook;
    }

    public String getUregistered_residence() {
        return uregistered_residence;
    }

    public void setUregistered_residence(String uregistered_residence) {
        this.uregistered_residence = uregistered_residence;
    }

    public String getUhome_address() {
        return uhome_address;
    }

    public void setUhome_address(String uhome_address) {
        this.uhome_address = uhome_address;
    }

    public String getUgraduate_school() {
        return ugraduate_school;
    }

    public void setUgraduate_school(String ugraduate_school) {
        this.ugraduate_school = ugraduate_school;
    }

    public String getCmp_worktime_config__id() {
        return cmp_worktime_config__id;
    }

    public void setCmp_worktime_config__id(String cmp_worktime_config__id) {
        this.cmp_worktime_config__id = cmp_worktime_config__id;
    }

    public String getAm_start() {
        return am_start;
    }

    public void setAm_start(String am_start) {
        this.am_start = am_start;
    }

    public String getAm_end() {
        return am_end;
    }

    public void setAm_end(String am_end) {
        this.am_end = am_end;
    }

    public String getPm_start() {
        return pm_start;
    }

    public void setPm_start(String pm_start) {
        this.pm_start = pm_start;
    }

    public String getPm_end() {
        return pm_end;
    }

    public void setPm_end(String pm_end) {
        this.pm_end = pm_end;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getIsdel_per() {
        return isdel_per;
    }

    public void setIsdel_per(String isdel_per) {
        this.isdel_per = isdel_per;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "SaveUserBO{" +
                "operator='" + operator + '\'' +
                ", business='" + business + '\'' +
                ", ip='" + ip + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", roleType='" + roleType + '\'' +
                ", uids='" + uids + '\'' +
                ", uphone='" + uphone + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uip='" + uip + '\'' +
                ", uname='" + uname + '\'' +
                ", ufirstspell='" + ufirstspell + '\'' +
                ", uwholespell='" + uwholespell + '\'' +
                ", uimage='" + uimage + '\'' +
                ", uinduction='" + uinduction + '\'' +
                ", ubirthday='" + ubirthday + '\'' +
                ", fdepartment_id='" + fdepartment_id + '\'' +
                ", fdepartment='" + fdepartment + '\'' +
                ", uemail='" + uemail + '\'' +
                ", usex='" + usex + '\'' +
                ", utype='" + utype + '\'' +
                ", ucompany='" + ucompany + '\'' +
                ", ucode='" + ucode + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", udataAuthorityString='" + udataAuthorityString + '\'' +
                ", udataAuthority='" + udataAuthority + '\'' +
                ", cmp_user_position__id='" + cmp_user_position__id + '\'' +
                ", uposition_name='" + uposition_name + '\'' +
                ", ucompany_id='" + ucompany_id + '\'' +
                ", udepartment='" + udepartment + '\'' +
                ", udepartment_id='" + udepartment_id + '\'' +
                ", ushort_phone='" + ushort_phone + '\'' +
                ", ujob='" + ujob + '\'' +
                ", cmp_user_appearance__id='" + cmp_user_appearance__id + '\'' +
                ", ueducation='" + ueducation + '\'' +
                ", companyID='" + companyID + '\'' +
                ", umarital_status='" + umarital_status + '\'' +
                ", uid_card='" + uid_card + '\'' +
                ", unation='" + unation + '\'' +
                ", uplace_origin='" + uplace_origin + '\'' +
                ", uentry_date='" + uentry_date + '\'' +
                ", upositive_date='" + upositive_date + '\'' +
                ", uleave_date='" + uleave_date + '\'' +
                ", upolitical_outlook='" + upolitical_outlook + '\'' +
                ", uregistered_residence='" + uregistered_residence + '\'' +
                ", uhome_address='" + uhome_address + '\'' +
                ", ugraduate_school='" + ugraduate_school + '\'' +
                ", cmp_worktime_config__id='" + cmp_worktime_config__id + '\'' +
                ", am_start='" + am_start + '\'' +
                ", am_end='" + am_end + '\'' +
                ", pm_start='" + pm_start + '\'' +
                ", pm_end='" + pm_end + '\'' +
                ", company_id='" + company_id + '\'' +
                ", type='" + type + '\'' +
                ", rule_name='" + rule_name + '\'' +
                ", roleids='" + roleids + '\'' +
                ", id='" + id + '\'' +
                ", positionId='" + positionId + '\'' +
                ", isdel='" + isdel + '\'' +
                ", isdel_per='" + isdel_per + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", onlineUserId='" + onlineUserId + '\'' +
                '}';
    }
}
