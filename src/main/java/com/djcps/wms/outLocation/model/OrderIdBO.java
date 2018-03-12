package com.djcps.wms.outLocation.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseListPartnerIdBO;

public class OrderIdBO extends BaseListPartnerIdBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8609830274240724487L;
	
	private String orderId;
	
	private String fkeyarea;
	
	public OrderIdBO(){
		this.fkeyarea = "3303";
	}
	
	public String getFkeyarea() {
		return fkeyarea;
	}
	public void setFkeyarea(String fkeyarea) {
		this.fkeyarea = fkeyarea;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
