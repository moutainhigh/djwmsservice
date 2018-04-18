package com.djcps.wms.workrecords.model;

import com.djcps.wms.commons.base.BaseAddBO;

public class WorkRecordsPO extends BaseAddBO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2643150196619346401L;
	
	/**
	 * 楞型
	 */
	private Integer fluteType;
	/**
	 * 总面积
	 */
	private Double totalArea;
	
	
	public Integer getFluteType() {
		return fluteType;
	}
	public void setFluteType(Integer fluteType) {
		this.fluteType = fluteType;
	}
	public Double getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}
	@Override
	public String toString() {
		return "WorkRecordsPO [fluteType=" + fluteType + ", totalArea=" + totalArea + "]";
	}
	
	
	
}
