package com.djcps.wms.order.model.onlinepaperboard;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.model.PartnerInfoBO;

/**
 * 根据订单号批量查询订单详情,支持拆单和子单混合查询
 * @company:djwms
 * @author:zdx
 * @date:2018年4月13日
 */
public class BatchOrderIdListBO extends PartnerInfoBO implements Serializable {

	private static final long serialVersionUID = -6932870228839059118L;
	
	private List<String> orderIds;
	
	private String keyArea;

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public String getKeyArea() {
		return keyArea;
	}

	public void setKeyArea(String keyArea) {
		this.keyArea = keyArea;
	}

	@Override
	public String toString() {
		return "BatchOrderIdList [orderIds=" + orderIds + ", keyArea=" + keyArea + "]";
	}
	
}
