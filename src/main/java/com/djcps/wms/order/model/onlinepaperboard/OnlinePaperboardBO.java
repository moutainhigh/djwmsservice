package com.djcps.wms.order.model.onlinepaperboard;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 线上纸板订单查询对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class OnlinePaperboardBO extends BaseBO implements Serializable{
   
	private static final long serialVersionUID = 778949046213494674L;
	
    /**
     * 合作方下id
     */
    @NotBlank
    private String partnerId;
	
	/**
	 * 合作方名称
	 */
	private String partnerName;
	
	/**
	 * 合作方区域
	 */
	private String partnerArea;
	
	/**
	 * 操作人id
	 */
	private String operatorId;
	
	/**
	 * 操作人名称
	 */
	private String operator;
    
    /**
     * 区域下拉框
     */
    @NotNull
    private Integer keyArea;
    
    /**
     * 状态下拉框
     */
    private Integer orderStatus;
    /**
     * 平台订单编号输入框
     */
    private String childOrderId;
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
    private BigDecimal boxLength;
    /**
     * 纸箱规格宽
     */
    private BigDecimal boxWidth;
    /**
     * 纸箱规格高
     */
    private BigDecimal boxHeight;
    /**
     * 落料长
     */
    private BigDecimal materialLength;
    /**
     * 落料宽
     */
    private BigDecimal materialWidth;
    /**
     * 下单开始时间
     */
    private String orderStartTime;
    /**
     * 下单结束时间
     */
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
     * 是否是支付时间 1:选择支付时间 2：下单时间
     */
    private Integer isPayOrOrderTime;
    /**
     * 是否是急单
     */
    private Integer urgencyOrder;
    /**
     * 异常订单状态
     */
    private Integer excpStatus;
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
	public Integer getKeyArea() {
		return keyArea;
	}
	public void setKeyArea(Integer keyArea) {
		this.keyArea = keyArea;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getChildOrderId() {
		return childOrderId;
	}
	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
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
	public BigDecimal getBoxLength() {
		return boxLength;
	}
	public void setBoxLength(BigDecimal boxLength) {
		this.boxLength = boxLength;
	}
	public BigDecimal getBoxWidth() {
		return boxWidth;
	}
	public void setBoxWidth(BigDecimal boxWidth) {
		this.boxWidth = boxWidth;
	}
	public BigDecimal getBoxHeight() {
		return boxHeight;
	}
	public void setBoxHeight(BigDecimal boxHeight) {
		this.boxHeight = boxHeight;
	}
	public BigDecimal getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(BigDecimal materialLength) {
		this.materialLength = materialLength;
	}
	public BigDecimal getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(BigDecimal materialWidth) {
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
	public Integer getIsPayOrOrderTime() {
		return isPayOrOrderTime;
	}
	public void setIsPayOrOrderTime(Integer isPayOrOrderTime) {
		this.isPayOrOrderTime = isPayOrOrderTime;
	}
	public Integer getUrgencyOrder() {
		return urgencyOrder;
	}
	public void setUrgencyOrder(Integer urgencyOrder) {
		this.urgencyOrder = urgencyOrder;
	}
	public Integer getExcpStatus() {
		this.excpStatus = 0;
		return excpStatus;
	}
	public void setExcpStatus(Integer excpStatus) {
		this.excpStatus = excpStatus;
	}
	@Override
	public String toString() {
		return "OnlinePaperboardBO [partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea="
				+ partnerArea + ", operatorId=" + operatorId + ", operator=" + operator + ", keyArea=" + keyArea
				+ ", orderStatus=" + orderStatus + ", childOrderId=" + childOrderId + ", customerName=" + customerName
				+ ", productName=" + productName + ", materialName=" + materialName + ", boxLength=" + boxLength
				+ ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight + ", materialLength=" + materialLength
				+ ", materialWidth=" + materialWidth + ", orderStartTime=" + orderStartTime + ", orderEndTime="
				+ orderEndTime + ", deliveryStartTime=" + deliveryStartTime + ", deliveryEndTime=" + deliveryEndTime
				+ ", isPayOrOrderTime=" + isPayOrOrderTime + ", urgencyOrder=" + urgencyOrder + ", excpStatus="
				+ excpStatus + "]";
	}

}
