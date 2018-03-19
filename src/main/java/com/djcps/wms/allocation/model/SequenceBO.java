package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车顺序对象
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class SequenceBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 910056481885333624L;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 装车顺序
	 */
	private Integer sequence;
	
	/**
	 * 提货单号
	 */
	private String deliveryId;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "SequenceBO [orderId=" + orderId + ", sequence=" + sequence + ", deliveryId=" + deliveryId + "]";
	}

}
