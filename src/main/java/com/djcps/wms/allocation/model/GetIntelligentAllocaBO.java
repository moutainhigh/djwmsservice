package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;

/**
 *	智能配货结果
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class GetIntelligentAllocaBO extends BaseBO implements Serializable{

	private static final long serialVersionUID = 3848765538827058096L;
	
	/**
	 * 配货结果
	 */
	private String effect;
	
	/**
	 * 提货单号
	 */
	private String deliveryId;
	
	/**
	 * 订单状态
	 */
	@NotBlank
	private String orderStatus;
	/**
	 * 提醒
	 */
	@NotBlank
	private String remind;
	
	/**
	 * 仓库类型,1,2,3,4,5(纸板，纸箱，积分商城仓库，物料仓库，退货仓库)
	 */
	@NotBlank
	private String warehouseType;
	/**
	 * 仓库编码
	 */
	@NotBlank
	private String warehouseId;
	
	
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRemind() {
		return remind;
	}
	public void setRemind(String remind) {
		this.remind = remind;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	@Override
	public String toString() {
		return "GetIntelligentAllocaBo [effect=" + effect + ", deliveryId=" + deliveryId + ", orderStatus="
				+ orderStatus + ", remind=" + remind + ", warehouseType=" + warehouseType + ", warehouseId="
				+ warehouseId + "]";
	}
	
}
