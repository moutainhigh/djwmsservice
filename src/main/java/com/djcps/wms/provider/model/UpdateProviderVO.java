package com.djcps.wms.provider.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBo;

/**
 * @title:供应商修改对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class UpdateProviderVO extends BaseUpdateAndDeleteBo implements Serializable{

	private static final long serialVersionUID = 3436724307882748378L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 供应商名称,30个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 供应商简称,10个字 
	 */
	@NotBlank
	private String shortName;
	
	/**
	 * 官网url,50个字
	 */
	private String webUrl;
	
	/**
	 * 省份名称
	 */
	private String provinceName;
	
	/**
	 * 省份编码
	 */
	private String provinceCode;
	
	/**
	 * 城市名称 
	 */
	private String cityName;
	
	/**
	 * 城市编码
	 */
	private String cityCode;
	
	/**
	 * 区名称
	 */
	private String areaName;
	
	/**
	 * 区编码
	 */
	private String areaCode;
	
	/**
	 * 供应商地址,50个字
	 */
	private String address;
	
	/**
	 * 联系人,10个字
	 */
	private String contacts;
	
	/**
	 * 座机电话,15个字
	 */
	private String tel;
	
	/**
	 * 手机号码,11个字
	 */
	private String phone;
	
	/**
	 * 传真号码,15个字 
	 */
	private String fax;
	
	/**
	 * 邮箱,30个字
	 */
	private String email;
	
	/**
	 * 备注,50个字
	 */
	private String remark;

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "UpdateProviderVO [id=" + id + ", name=" + name + ", shortName=" + shortName + ", webUrl=" + webUrl
				+ ", provinceName=" + provinceName + ", provinceCode=" + provinceCode + ", cityName=" + cityName
				+ ", cityCode=" + cityCode + ", areaName=" + areaName + ", areaCode=" + areaCode + ", address="
				+ address + ", contacts=" + contacts + ", tel=" + tel + ", phone=" + phone + ", fax=" + fax + ", email="
				+ email + ", remark=" + remark + "]";
	}

}
