package com.djcps.wms.allocation.model;


import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;

/**
 * 根据运单号获取提货单信息
 * @company:djwms
 * @author:zdx
 * @date:2018年1月29日
 */
public class GetDeliveryByWaybillIdsBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -3661028574202815287L;
	
	/**
	 * 批量运单号 
	 */
	@NotEmpty
	private List<String> waybillIds;

	public List<String> getWaybillIds() {
		return waybillIds;
	}

	public void setWaybillIds(List<String> waybillIds) {
		this.waybillIds = waybillIds;
	}

	@Override
	public String toString() {
		return "GetDeliveryByWaybillIdBO [waybillIds=" + waybillIds + "]";
	}
	
}
