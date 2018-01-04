package com.djcps.wms.warehouse.model.location;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 获取所有库位对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月12日
 */
public class SelectAllLocationListBO extends BaseListBO implements Serializable{

	private static final long serialVersionUID = 3062314207607906888L;
	
	/**
	 * 合作方id
	 */
	@NotBlank
	private String partnerId;
	
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

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
		return "SelectAllLocationList [partnerId=" + partnerId + ", warehouseId=" + warehouseId + ", warehouseAreaId="
				+ warehouseAreaId + "]";
	}
	
}
