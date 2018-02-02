package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 修改冗余表字段
 * @company:djwms
 * @author:zdx
 * @date:2018年1月26日
 */
public class UpdateOrderRedundantBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 3538259834697292085L;
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 订单状态
	 */
	private Integer status;
	/**
	 * 是否拆分
	 */
	private Integer isSplit;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	private String partnerId;
	
	
	public String getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsSplit() {
		return isSplit;
	}
	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}
	@Override
	public String toString() {
		return "UpdateOrderRedundantBO [waybillId=" + waybillId + ", deliveryId=" + deliveryId + ", orderId=" + orderId
				+ ", status=" + status + ", isSplit=" + isSplit + ", plateNumber=" + plateNumber + ", partnerId="
				+ partnerId + "]";
	}
	
}
