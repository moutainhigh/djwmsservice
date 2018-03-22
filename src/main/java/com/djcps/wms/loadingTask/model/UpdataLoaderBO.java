package com.djcps.wms.loadingTask.model;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 *  更新装车员信息参数
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class UpdataLoaderBO extends BaseBO {

	private String id;
	
    /**
     * 
     */
    private static final long serialVersionUID = -4375821817069817622L;
    /**
     * 姓名
     */
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
    private String loadingTableid;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 操作人编号
     */
    @NotBlank
    private String operatorId;
    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;
    /**
     * 操作人姓名
     */
    @NotBlank
    private String operator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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

    public String getLoadingTableid() {
        return loadingTableid;
    }

    public void setLoadingTableid(String loadingTableid) {
        this.loadingTableid = loadingTableid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "UpdataLoaderBO [id=" + id + ", name=" + name + ", sex=" + sex + ", idCard=" + idCard + ", phone="
				+ phone + ", provinceName=" + provinceName + ", cityName=" + cityName + ", countyName=" + countyName
				+ ", address=" + address + ", remark=" + remark + ", loadingTableid=" + loadingTableid + ", status="
				+ status + ", operatorId=" + operatorId + ", partnerId=" + partnerId + ", operator=" + operator + "]";
	}

}
