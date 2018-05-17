package com.djcps.wms.outorder.model.outorderresult;

import java.util.List;

import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.outorder.model.OrderDetailBO;

/**
 * 订单详细信息
 * @author ldh
 */
public class OrderDetailInfoVO {
	/** 
	 * 总金额
	 */
	private Double totalPrice;
	/**
	 * 订单明细 
	 */
	private List<WarehouseOrderDetailPO> orderDetails;
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
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
    public List<WarehouseOrderDetailPO> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<WarehouseOrderDetailPO> orderDetails) {
        this.orderDetails = orderDetails;
    }
    @Override
    public String toString() {
        return "OrderDetailInfoVO [totalPrice=" + totalPrice + ", orderDetails=" + orderDetails + ", totalAmount="
                + totalAmount + "]";
    }
	
}
