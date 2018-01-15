package com.djcps.wms.order.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.constant.AppConstant;

/**
 * 订单号对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class OrderIdBO extends BaseListBO implements Serializable{
	
	private static final long serialVersionUID = -5616396253072660052L;

	/**
	 * 订单号
	 */
	private String childOrderId;
	
	private String orderId;
	
	private String status;
	
	@NotBlank
	private String fkeyarea;
	
	public OrderIdBO() {
		this.fkeyarea = "3303";
		this.setVersion(AppConstant.DEFAULT_VERSION);
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderIdBO [childOrderId=" + childOrderId + ", orderId=" + orderId + ", status=" + status + ", fkeyarea="
				+ fkeyarea + "]";
	}

}
