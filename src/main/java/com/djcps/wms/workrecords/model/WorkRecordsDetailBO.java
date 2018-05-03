package com.djcps.wms.workrecords.model;

import com.djcps.wms.commons.base.BaseListBO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
/**
 * 
 * @author py
 *
 */
public class WorkRecordsDetailBO extends BaseListBO{
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
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "WorkRecordsDetailBO [operatorId=" + operatorId + ", operationType=" + operationType + ", fluteType="
				+ fluteType + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	
	
	
}
