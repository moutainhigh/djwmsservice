package com.djcps.wms.warehouse.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:启用禁用装车台对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class IsUseWarehouseBO extends BaseParam implements Serializable{

	private static final long serialVersionUID = -7664247947519185744L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
		return "IsUseBean [id=" + id + ", partnerId=" + partnerId + ", operatorId=" + operatorId + ", operator="
				+ operator + "]";
	}

}
