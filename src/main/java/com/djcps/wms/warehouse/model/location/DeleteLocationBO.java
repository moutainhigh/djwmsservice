package com.djcps.wms.warehouse.model.location;

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
public class DeleteLocationBO extends BaseUpdateAndDeleteBo implements Serializable{
	
	private static final long serialVersionUID = 8845311354628210086L;
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 * 编码类型
	 */
	private String codeType="3";
	
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
	
	/**
	 * 库位编码 
	 */
	@NotBlank
	private String warehouseLocId;

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
	}

	public String getWarehouseLocId() {
		return warehouseLocId;
	}

	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}

	@Override
	public String toString() {
		return "DeleteLocationBO [id=" + id + ", codeType=" + codeType + ", warehouseId=" + warehouseId
				+ ", warehouseAreaId=" + warehouseAreaId + ", warehouseLocId=" + warehouseLocId + "]";
	}
	
}
