package com.djcps.wms.workrecords.model.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;

public class WorkRecordsParam extends BaseListBO{
	/**
	 * 操作人id
	 */
	@NotBlank
	private String operatorId;
	/**
	 * 操作类型
	 */
	@NotNull
	private Integer operationType;
	/**
	 * 操作的纸板的楞型
	 */
	@NotNull
	private Integer fluteType;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public Integer getFluteType() {
		return fluteType;
	}

	public void setFluteType(Integer fluteType) {
		this.fluteType = fluteType;
	}

	@Override
	public String toString() {
		return "WorkRecordsParam [operatorId=" + operatorId + ", operationType=" + operationType + ", fluteType="
				+ fluteType + "]";
	}
	
	
}
