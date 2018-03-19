package com.djcps.wms.warehouse.model.location;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;

/**
 * 获取所有库位对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月12日
 */
public class SelectAllLocationListBO extends BaseListPartnerIdBO implements Serializable{

	private static final long serialVersionUID = 3062314207607906888L;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	
	/**
	 * 库区编码
	 */
	@NotBlank
	private String warehouseAreaId;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	@Override
	public String toString() {
		return "SelectAllLocationListBO [warehouseId=" + warehouseId + ", warehouseAreaId=" + warehouseAreaId + "]";
	}
}
