package com.djcps.wms.stocktaking.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 不带f的订单详细参数
 * 
 * @author wzy
 * @param
 * @return
 * @date 2018/2/2 14:05
 **/
public class OrderInfoBO {

	private String unit;

	private String orderId;

	/**
	 * ================子订单属性======================
	 */

	/**
	 * 区域中间键
	 */
	private Integer keyArea;
	/**
	 * 合作方id
	 */
	private String partnerId;

	/**
	 * 材料id
	 */
	private String materiaFid;

	/**
	 * 平台订单编号
	 */
	private String childOrderId;
	/**
	 * 状态
	 */
	private Integer orderStatus;
	/**
	 * 下单时间
	 */
	private Date orderTime;

	/**
	 * 支付时间
	 */
	private Date payTime;
	/**
	 * 交期
	 */
	private Date deliveryTime;
	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 材料名称
	 */
	private String materialName;
	/**
	 * 楞型
	 */
	private String fluteType;
	/**
	 * 只数
	 */
	private Integer amount;
	/**
	 * 片数
	 */
	private Integer amountPiece;
	/**
	 * 连做方式
	 */
	private String series;
	/**
	 * 箱型
	 */
	private Integer boxModel;
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
	 * 压线方式
	 */
	private String staveType;
	/**
	 * 横向压线
	 */
	private String hline;
	/**
	 * 纵向压线
	 */
	private String vline;
	/**
	 * 纵向压线（1位小数）
	 */
	private String newVline;
	/**
	 * 横压公式
	 */
	private String hlineFormula;
	/**
	 * 纵压公式
	 */
	private String vlineFormula;
	/**
	 * 面积
	 */
	private BigDecimal productArea;
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	/**
	 * 金额
	 */
	private BigDecimal amountPrice;
	/**
	 * 区域
	 */
	private String codeProvince;
	/**
	 * 合作方名称
	 */
	private String partnerName;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 收货地址
	 */
	private String addressDetail;
	/**
	 * 是否急单 1：是 0：否
	 */
	private Integer urgencyOrder;
	/**
	 * 异常订单状态
	 */
	private Integer excpStatus;
	/**
	 * 表的区域拆分键
	 */
	private int goKeyArea;
	/**
	 * 营销方案
	 */
	private String marktingplanId;
	/**
	 * 母账号用户ID
	 */
	private String userId;

	/**
	 * 生产订单编号
	 */
	private String productionNo;

	/**
	 * 拆单状态
	 */
	private int splitOrder;
	/**
	 * 母账号名称
	 */
	private String puserName;
	/**
	 * 子账号名称
	 */
	private String cuserName;
	/**
	 * 经纬度
	 */
	private String lnglat;

	// =======================

	/**
	 * 提货单号
	 */
	private String deliveryOrder;

	/**
	 * 订单数量
	 */
	private Integer orderAmount;

	/**
	 * ========================wms自己增加的订单属性==============================
	 */

	/**
	 * 下料规格
	 */
	private String materialSize;

	/**
	 * 产品规格
	 */
	private String productSize;

	/**
	 * 省市区外加地址详情拼接
	 */
	private String addressDetailProvince;

	public OrderInfoBO() {
		super();
		this.unit = "片";
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getKeyArea() {
		return keyArea;
	}

	public void setKeyArea(Integer keyArea) {
		this.keyArea = keyArea;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMateriaFid() {
		return materiaFid;
	}

	public void setMateriaFid(String materiaFid) {
		this.materiaFid = materiaFid;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAmountPiece() {
		return amountPiece;
	}

	public void setAmountPiece(Integer amountPiece) {
		this.amountPiece = amountPiece;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Integer getBoxModel() {
		return boxModel;
	}

	public void setBoxModel(Integer boxModel) {
		this.boxModel = boxModel;
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

	public String getStaveType() {
		return staveType;
	}

	public void setStaveType(String staveType) {
		this.staveType = staveType;
	}

	public String getHline() {
		return hline;
	}

	public void setHline(String hline) {
		this.hline = hline;
	}

	public String getVline() {
		return vline;
	}

	public void setVline(String vline) {
		this.vline = vline;
	}

	public String getNewVline() {
		return newVline;
	}

	public void setNewVline(String newVline) {
		this.newVline = newVline;
	}

	public String getHlineFormula() {
		return hlineFormula;
	}

	public void setHlineFormula(String hlineFormula) {
		this.hlineFormula = hlineFormula;
	}

	public String getVlineFormula() {
		return vlineFormula;
	}

	public void setVlineFormula(String vlineFormula) {
		this.vlineFormula = vlineFormula;
	}

	public BigDecimal getProductArea() {
		return productArea;
	}

	public void setProductArea(BigDecimal productArea) {
		this.productArea = productArea;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(BigDecimal amountPrice) {
		this.amountPrice = amountPrice;
	}

	public String getCodeProvince() {
		return codeProvince;
	}

	public void setCodeProvince(String codeProvince) {
		this.codeProvince = codeProvince;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public Integer getUrgencyOrder() {
		return urgencyOrder;
	}

	public void setUrgencyOrder(Integer urgencyOrder) {
		this.urgencyOrder = urgencyOrder;
	}

	public Integer getExcpStatus() {
		return excpStatus;
	}

	public void setExcpStatus(Integer excpStatus) {
		this.excpStatus = excpStatus;
	}

	public int getGoKeyArea() {
		return goKeyArea;
	}

	public void setGoKeyArea(int goKeyArea) {
		this.goKeyArea = goKeyArea;
	}

	public String getMarktingplanId() {
		return marktingplanId;
	}

	public void setMarktingplanId(String marktingplanId) {
		this.marktingplanId = marktingplanId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductionNo() {
		return productionNo;
	}

	public void setProductionNo(String productionNo) {
		this.productionNo = productionNo;
	}

	public int getSplitOrder() {
		return splitOrder;
	}

	public void setSplitOrder(int splitOrder) {
		this.splitOrder = splitOrder;
	}

	public String getPuserName() {
		return puserName;
	}

	public void setPuserName(String puserName) {
		this.puserName = puserName;
	}

	public String getCuserName() {
		return cuserName;
	}

	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public String getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(String deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getAddressDetailProvince() {
		return addressDetailProvince;
	}

	public void setAddressDetailProvince(String addressDetailProvince) {
		this.addressDetailProvince = addressDetailProvince;
	}

	public String getFluteType() {
		return fluteType;
	}

	public void setFluteType(String fluteType) {
		this.fluteType = fluteType;
	}

	@Override
	public String toString() {
		return "OrderInfoBO [unit=" + unit + ", orderId=" + orderId + ", keyArea=" + keyArea + ", partnerId="
				+ partnerId + ", materiaFid=" + materiaFid + ", childOrderId=" + childOrderId + ", orderStatus="
				+ orderStatus + ", orderTime=" + orderTime + ", payTime=" + payTime + ", deliveryTime=" + deliveryTime
				+ ", productName=" + productName + ", materialName=" + materialName + ", fluteType=" + fluteType
				+ ", amount=" + amount + ", amountPiece=" + amountPiece + ", series=" + series + ", boxModel="
				+ boxModel + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight
				+ ", materialLength=" + materialLength + ", materialWidth=" + materialWidth + ", staveType=" + staveType
				+ ", hline=" + hline + ", vline=" + vline + ", newVline=" + newVline + ", hlineFormula=" + hlineFormula
				+ ", vlineFormula=" + vlineFormula + ", productArea=" + productArea + ", unitPrice=" + unitPrice
				+ ", amountPrice=" + amountPrice + ", codeProvince=" + codeProvince + ", partnerName=" + partnerName
				+ ", customerName=" + customerName + ", consignee=" + consignee + ", contactWay=" + contactWay
				+ ", addressDetail=" + addressDetail + ", urgencyOrder=" + urgencyOrder + ", excpStatus=" + excpStatus
				+ ", goKeyArea=" + goKeyArea + ", marktingplanId=" + marktingplanId + ", userId=" + userId
				+ ", productionNo=" + productionNo + ", splitOrder=" + splitOrder + ", puserName=" + puserName
				+ ", cuserName=" + cuserName + ", lnglat=" + lnglat + ", deliveryOrder=" + deliveryOrder
				+ ", orderAmount=" + orderAmount + ", materialSize=" + materialSize + ", productSize=" + productSize
				+ ", addressDetailProvince=" + addressDetailProvince + "]";
	}


}
