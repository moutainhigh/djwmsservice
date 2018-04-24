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
	
	private String partnerArea;

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	@Override
	public String toString() {
		return "OutOrderBO [outOrderId=" + outOrderId + ", partnerArea=" + partnerArea + "]";
	}
	
}
