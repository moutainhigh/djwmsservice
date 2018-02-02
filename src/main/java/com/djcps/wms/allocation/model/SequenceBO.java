package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBO;

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
		return "SequenceBO [orderId=" + orderId + ", sequence=" + sequence + "]";
	}
	
	
}
