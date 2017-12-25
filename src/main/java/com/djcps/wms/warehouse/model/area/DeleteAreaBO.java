package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * @title:删除仓库对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月27日
 */
public class DeleteAreaBO extends BaseUpdateAndDeleteBO implements Serializable{
	
	private static final long serialVersionUID = -1269805468971963330L;
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 编码类型
	 */
	private String codeType;
	
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

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	@Override
	public String toString() {
		return "DeleteAreaBO [id=" + id + ", codeType=" + codeType + ", warehouseId=" + warehouseId
				+ ", warehouseAreaId=" + warehouseAreaId + "]";
	}

}
