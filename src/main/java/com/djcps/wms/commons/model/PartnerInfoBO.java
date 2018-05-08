package com.djcps.wms.commons.model;

import com.djcps.wms.commons.base.BaseBO;

import java.io.Serializable;

/**
 * 合作方对象
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public class PartnerInfoBO  extends BaseBO implements Serializable{
	
	private static final long serialVersionUID = -2191735308081531970L;

	/**
	 * 合作方id
	 */
	private String partnerId; 
	
	/**
	 * 合作方名称
	 */
	private String partnerName;
	
	/**
	 * 合作方区域,就是区域拆分键
	 */
	private String partnerArea;
	
	/**
	 * 合作方区域,就是区域拆分键,因为oms都需要这个字段,所有的话为什么方便映射冗余了这个字段
	 */
	private String keyArea;
	
	/**
	 * 操作人id
	 */
	private String operatorId;
	
	/**
	 * 操作人名称
	 */
	private String operator;

	public PartnerInfoBO(){
//		this.partnerId = "100"; 
//		this.partnerName = "东经科技";
//		this.partnerArea = "3303";
//		this.operatorId = "100";
//		this.operator = "admin";
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
		return "PartnerInfoBO [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea=" + partnerArea
				+ ", operatorId=" + operatorId + ", operator=" + operator + "]";
	}

}
