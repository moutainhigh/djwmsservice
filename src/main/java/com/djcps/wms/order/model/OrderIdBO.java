package com.djcps.wms.order.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 订单号对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class OrderIdBO extends BaseBO implements Serializable{
	
	private static final long serialVersionUID = -5616396253072660052L;

	/**
	 * 订单号
	 */
	@NotBlank
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
