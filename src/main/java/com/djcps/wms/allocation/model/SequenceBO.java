package com.djcps.wms.allocation.model;

import java.io.Serializable;

/**
 * 装车顺序
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class SequenceBO implements Serializable{

	private static final long serialVersionUID = 910056481885333624L;
	
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 装车顺序
	 */
	private String sequence;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "SequenceBO [orderId=" + orderId + ", sequence=" + sequence + "]";
	}
	
}
