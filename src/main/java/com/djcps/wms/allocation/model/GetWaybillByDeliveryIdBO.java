package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 根据提货单号获取运单信息
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class GetWaybillByDeliveryIdBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 1453575601560102835L;
	
	/**
	 * 运单号
	 */
	private String deliveryId;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	@Override
	public String toString() {
		return "GetWaybillByDeliveryIdBO [deliveryId=" + deliveryId + "]";
	}

}
