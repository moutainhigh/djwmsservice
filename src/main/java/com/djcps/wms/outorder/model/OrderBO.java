package com.djcps.wms.outorder.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseListPartnerIdBO;
/**
 * 
 * @author ldh
 *
 */
public class OrderBO extends BaseListPartnerIdBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3821544873168751274L;
	private String childOrderId;
	private Integer fkeyarea;
	public OrderBO(){
		this.fkeyarea = 3303;
	}
	public String getChildOrderId() {
		return childOrderId;
	}
	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}
	public Integer getFkeyarea() {
		return fkeyarea;
	}
	public void setFkeyarea(Integer fkeyarea) {
		this.fkeyarea = fkeyarea;
	}
	@Override
	public String toString() {
		return "OrderBO [childOrderId=" + childOrderId + ", fkeyarea=" + fkeyarea + "]";
	}
	
}
