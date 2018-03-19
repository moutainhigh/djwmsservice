package com.djcps.wms.allocation.model;

import java.io.Serializable;

/**
 * 提货员
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class PickerPO implements Serializable{
	
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
	 * 提货员状态
	 */
	private String pickerStatus;
	
	public PickerPO(String pickerId, String pickerName, String pickerPhone, String pickerStatus) {
		super();
		this.pickerId = pickerId;
		this.pickerName = pickerName;
		this.pickerPhone = pickerPhone;
		this.pickerStatus = pickerStatus;
	}
	public String getPickerStatus() {
		return pickerStatus;
	}
	public void setPickerStatus(String pickerStatus) {
		this.pickerStatus = pickerStatus;
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
	@Override
	public String toString() {
		return "PickerPO [pickerId=" + pickerId + ", pickerName=" + pickerName + ", pickerPhone=" + pickerPhone
				+ ", pickerStatus=" + pickerStatus + "]";
	}
	
}
