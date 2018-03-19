package com.djcps.wms.warehouse.model.area;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 * @title:仓库唯一标识查询对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public class SelectAreaByIdBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 5667965468953896917L;
	/**
	 * 唯一标识
	 */
	@NotBlank
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SelectWarehouseByIdBO [id=" + id + "]";
	}

}
