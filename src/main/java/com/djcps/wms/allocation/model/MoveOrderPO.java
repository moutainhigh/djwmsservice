package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 移除订单
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class MoveOrderPO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 4987272235693256199L;
	
	/**
	 * 订单号
	 */
	@NotEmpty
	private List<String> orderIds;
	
	/**
	 * 标记
	 */
	private String flag;
	
	/**
	 * 订单状态
	 */
	private Integer status;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	@Override
	public String toString() {
		return "MoveOrderPO [orderIds=" + orderIds + ", status=" + status + "]";
	}
	
}
