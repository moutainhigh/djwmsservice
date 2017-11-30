package com.djcps.wms.warehouse.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseParam;

/**
 * @title:仓库唯一标识查询对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class SelectWarehouseByIdBO extends BaseParam implements Serializable{

	private static final long serialVersionUID = 2805736427438190741L;

	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;
	
	/**
	 *合作方id
	 */
	@NotBlank
	private String partnerId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "SelectByIdBean [id=" + id + ", partnerId=" + partnerId + "]";
	}
	

}
