package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.model.PartnerInfoBO;

/**
 * 拆分订单对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月18日
 */
public class SplitOrderBO extends PartnerInfoBO implements Serializable{
	
	private static final long serialVersionUID = 5513358744068786620L;

	/**
	 * 子订单
	 */
	@NotEmpty
	private String orderId;
	
	/**
	 * 订单状态
	 */
	private Integer orderStatus;
	
	/**
	 * 拆分标记
	 */
	private Integer isSplit;
	
	/**
	 * 订单数量
	 */
	private Integer orderAmount;
	
	/**
	 * 第一条拆单信息
	 */
	private SplitOrderFirstBO splitOrderFirst;
	
	/**
	 * 第二条拆单信息
	 */
	private SplitOrderSecondBO splitOrderSecond;

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public SplitOrderFirstBO getSplitOrderFirst() {
		return splitOrderFirst;
	}

	public void setSplitOrderFirst(SplitOrderFirstBO splitOrderFirst) {
		this.splitOrderFirst = splitOrderFirst;
	}

	public SplitOrderSecondBO getSplitOrderSecond() {
		return splitOrderSecond;
	}

	public void setSplitOrderSecond(SplitOrderSecondBO splitOrderSecond) {
		this.splitOrderSecond = splitOrderSecond;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}

	@Override
	public String toString() {
		return "SplitOrderBO [orderId=" + orderId + ", orderStatus=" + orderStatus + ", isSplit=" + isSplit
				+ ", splitOrderFirst=" + splitOrderFirst + ", splitOrderSecond=" + splitOrderSecond + "]";
	}
	
}
