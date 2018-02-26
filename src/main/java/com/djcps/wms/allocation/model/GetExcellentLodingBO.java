package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车优化
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class GetExcellentLodingBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 5195739532131414696L;
	
	/**
	 * 运单号
	 */
	@NotBlank
	private String waybillId;
	
	/**
	 * 追加订单界面传来的新的提货单号
	 */
	private String deliveryId;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	@Override
	public String toString() {
		return "GetExcellentLodingBO [waybillId=" + waybillId + ", deliveryId=" + deliveryId + "]";
	}
	
}
