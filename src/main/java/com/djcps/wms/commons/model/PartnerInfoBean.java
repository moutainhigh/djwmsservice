package com.djcps.wms.commons.model;

public class PartnerInfoBean {
	
	private String partnerId; 
	private String partnerName;
	private String partnerArea;
	private String operatorId;
	private String operator;
	
	public PartnerInfoBean(){
		this.partnerId = "100"; 
		this.partnerName = "东经科技";
		this.partnerArea = "3303";
		this.operatorId = "100";
		this.operator = "admin";
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "PartnerInfoBean [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea="
				+ partnerArea + ", operatorId=" + operatorId + ", operator=" + operator + "]";
	}
}
