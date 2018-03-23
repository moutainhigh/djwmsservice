package com.djcps.wms.cancelstock.model.param;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 新增退库
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
public class AddCancelStockBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 1164403337815657630L;
	
	/**
	 * 订单号
	 */
	@NotBlank
	private String order;
	/**
	 * 退库数量
	 */
	@NotBlank
	private Integer cancelAmount;
	/**
	 * 提货人id(提货人即退库人)
	 */
	@NotBlank
	private String pickerId;
	/**
	 * 提货员名称
	 */
	@NotBlank
	private String pickerName;
	/**
	 * 提货员联系方式
	 */
	@NotBlank
	private String pickerPhone;
	/**
	 * 任务状态(0未完成;1完成)
	 */
	@NotNull
	private Integer status;
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getCancelAmount() {
		return cancelAmount;
	}
	public void setCancelAmount(Integer cancelAmount) {
		this.cancelAmount = cancelAmount;
	}
	public String getPickerId() {
		return pickerId;
	}
	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}
	public String getPickerName() {
		return pickerName;
	}
	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}
	public String getPickerPhone() {
		return pickerPhone;
	}
	public void setPickerPhone(String pickerPhone) {
		this.pickerPhone = pickerPhone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AddCancelStockBO [order=" + order + ", cancelAmount=" + cancelAmount + ", pickerId=" + pickerId
				+ ", pickerName=" + pickerName + ", pickerPhone=" + pickerPhone + ", status=" + status + "]";
	}
	
}
