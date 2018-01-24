package com.djcps.wms.order.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseBO;

public class OrderIdsBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 7172144476115730320L;
	
	private List<String> childOrderIds;

	public List<String> getChildOrderIds() {
		return childOrderIds;
	}

	public void setChildOrderIds(List<String> childOrderIds) {
		this.childOrderIds = childOrderIds;
	}

	@Override
	public String toString() {
		return "OrderIdsBO [childOrderIds=" + childOrderIds + "]";
	}
	
}
