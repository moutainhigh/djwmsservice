package com.djcps.wms.order.model;

/**
 * 库位对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseLocationBO {
	
	/**
	 * 实时在库数量
	 */
	private String trueAmount;
    /**
     * 库位编码
     */
    private String warehouseLocId;
    /**
     * 库位名称
     */
    private String warehouseLocName;
    
	public String getTrueAmount() {
		return trueAmount;
	}
	public void setTrueAmount(String trueAmount) {
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
		return "WarehouseLocationBo [trueAmount=" + trueAmount + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + "]";
	}
    
}
