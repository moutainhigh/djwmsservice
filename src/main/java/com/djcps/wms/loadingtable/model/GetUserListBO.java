package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 获取装车台账户列表
 * @company:djwms
 * @author:zdx
 * @date:2018年3月22日
 */
public class GetUserListBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 4722520774745756476L;
	
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Override
	public String toString() {
		return "GetUserListBO [warehouseId=" + warehouseId + "]";
	}
}
