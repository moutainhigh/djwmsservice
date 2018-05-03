package com.djcps.wms.order.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.commons.constant.AppConstant;

/**
 * 订单号对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class OrderIdBO extends BaseListPartnerIdBO implements Serializable{
	
	private static final long serialVersionUID = -5616396253072660052L;

	/**
	 * 订单服务用的订单号
	 */
	private String childOrderId;
	
	/**
	 * 我们自己用的订单号
	 */
	private String orderId;
	
	private String status;
	
	private String keyArea;
	
	private String partnerArea;

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public String getKeyArea() {
		return keyArea;
	}

	public void setKeyArea(String keyArea) {
		this.keyArea = keyArea;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderIdBO [childOrderId=" + childOrderId + ", orderId=" + orderId + ", status=" + status + ", keyArea="
				+ keyArea + ", partnerArea=" + partnerArea + "]";
	}
	
}
