package com.djcps.wms.warehouse.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:删除仓库对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class DeleteWarehouseBO extends BaseParam implements Serializable{
	
	private static final long serialVersionUID = -562816048816215248L;

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
		return "DeleteBean [id=" + id + ", partnerId=" + partnerId + ", operatorId=" + operatorId + ", operator="
				+ operator + "]";
	}
	

}
