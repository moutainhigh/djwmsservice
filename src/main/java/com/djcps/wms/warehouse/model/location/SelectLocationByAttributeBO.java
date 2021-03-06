package com.djcps.wms.warehouse.model.location;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;

/**
 * @title:模糊查询仓库对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class SelectLocationByAttributeBO extends BaseListPartnerIdBO implements Serializable{

	private static final long serialVersionUID = 4482968502224906253L;
	
	/**
	 * 库位类型
	 */
	private String type;
	
	/**
	 * 库位编号
	 */
	private String warehouseLocId;
	
	/**
	 * 库位名称
	 */
	private String name;
	
	/**
	 * 库区编码
	 */
	private String warehouseAreaId;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWarehouseLocId() {
		return warehouseLocId;
	}

	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Override
	public String toString() {
		return "SelectLocationByAttributeBO [type=" + type + ", warehouseLocId=" + warehouseLocId + ", name=" + name
				+ ", warehouseAreaId=" + warehouseAreaId + ", warehouseId=" + warehouseId + "]";
	}

	
}
