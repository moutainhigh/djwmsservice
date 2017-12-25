package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 根据仓库id获取该仓库下所有的库区
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月11日
 */
public class SelectAllAreaListBO extends BaseBO implements Serializable{
	
	private static final long serialVersionUID = -2898629682156747601L;

	/**
	 * 合作方id
	 */
	@NotBlank
	private String partnerId;
	
	/**
	 * 仓库编号
	 */
	@NotBlank
	private String warehouseId;

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

	@Override
	public String toString() {
		return "SelectAllAreaList [partnerId=" + partnerId + ", warehouseId=" + warehouseId + "]";
	}
	
}
