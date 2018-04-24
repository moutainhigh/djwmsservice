package com.djcps.wms.order.model.onlinepaperboard;



import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * 拆单的子单对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class UpdateSplitSonOrderBO implements Serializable{
	private static final long serialVersionUID = -6967814470639816450L;
	/**
     * 区域拆分键
     */
    @NotNull
    private String keyArea;
    /**
     * 订单id
     */
    @NotNull
    private String orderId;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 拆单状态 0未拆  1已拆单
     */
    private String splitStatus;
    /**
     * 拆单集合
     */
    private List<UpdateSplitOrderBO> splitOrders;
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<UpdateSplitOrderBO> getSplitOrders() {
		return splitOrders;
	}
	public void setSplitOrders(List<UpdateSplitOrderBO> splitOrders) {
		this.splitOrders = splitOrders;
	}
	public String getKeyArea() {
		return keyArea;
	}
	public void setKeyArea(String keyArea) {
		this.keyArea = keyArea;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getSplitStatus() {
		return splitStatus;
	}
	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}
	@Override
	public String toString() {
		return "UpdateSplitSonOrderBO [keyArea=" + keyArea + ", orderId=" + orderId + ", orderStatus=" + orderStatus
				+ ", splitStatus=" + splitStatus + ", splitOrders=" + splitOrders + "]";
	}
    
}
