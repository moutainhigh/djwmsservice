package com.djcps.wms.stock.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 根据订单id获取库位
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class SelectAreaByOrderId extends BaseBO implements Serializable{

	private static final long serialVersionUID = 4690212141709269272L;
	
	/**
	 * 订单id
	 */
	@NotNull
	private List orderIds;

	public List getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List orderIds) {
		this.orderIds = orderIds;
	}

	@Override
	public String toString() {
		return "SelectAreaByOrderId [orderIds=" + orderIds + "]";
	}
	
}
