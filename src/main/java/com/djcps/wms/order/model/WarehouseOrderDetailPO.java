package com.djcps.wms.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;



/**
 * 纸板映射对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseOrderDetailPO implements Serializable{

	private static final long serialVersionUID = -4276569892759744580L;
	
	private String fdblflag;
	
	private String unit;
	
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
	 * 平台订单编号
	 */
	private String childOrderId;
	/**
	 * 订单状态
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
	 * 材料id
	 */
	private String materiaFid;
	
	/**
	 * 材料名称
	 */
	private String materialName;
	/**
	 * 楞型(线上纸板订单取来的楞型是中文,线下纸板和纸箱取来的是数字)
	 */
	private String fluteType;
	
	/**
	 * 楞型的转换字符串
	 */
	private String fluteTypeString;
	/**
	 * 只数(库存信息中会订单数量用的是amount字段,统一都用orderAmount表示订单数量)
	 */
//	private Integer amount;
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
	private Integer splitOrder;
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
	
	/**
	 * ========================拆单属性==============================
	 */
	
	 /**
     * 拆单号
     */
    private String subOrderId;
    /**
     * 拆单数量
     */
    private int subNumber;
    /**
     * 拆单状态
     */
    private Integer subStatus;
    /**
     * 拆单地址
     */
    private String subAddress;
    /**
     *出库数量
     */
    private Integer outStock;
    /**
     * 是入库数量
     */
    private Integer inStock;
    /**
     *是否异常 1有  0无
     */
    private int isException;
    /**
     * 是否备货 1有  0无
     */
    private Integer isStored;
    /**
     * 是否生产 1有  0无
     */
    private Integer isProduce;

	/**
	 * ===============================在库信息属性=============================
	 */
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 已入库数量
	 */
	private Integer amountSaved;
	
	/**
	 * 订单数量
	 */
	private Integer orderAmount;
	
	/**
	 * 备注
	 */
	private String remark;
	
	  /**
	   * 库存数量
	   */
	private Integer  instockAmount;
	
	/**
	 * 库区list
	 */
	private List<WarehouseAreaBO> areaList;
	
	/**
	 * =====================================================================
	 */
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	
	/**
	 * 提货单状态
	 */
	private String deliveryIdStatus;
	/**
	 * 装车顺序
	 */
	private Integer sequence;
	
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	
	/**
	 * 提货数量
	 */
	private Integer deliveryAmount;
	
	/**
	 * =======================================================
	 */
	
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 库区编码
	 */
	private String warehouseAreaId;
	/**
	 * 库区名称
	 */
	private String warehouseAreaName;
	/**
	 * 库位编码
	 */
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	private String warehouseLocName;
	
	/**
	 * 智能配货id
	 */
	private String allocationId;
	
	
	
	public WarehouseOrderDetailPO() {
		super();
		this.unit = "片";
	}

	public String getFdblflag() {
		return fdblflag;
	}

	public void setFdblflag(String fdblflag) {
		this.fdblflag = fdblflag;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getAmountSaved() {
		return amountSaved;
	}

	public void setAmountSaved(Integer amountSaved) {
		this.amountSaved = amountSaved;
	}

//	public Integer getAmount() {
//		return amount;
//	}
//
//	public void setAmount(Integer amount) {
//		this.amount = amount;
//	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getInstockAmount() {
		return instockAmount;
	}

	public void setInstockAmount(Integer instockAmount) {
		this.instockAmount = instockAmount;
	}

	public List<WarehouseAreaBO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBO> areaList) {
		this.areaList = areaList;
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

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getDeliveryIdStatus() {
		return deliveryIdStatus;
	}

	public void setDeliveryIdStatus(String deliveryIdStatus) {
		this.deliveryIdStatus = deliveryIdStatus;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getLoadingPersonId() {
		return loadingPersonId;
	}

	public void setLoadingPersonId(String loadingPersonId) {
		this.loadingPersonId = loadingPersonId;
	}

	public String getLoadingPersonName() {
		return loadingPersonName;
	}

	public void setLoadingPersonName(String loadingPersonName) {
		this.loadingPersonName = loadingPersonName;
	}

	public Integer getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(Integer deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	public String getWarehouseAreaName() {
		return warehouseAreaName;
	}

	public void setWarehouseAreaName(String warehouseAreaName) {
		this.warehouseAreaName = warehouseAreaName;
	}

	public String getWarehouseLocId() {
		return warehouseLocId;
	}

	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}

	public String getWarehouseLocName() {
		return warehouseLocName;
	}

	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
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

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getFluteType() {
		return fluteType;
	}

	public void setFluteType(String fluteType) {
		this.fluteType = fluteType;
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
		this.excpStatus = 0;
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

	public Integer getSplitOrder() {
		return splitOrder;
	}

	public void setSplitOrder(Integer splitOrder) {
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

	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	public int getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(int subNumber) {
		this.subNumber = subNumber;
	}

	public Integer getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(Integer subStatus) {
		this.subStatus = subStatus;
	}

	public String getSubAddress() {
		return subAddress;
	}

	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}

	public Integer getOutStock() {
		return outStock;
	}

	public void setOutStock(Integer outStock) {
		this.outStock = outStock;
	}

	public Integer getInStock() {
		return inStock;
	}

	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	public int getIsException() {
		return isException;
	}

	public void setIsException(int isException) {
		this.isException = isException;
	}

	public Integer getIsStored() {
		return isStored;
	}

	public void setIsStored(Integer isStored) {
		this.isStored = isStored;
	}

	public Integer getIsProduce() {
		return isProduce;
	}

	public void setIsProduce(Integer isProduce) {
		this.isProduce = isProduce;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMateriaFid() {
		return materiaFid;
	}

	public void setMateriaFid(String materiaFid) {
		this.materiaFid = materiaFid;
	}

	public String getFluteTypeString() {
		return fluteTypeString;
	}

	public void setFluteTypeString(String fluteTypeString) {
		this.fluteTypeString = fluteTypeString;
	}

	@Override
	public String toString() {
		return "WarehouseOrderDetailPO [fdblflag=" + fdblflag + ", unit=" + unit + ", keyArea=" + keyArea
				+ ", partnerId=" + partnerId + ", childOrderId=" + childOrderId + ", orderStatus=" + orderStatus
				+ ", orderTime=" + orderTime + ", payTime=" + payTime + ", deliveryTime=" + deliveryTime
				+ ", productName=" + productName + ", materiaFid=" + materiaFid + ", materialName=" + materialName
				+ ", fluteType=" + fluteType + ", fluteTypeString=" + fluteTypeString + ", amountPiece=" + amountPiece
				+ ", series=" + series + ", boxModel=" + boxModel + ", boxLength=" + boxLength + ", boxWidth="
				+ boxWidth + ", boxHeight=" + boxHeight + ", materialLength=" + materialLength + ", materialWidth="
				+ materialWidth + ", staveType=" + staveType + ", hline=" + hline + ", vline=" + vline + ", newVline="
				+ newVline + ", hlineFormula=" + hlineFormula + ", vlineFormula=" + vlineFormula + ", productArea="
				+ productArea + ", unitPrice=" + unitPrice + ", amountPrice=" + amountPrice + ", codeProvince="
				+ codeProvince + ", partnerName=" + partnerName + ", customerName=" + customerName + ", consignee="
				+ consignee + ", contactWay=" + contactWay + ", addressDetail=" + addressDetail + ", urgencyOrder="
				+ urgencyOrder + ", excpStatus=" + excpStatus + ", goKeyArea=" + goKeyArea + ", marktingplanId="
				+ marktingplanId + ", userId=" + userId + ", productionNo=" + productionNo + ", splitOrder="
				+ splitOrder + ", puserName=" + puserName + ", cuserName=" + cuserName + ", lnglat=" + lnglat
				+ ", materialSize=" + materialSize + ", productSize=" + productSize + ", addressDetailProvince="
				+ addressDetailProvince + ", subOrderId=" + subOrderId + ", subNumber=" + subNumber + ", subStatus="
				+ subStatus + ", subAddress=" + subAddress + ", outStock=" + outStock + ", inStock=" + inStock
				+ ", isException=" + isException + ", isStored=" + isStored + ", isProduce=" + isProduce + ", orderId="
				+ orderId + ", amountSaved=" + amountSaved + ", orderAmount=" + orderAmount + ", remark=" + remark
				+ ", instockAmount=" + instockAmount + ", areaList=" + areaList + ", waybillId=" + waybillId
				+ ", deliveryId=" + deliveryId + ", plateNumber=" + plateNumber + ", deliveryIdStatus="
				+ deliveryIdStatus + ", sequence=" + sequence + ", pickerId=" + pickerId + ", pickerName=" + pickerName
				+ ", loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName
				+ ", deliveryAmount=" + deliveryAmount + ", warehouseId=" + warehouseId + ", warehouseName="
				+ warehouseName + ", warehouseAreaId=" + warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName
				+ ", warehouseLocId=" + warehouseLocId + ", warehouseLocName=" + warehouseLocName + ", allocationId="
				+ allocationId + "]";
	}
	
}
