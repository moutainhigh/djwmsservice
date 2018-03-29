package com.djcps.wms.cancelstock.model.param;


import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * 修改退库
 * @company:djwms
 * @author:zdx
 * @date:2018年3月19日
 */
public class UpdateCancelStockBO extends BaseUpdateAndDeleteBO implements Serializable {

	private static final long serialVersionUID = 8805585463894544952L;
	
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
		return "UpdateCancelStockBO [id=" + id + "]";
	}
}
