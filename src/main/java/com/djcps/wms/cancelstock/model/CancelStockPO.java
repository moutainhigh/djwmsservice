package com.djcps.wms.cancelstock.model;


import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * 退库任务映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
public class CancelStockPO  implements Serializable{
	
	private static final long serialVersionUID = 2415742237477590586L;
	
	/**
	 * 子订单号
	 */
	private String sonOrderId;
	
	/**
	 * 唯一标识id
	 */
	private String id;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 退库数量
	 */
	private Integer cancelAmount;
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 提货员联系方式 
	 */
	private String pickerPhone;
	/**
	 * 任务状态(0未完成,1完成)
	 */
	private Integer status;
	
	
	public String getSonOrderId() {
		return sonOrderId;
	}
	public void setSonOrderId(String sonOrderId) {
		this.sonOrderId = sonOrderId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
		return "CancelStockPO [sonOrderId=" + sonOrderId + ", id=" + id + ", orderId=" + orderId + ", cancelAmount="
				+ cancelAmount + ", pickerId=" + pickerId + ", pickerName=" + pickerName + ", pickerPhone="
				+ pickerPhone + ", status=" + status + "]";
	}
	
}
