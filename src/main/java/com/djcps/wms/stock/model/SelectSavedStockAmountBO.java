package com.djcps.wms.stock.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseParam;

/**
 * 获取已入库的订单数量
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class SelectSavedStockAmountBO extends BaseParam implements Serializable{
	
	private static final long serialVersionUID = -4764501424934419327L;
	
	/**
	 * 订单号
	 */
	@NotBlank
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "SelectSavedStockAmount [orderId=" + orderId + "]";
	}
	
}
