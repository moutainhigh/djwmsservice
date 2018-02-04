package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;

/**
 * 混合配货,根据混合订单类型获取订单号
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class GetOrderIdByOrderType extends BaseListPartnerIdBO implements Serializable{
	
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
