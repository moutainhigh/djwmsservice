package com.djcps.wms.order.model;

import java.io.Serializable;

/**
 * 库位对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseLocationBO implements Serializable{
	
	private static final long serialVersionUID = 4913476852497777763L;
	/**
	 * 实时在库数量
	 */
	private Integer trueAmount;
    /**
     * 库位编码
     */
    private String warehouseLocId;
    /**
     * 库位名称
     */
    private String warehouseLocName;
    
	public Integer getTrueAmount() {
		return trueAmount;
	}
	public void setTrueAmount(Integer trueAmount) {
		this.trueAmount = trueAmount;
	}
	public String getWarehouseLocId() {
		return warehouseLocId;
	}
	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}
	public String getWarehouseLocName() {
		return warehouseLocName;
	}
	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}
	@Override
	public String toString() {
		return "WarehouseLocationBO [trueAmount=" + trueAmount + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + "]";
	}
    
}
