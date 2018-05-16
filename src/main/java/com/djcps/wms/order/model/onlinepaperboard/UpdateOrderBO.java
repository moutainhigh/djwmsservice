package com.djcps.wms.order.model.onlinepaperboard;



import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.model.PartnerInfoBO;

import java.io.Serializable;
import java.util.List;

/**
 * 拆单的子单对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class UpdateOrderBO extends PartnerInfoBO implements Serializable{
	private static final long serialVersionUID = -6967814470639816450L;
	/**
     * 区域拆分键
     */
    private String keyArea;
    
    /**
     * 订单id
     */
    @NotEmpty
    private String orderId;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 拆单状态 0未拆  1已拆单
     */
    private Integer splitStatus;
    
    @NotNull
    private List<String> deleteOrdeIdList;
    
    /**
     * 拆单集合
     */
    private List<UpdateSplitOrderBO> splitOrders;
    
    @NotNull
    private UpdateSplitOrderBO firstSpiltOrder;
    
    @NotNull
    private UpdateSplitOrderBO secondSpiltOrder;

	public List<String> getDeleteOrdeIdList() {
		return deleteOrdeIdList;
	}
	public void setDeleteOrdeIdList(List<String> deleteOrdeIdList) {
		this.deleteOrdeIdList = deleteOrdeIdList;
	}
	public UpdateSplitOrderBO getFirstSpiltOrder() {
		return firstSpiltOrder;
	}
	public void setFirstSpiltOrder(UpdateSplitOrderBO firstSpiltOrder) {
		this.firstSpiltOrder = firstSpiltOrder;
	}
	public UpdateSplitOrderBO getSecondSpiltOrder() {
		return secondSpiltOrder;
	}
	public void setSecondSpiltOrder(UpdateSplitOrderBO secondSpiltOrder) {
		this.secondSpiltOrder = secondSpiltOrder;
	}
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
	public Integer getSplitStatus() {
		return splitStatus;
	}
	public void setSplitStatus(Integer splitStatus) {
		this.splitStatus = splitStatus;
	}
	@Override
	public String toString() {
		return "UpdateOrderBO [keyArea=" + keyArea + ", orderId=" + orderId + ", orderStatus=" + orderStatus
				+ ", splitStatus=" + splitStatus + ", deleteOrdeIdList=" + deleteOrdeIdList + ", splitOrders="
				+ splitOrders + ", firstSpiltOrder=" + firstSpiltOrder + ", secondSpiltOrder=" + secondSpiltOrder + "]";
	}
}
