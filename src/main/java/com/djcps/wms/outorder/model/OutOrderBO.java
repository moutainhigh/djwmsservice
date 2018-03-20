package com.djcps.wms.outorder.model;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 获取出库单id
 * @author ldh
 *
 */
public class OutOrderBO {
	@NotBlank
	private String outOrderId;

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	@Override
	public String toString() {
		return "OutOrderBO [outOrderId=" + outOrderId + "]";
	}
	
}
