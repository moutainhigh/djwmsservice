package com.djcps.wms.commons.base;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title:新增基础对象
 * @description:需要新增的对象,需要继承此对象
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public class BaseAddBO extends BaseBO implements Serializable{
	
	private static final long serialVersionUID = -185610484814897617L;

	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;
	
	/**
	 * 合作方名称
	 */
	@NotBlank
	private String partnerName;
	
	/**
	 * 合作方所在区域
	 */
	@NotBlank
	private String partnerArea;
	
	/**
	 * 操作人id
	 */
	@NotBlank
	private String operatorId;
	
	/**
	 * 操作人名称
	 */
	@NotBlank
	private String operator;

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
		return "BaseAddBo [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea=" + partnerArea
				+ ", operatorId=" + operatorId + ", operator=" + operator + "]";
	}
	
}
