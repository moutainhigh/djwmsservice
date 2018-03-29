package com.djcps.wms.outorder.model.outorderresult;

import java.util.List;

import com.djcps.wms.outorder.model.OrderDetailBO;
/**
 * 前端接收出库单结果
 * @author ldh
 *
 */
public class OrderDetailInfoVO {
	/**
	 * 总金额
	 */
	private Double totalPrice;
	/**
	 * 订单明细 
	 */
	private List<OrderDetailBO> orderDetails;
	/**
	 * 总数量
	 */
	private Integer totalAmount;
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderDetailBO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailBO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "OrderDetailInfoVO [totalPrice=" + totalPrice + ", orderDetails=" + orderDetails + ", totalAmount="
				+ totalAmount + "]";
	}
	
	
}
