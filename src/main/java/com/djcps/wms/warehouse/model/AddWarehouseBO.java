package com.djcps.wms.warehouse.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:仓库新增对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月29日
 */
public class AddWarehouseBO extends BaseParam implements Serializable{

	private static final long serialVersionUID = -3191296476292183363L;
	
	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;
	
	/**
	 * 合作方名称
	 */
	@NotBlank
	private String partnerName;
	
	/**
	 * 合作方所在区域
	 */
	@NotBlank
	private String partnerArea;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	
	/**
	 * 仓库名称,最多10个字
	 */
	@NotBlank
	private String name;
	
	/**
	 * 仓库类型
	 */
	@NotBlank
	private String type;
	
	/**
	 * 仓库状态 1.使用中  2.已暂停
	 */
	private String state;
	
	/**
	 * 联系人,最多10个字
	 */
	private String contacts;
	
	/**
	 * 座机号码,最多15个字
	 */
	private String tel;
	
	/**
	 * 手机号码,以1开头的11位数字
	 */
	private String phone;
	
	/**
	 * 备注,最多50个字
	 */
	private String remark;
	
	/**
	 * 是否删除 1.未删除  2.已删除
	 */
	private String delete;
	
	/**
	 * 操作人id
	 */
	private String operatorId;
	
	/**
	 * 操作人
	 */
	private String operator;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "AddWarehouseBO [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea="
				+ partnerArea + ", warehouseId=" + warehouseId + ", name=" + name + ", type=" + type + ", state="
				+ state + ", contacts=" + contacts + ", tel=" + tel + ", phone=" + phone + ", remark=" + remark
				+ ", delete=" + delete + ", operatorId=" + operatorId + ", operator=" + operator + "]";
	}

}
