package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseListBO;

public class GetOrderIdByOrderType extends BaseListBO implements Serializable{
	
	private static final long serialVersionUID = -7511910228670196227L;
	/**
	 * 订单类型
	 */
	private List<String> orderTypeList;
	public List<String> getOrderTypeList() {
		return orderTypeList;
	}
	public void setOrderTypeList(List<String> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}
	@Override
	public String toString() {
		return "GetOrderIdByOrderType [orderTypeList=" + orderTypeList + "]";
	}
	
}
