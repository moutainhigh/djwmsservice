package com.djcps.wms.order.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseBO;

/**
 * 多个订单号对象
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class OrderIdsBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 7172144476115730320L;
	
	private List<String> childOrderIds;
	
	private String partnerArea;

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public List<String> getChildOrderIds() {
		return childOrderIds;
	}

	public void setChildOrderIds(List<String> childOrderIds) {
		this.childOrderIds = childOrderIds;
	}

	@Override
	public String toString() {
		return "OrderIdsBO [childOrderIds=" + childOrderIds + ", partnerArea=" + partnerArea + "]";
	}
	
}
