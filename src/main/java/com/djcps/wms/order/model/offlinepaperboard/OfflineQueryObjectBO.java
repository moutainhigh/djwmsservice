package com.djcps.wms.order.model.offlinepaperboard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * 线下纸板订单查询model
 * @company:djwms
 * @author:zdx
 * @date:2018年5月8日
 */
public class OfflineQueryObjectBO implements Serializable{
	
	private static final long serialVersionUID = -4663390483329978061L;

	/**
	 * 当前页
	 */
	@NotNull
	private Integer pageNum;
	
	/**
	 * 条数 
	 */
	@NotNull
	private Integer pageSize;
	
	/**
     * 合作方下id
     */
	@NotEmpty
    private String partnerId;
	
	/**
	 * 合作方名称
	 */
	@NotEmpty
	private String partnerName;
	
	/**
	 * 合作方区域
	 */
	@NotEmpty
	private String partnerArea;
	
	/**
	 * 操作人id
	 */
	@NotEmpty
	private String operatorId;
	
	/**
	 * 操作人名称
	 */
	@NotEmpty
	private String operator;
    
    /**
     * 区域拆分键
     */
	@NotEmpty
    private String keyArea;
    
    /**
     * 订单状态
     */
	@NotNull
    private Integer orderStatus;
    
    /**
     * 全部订单状态
     */
    private List<String> allOrderStatus;
    
    /**
     * 平台订单编号输入框
     */
    private String orderId;
    /**
     * 客户名称输入框
     */
    private String customerName;
    /**
     * 产品名称输入框
     */
    private String productName;
    /**
     * 材料名称输入框
     */
    private String materialName;
    /**
     * 纸箱规格长
     */
    private String boxLength;
    /**
     * 纸箱规格宽
     */
    private String boxWidth;
    /**
     * 纸箱规格高
     */
    private String boxHeight;
    /**
     * 落料长
     */
    private String materialLength;
    /**
     * 落料宽
     */
    private String materialWidth;
    /**
     * 下单开始时间(线下纸板订单没有支付时间)
     */
    @NotEmpty
    private String orderStartTime;
    /**
     * 下单结束时间(线下纸板订单没有支付时间)
     */
    @NotEmpty
    private String orderEndTime;
    /**
     * 交期开始时间
     */
    private String deliveryStartTime;
    /**
     * 交期结束时间
     */
    private String deliveryEndTime;
    
    /**
     * 是否是急单
     */
    private Integer urgencyOrder;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getKeyArea() {
		return keyArea;
	}

	public void setKeyArea(String keyArea) {
		this.keyArea = keyArea;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<String> getAllOrderStatus() {
		return allOrderStatus;
	}

	public void setAllOrderStatus(List<String> allOrderStatus) {
		this.allOrderStatus = allOrderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getBoxLength() {
		return boxLength;
	}

	public void setBoxLength(String boxLength) {
		this.boxLength = boxLength;
	}

	public String getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(String boxWidth) {
		this.boxWidth = boxWidth;
	}

	public String getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(String boxHeight) {
		this.boxHeight = boxHeight;
	}

	public String getMaterialLength() {
		return materialLength;
	}

	public void setMaterialLength(String materialLength) {
		this.materialLength = materialLength;
	}

	public String getMaterialWidth() {
		return materialWidth;
	}

	public void setMaterialWidth(String materialWidth) {
		this.materialWidth = materialWidth;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public Integer getUrgencyOrder() {
		return urgencyOrder;
	}

	public void setUrgencyOrder(Integer urgencyOrder) {
		this.urgencyOrder = urgencyOrder;
	}

	@Override
	public String toString() {
		return "OfflineQueryObjectBO [pageNum=" + pageNum + ", pageSize=" + pageSize + ", partnerId=" + partnerId
				+ ", partnerName=" + partnerName + ", partnerArea=" + partnerArea + ", operatorId=" + operatorId
				+ ", operator=" + operator + ", keyArea=" + keyArea + ", orderStatus=" + orderStatus
				+ ", allOrderStatus=" + allOrderStatus + ", orderId=" + orderId + ", customerName=" + customerName
				+ ", productName=" + productName + ", materialName=" + materialName + ", boxLength=" + boxLength
				+ ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight + ", materialLength=" + materialLength
				+ ", materialWidth=" + materialWidth + ", orderStartTime=" + orderStartTime + ", orderEndTime="
				+ orderEndTime + ", deliveryStartTime=" + deliveryStartTime + ", deliveryEndTime=" + deliveryEndTime
				+ ", urgencyOrder=" + urgencyOrder + "]";
	}
    
}
