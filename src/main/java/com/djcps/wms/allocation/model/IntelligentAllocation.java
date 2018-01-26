package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 * 智能配货映射结果
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class IntelligentAllocation implements Serializable{

	private static final long serialVersionUID = -3290887202534612683L;
	
	private WarehouseOrderDetailPO warehouseOrderDetailPO;
	
	private CarInfo carInfo;

	public WarehouseOrderDetailPO getWarehouseOrderDetailPO() {
		return warehouseOrderDetailPO;
	}

	public void setWarehouseOrderDetailPO(WarehouseOrderDetailPO warehouseOrderDetailPO) {
		this.warehouseOrderDetailPO = warehouseOrderDetailPO;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	@Override
	public String toString() {
		return "IntelligentAllocation [warehouseOrderDetailPO=" + warehouseOrderDetailPO + ", carInfo=" + carInfo + "]";
	}
	
}
