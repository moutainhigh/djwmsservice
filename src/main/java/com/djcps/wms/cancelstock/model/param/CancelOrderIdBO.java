package com.djcps.wms.cancelstock.model.param;


import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * 订单号属性
 * @company:djwms
 * @author:zdx
 * @date:2018年3月20日
 */
public class CancelOrderIdBO extends BaseUpdateAndDeleteBO implements Serializable{

	private static final long serialVersionUID = 6241284697312903572L;
	
	/**
	 * 订单号 
	 */
	@NotBlank
	private String orderId;
	
	@NotBlank
	private String pickerId;

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderIdBO [orderId=" + orderId + ", pickerId=" + pickerId + "]";
	}
	
}
