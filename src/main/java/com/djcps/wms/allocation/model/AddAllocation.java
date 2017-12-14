package com.djcps.wms.allocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseAddBo;

/**
 * 订单类型对象
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年12月8日
 */
public class AddAllocation extends BaseAddBo implements Serializable{
	
	private static final long serialVersionUID = -5680146319503523432L;
	
	/**
	 * 参与配货订单类型集合
	 */
	private String orderTypes;

	public String getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(String orderTypes) {
		this.orderTypes = orderTypes;
	}

	@Override
	public String toString() {
		return "OrderType [orderTypes=" + orderTypes + "]";
	}
	
}
