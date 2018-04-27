package com.djcps.wms.workrecords.model;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BasePO;

/**
 * 
 * 
 * @author py
 *
 */

public class WorkRecordsPO extends BasePO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2643150196619346401L;
	
	/**
	 * 楞型
	 */
	private Integer fluteType;

	/**
	 * 楞型名称
	 */
	private String fluteTypeName;

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

    public String getFluteTypeName() {
        return fluteTypeName;
    }

    public void setFluteTypeName(String fluteTypeName) {
        this.fluteTypeName = fluteTypeName;
    }

    @Override
    public String toString() {
        return "WorkRecordsPO{" +
                "fluteType=" + fluteType +
                ", fluteTypeName='" + fluteTypeName + '\'' +
                ", totalArea=" + totalArea +
                '}';
    }


}
