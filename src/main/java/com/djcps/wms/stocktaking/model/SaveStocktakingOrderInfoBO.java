package com.djcps.wms.stocktaking.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @title:保存盘点结果参数类
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/12
 **/
public class SaveStocktakingOrderInfoBO {

    /**
     * 是否正常新增 0正常，1新增，2盘盈
     */
    private Integer isAdd;


    /**
     * 关联编号
     */
    private String relativeId;

    /**
     * 作业id
     */
    private String jobId;

    /**
     * 合作方编号
     */
    private String partnerId;

    /**
     * 合作方区域
     */
    private String partnerArea;

    /**
     * 合作方名称
     */
    private String partnerName;
    /**
     * 仓库编号
     */
    private String warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区编号
     */
    private String warehouseAreaId;
    /**
     * 库区名称
     */
    private String warehouseAreaName;
    /**
     * 库位编号
     */
    private String warehouseLocId;
    /**
     * 库位名称
     */
    private String warehouseLocName;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 楞型
     */
    private String fluteType;
    /**
     * 材料编号
     */
    private String materiaFid;
	/**
	 * 落料长
	 */
	private String materialLength;
	/**
	 * 落料宽
	 */
	private String materialWidth;
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
     * 产品规格
     */
    private String productSize;

    /**
     * 下料规格
     */
    private String materialSize;

    /**
     * 库存数量
     */
    private Integer instockAmount;
    /**
     * 盘点数量
     */
    private Integer takeStockAmount;
    /**
     * 差异量
     */
    private Integer differenceValue;
    /**
     * 是否盘盈1 是，2不是盘盈
     */
    private Integer  isInventoryProfit;

    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人编号
     */
    private String operatorId;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 交期时间
     */
    private Date deliveryTime;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 单位
     */
    private String units;

    /**
     * 盘点员名
     */
    private String inventoryClerk;

    /**
     * 盘点员id
     */
    private String inventoryClerkId;


    /**
     *盘点作业状态 1未完成，3已完成
     */
    private Integer status;

    /**
     * 材料名称
     */
    private String materialName;

    /**
     * 经纬度
     */
    private String lnglat;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 地址街道
     */
    private String addressDetail;

    /**
     * 省市区
     */
    private String codeProvince;


    /**
     * 联系人
     */
    private String consignee;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
	 * 母账号名称
	 */
	private String puserName;
	/**
	 * 子账号名称
	 */
	private String cuserName;

    /**
     * 订单号
     */
    private String childOrderId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单数量
     */
    private Integer orderAmount;

    /**
     * 客户名称
     */
    private String customerName;
    
    public SaveStocktakingOrderInfoBO(String units) {
		super();
		this.units = "片";
	}	

    public SaveStocktakingOrderInfoBO() {
	}
	public Integer getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	public String getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(String relativeId) {
		this.relativeId = relativeId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerArea() {
		return partnerArea;
	}

	public void setPartnerArea(String partnerArea) {
		this.partnerArea = partnerArea;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFluteType() {
		return fluteType;
	}

	public void setFluteType(String fluteType) {
		this.fluteType = fluteType;
	}

	public String getMateriaFid() {
		return materiaFid;
	}

	public void setMateriaFid(String materiaFid) {
		this.materiaFid = materiaFid;
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

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public Integer getInstockAmount() {
		return instockAmount;
	}

	public void setInstockAmount(Integer instockAmount) {
		this.instockAmount = instockAmount;
	}

	public Integer getTakeStockAmount() {
		return takeStockAmount;
	}

	public void setTakeStockAmount(Integer takeStockAmount) {
		this.takeStockAmount = takeStockAmount;
	}

	public Integer getDifferenceValue() {
		return differenceValue;
	}

	public void setDifferenceValue(Integer differenceValue) {
		this.differenceValue = differenceValue;
	}

	public Integer getIsInventoryProfit() {
		return isInventoryProfit;
	}

	public void setIsInventoryProfit(Integer isInventoryProfit) {
		this.isInventoryProfit = isInventoryProfit;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getInventoryClerk() {
		return inventoryClerk;
	}

	public void setInventoryClerk(String inventoryClerk) {
		this.inventoryClerk = inventoryClerk;
	}

	public String getInventoryClerkId() {
		return inventoryClerkId;
	}

	public void setInventoryClerkId(String inventoryClerkId) {
		this.inventoryClerkId = inventoryClerkId;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getLnglat() {
		return lnglat;
	}

	public void setLnglat(String lnglat) {
		this.lnglat = lnglat;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getCodeProvince() {
		return codeProvince;
	}

	public void setCodeProvince(String codeProvince) {
		this.codeProvince = codeProvince;
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
	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "SaveStocktakingOrderInfoBO [isAdd=" + isAdd + ", relativeId=" + relativeId + ", jobId=" + jobId
				+ ", partnerId=" + partnerId + ", partnerArea=" + partnerArea + ", partnerName=" + partnerName
				+ ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName + ", warehouseAreaId="
				+ warehouseAreaId + ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", orderId=" + orderId + ", fluteType=" + fluteType
				+ ", materiaFid=" + materiaFid + ", materialLength=" + materialLength + ", materialWidth="
				+ materialWidth + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight
				+ ", productSize=" + productSize + ", materialSize=" + materialSize + ", instockAmount=" + instockAmount
				+ ", takeStockAmount=" + takeStockAmount + ", differenceValue=" + differenceValue
				+ ", isInventoryProfit=" + isInventoryProfit + ", operator=" + operator + ", operatorId=" + operatorId
				+ ", orderTime=" + orderTime + ", deliveryTime=" + deliveryTime + ", productName=" + productName
				+ ", units=" + units + ", inventoryClerk=" + inventoryClerk + ", inventoryClerkId=" + inventoryClerkId
				+ ", orderStatus=" + status + ", materialName=" + materialName + ", lnglat=" + lnglat
				+ ", payTime=" + payTime + ", addressDetail=" + addressDetail + ", codeProvince=" + codeProvince
				+ ", consignee=" + consignee + ", contactWay=" + contactWay + ", puserName=" + puserName
				+ ", cuserName=" + cuserName + ", childOrderId=" + childOrderId + ", remark=" + remark
				+ ", orderAmount=" + orderAmount + ", customerName=" + customerName + "]";
	}
	
}
