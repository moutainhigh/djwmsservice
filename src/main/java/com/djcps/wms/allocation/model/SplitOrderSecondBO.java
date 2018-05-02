package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.Date;

import com.djcps.wms.commons.model.PartnerInfoBO;

/**
 * 第二条拆单信息
 * @company:djwms
 * @author:zdx
 * @date:2018年4月18日
 */
public class SplitOrderSecondBO extends PartnerInfoBO implements Serializable{
	
	private static final long serialVersionUID = -8563547511754108853L;

	/**
	 * 第二条拆分订单
	 */
	private String secondOrder;
	
	private Integer secondOrderStatus;
	
	private Integer secondIsSplit;
	
	/**
	 * 订单数量
	 */
	private Integer secondOrderAmount;
	
	//插入t_cborder_redundant所需要的参数
	
	private String waybillId;
	private String deliveryId;
	private Integer status;
	private Integer redundantIsSplit;
	private String orderId;
	private String customerName;
	private String productName;
	private String materialName;
	private String materialLength;
	private String materialWidth;
	private String boxLength;
	private String boxWidth;
	private String boxHeight;
	private Date orderTime;
	private Date deliveryTime;
	private Date paymentTime;
	private String plateNumber;
	
	//================t_cborder_inventory数据===================
	
	private String partnerid; 
	private String partnername;
	private String partnerarea;
	private String id;
	private String orderid;
	private String splitorderid;
	private Integer inventoryIsSplit;
	private Integer amount;
	private Integer amountsaved;
	private Integer amountinstock;
	private Integer amountoutstock;
	private String warehouseid;
	private String warehousename;
	private String remark;
	private String operatorid;
	private String operator;
	public String getSecondOrder() {
		return secondOrder;
	}
	public void setSecondOrder(String secondOrder) {
		this.secondOrder = secondOrder;
	}
	public Integer getSecondOrderStatus() {
		return secondOrderStatus;
	}
	public void setSecondOrderStatus(Integer secondOrderStatus) {
		this.secondOrderStatus = secondOrderStatus;
	}
	public Integer getSecondIsSplit() {
		return secondIsSplit;
	}
	public void setSecondIsSplit(Integer secondIsSplit) {
		this.secondIsSplit = secondIsSplit;
	}
	public Integer getSecondOrderAmount() {
		return secondOrderAmount;
	}
	public void setSecondOrderAmount(Integer secondOrderAmount) {
		this.secondOrderAmount = secondOrderAmount;
	}
	public String getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRedundantIsSplit() {
		return redundantIsSplit;
	}
	public void setRedundantIsSplit(Integer redundantIsSplit) {
		this.redundantIsSplit = redundantIsSplit;
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
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPartnername() {
		return partnername;
	}
	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}
	public String getPartnerarea() {
		return partnerarea;
	}
	public void setPartnerarea(String partnerarea) {
		this.partnerarea = partnerarea;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getSplitorderid() {
		return splitorderid;
	}
	public void setSplitorderid(String splitorderid) {
		this.splitorderid = splitorderid;
	}
	public Integer getInventoryIsSplit() {
		return inventoryIsSplit;
	}
	public void setInventoryIsSplit(Integer inventoryIsSplit) {
		this.inventoryIsSplit = inventoryIsSplit;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmountsaved() {
		return amountsaved;
	}
	public void setAmountsaved(Integer amountsaved) {
		this.amountsaved = amountsaved;
	}
	public Integer getAmountinstock() {
		return amountinstock;
	}
	public void setAmountinstock(Integer amountinstock) {
		this.amountinstock = amountinstock;
	}
	public Integer getAmountoutstock() {
		return amountoutstock;
	}
	public void setAmountoutstock(Integer amountoutstock) {
		this.amountoutstock = amountoutstock;
	}
	public String getWarehouseid() {
		return warehouseid;
	}
	public void setWarehouseid(String warehouseid) {
		this.warehouseid = warehouseid;
	}
	public String getWarehousename() {
		return warehousename;
	}
	public void setWarehousename(String warehousename) {
		this.warehousename = warehousename;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		return "SplitOrderSecondBO [secondOrder=" + secondOrder + ", secondOrderStatus=" + secondOrderStatus
				+ ", secondIsSplit=" + secondIsSplit + ", secondOrderAmount=" + secondOrderAmount + ", waybillId="
				+ waybillId + ", deliveryId=" + deliveryId + ", status=" + status + ", redundantIsSplit="
				+ redundantIsSplit + ", orderId=" + orderId + ", customerName=" + customerName + ", productName="
				+ productName + ", materialName=" + materialName + ", materialLength=" + materialLength
				+ ", materialWidth=" + materialWidth + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth
				+ ", boxHeight=" + boxHeight + ", orderTime=" + orderTime + ", deliveryTime=" + deliveryTime
				+ ", paymentTime=" + paymentTime + ", plateNumber=" + plateNumber + ", partnerid=" + partnerid
				+ ", partnername=" + partnername + ", partnerarea=" + partnerarea + ", id=" + id + ", orderid="
				+ orderid + ", splitorderid=" + splitorderid + ", inventoryIsSplit=" + inventoryIsSplit + ", amount="
				+ amount + ", amountsaved=" + amountsaved + ", amountinstock=" + amountinstock + ", amountoutstock="
				+ amountoutstock + ", warehouseid=" + warehouseid + ", warehousename=" + warehousename + ", remark="
				+ remark + ", operatorid=" + operatorid + ", operator=" + operator + "]";
	}
	
}
