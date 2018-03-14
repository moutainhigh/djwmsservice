package com.djcps.wms.abnormal.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 订单号
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public class OrderIdBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 5610465748707236433L;
	
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderIdBO [orderId=" + orderId + "]";
	}
	
}
