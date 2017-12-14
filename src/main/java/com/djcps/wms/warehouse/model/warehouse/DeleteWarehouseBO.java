package com.djcps.wms.warehouse.model.warehouse;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.base.BaseParam;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeleteWarehouseBO [id=" + id + "]";
	}

}
