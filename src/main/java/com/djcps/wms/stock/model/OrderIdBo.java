package com.djcps.wms.stock.model;

/**
 * 订单号对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class OrderIdBo {
	
	/**
	 * 订单号
	 */
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderIdBo [orderId=" + orderId + "]";
	}
	
}
