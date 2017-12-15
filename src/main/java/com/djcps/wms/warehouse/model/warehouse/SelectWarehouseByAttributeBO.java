package com.djcps.wms.warehouse.model.warehouse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:模糊查询仓库对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class SelectWarehouseByAttributeBO extends BaseListParam implements Serializable{
	
	private static final long serialVersionUID = -5753645244313461096L;

	/**
	 * 仓库编码
	 */
	private String warehouseId;
	
	/**
	 * 仓库名称,最多10个字
	 */
	private String name;
	
	/**
	 * 仓库类型
	 */
	private String type;
	
	/**
	 * 启用,禁用
	 */
	private String effect;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	@Override
	public String toString() {
		return "SelectWarehouseByAttributeBO [warehouseId=" + warehouseId + ", name=" + name + ", type=" + type
				+ ", effect=" + effect + "]";
	}


}
