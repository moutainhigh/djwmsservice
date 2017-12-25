package com.djcps.wms.warehouse.model.warehouse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBo;

/**
 * @title:删除仓库对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class DeleteWarehouseBO extends BaseUpdateAndDeleteBo implements Serializable{
	
	private static final long serialVersionUID = -562816048816215248L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 编码类型
	 */
	private String codeType="1";
	
	/**
	 * 仓库编码 
	 */
	@NotBlank
	private String warehouseId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Override
	public String toString() {
		return "DeleteWarehouseBO [id=" + id + ", codeType=" + codeType + ", warehouseId=" + warehouseId + "]";
	}
	
}
