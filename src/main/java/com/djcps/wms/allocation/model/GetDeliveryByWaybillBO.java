package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 根据运单号获取提货单详情
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class GetDeliveryByWaybillBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -8810983484407999018L;
	
	/**
	 * 运单号
	 */
	@NotBlank
	private String WaybillId;

	public String getWaybillId() {
		return WaybillId;
	}

	public void setWaybillId(String waybillId) {
		WaybillId = waybillId;
	}

	@Override
	public String toString() {
		return "GetDeliveryByWaybillBO [WaybillId=" + WaybillId + "]";
	}
	
}
