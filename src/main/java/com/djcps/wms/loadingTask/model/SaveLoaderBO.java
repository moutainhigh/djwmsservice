package com.djcps.wms.loadingTask.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 新增装车员
 * 
 * @company:djwms
 * @author:wyb
 * @date:2018年3月22日
 */
public class SaveLoaderBO extends BaseBO {

    /**
     * 
     */
    private static final long serialVersionUID = 1712276936124992924L;
    /**
     * 主键
     */
    private String id;
    /**
     * 姓名
     */
    @NotBlank
    private String name;
    /**
     * 性别(1男;0女)
     */
    private Integer sex;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 区名称
     */
    private String countyName;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 装车台id
     */
    private String loadingTableId;
    /**
     * 操作人编号
     */
    private String operatorId;
    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;
    /**
     * 操作人姓名
     */
    private String operator;

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    @Override
    public String toString() {
        return "SaveLoaderBO [id=" + id + ", name=" + name + ", sex=" + sex + ", idCard=" + idCard + ", phone=" + phone
                + ", provinceName=" + provinceName + ", cityName=" + cityName + ", countyName=" + countyName
                + ", address=" + address + ", remark=" + remark + ", loadingTableId=" + loadingTableId + ", operatorId="
                + operatorId + ", partnerId=" + partnerId + ", operator=" + operator + "]";
    }

}
