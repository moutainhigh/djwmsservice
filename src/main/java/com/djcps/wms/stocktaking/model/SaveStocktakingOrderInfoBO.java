package com.djcps.wms.stocktaking.model;

import javax.validation.constraints.NotNull;
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
     * 是否正常新增 1是，2不是
     */
    private String isAdd;

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
     * 产品名称
     */
    private String productName;
    /**
     * 楞型
     */
    private String fluteType;
    /**
     * 材料编号
     */
    private String materialId;
    /**
     * 材料名称
     */
    private String materialName;
    /**
     * 下料长
     */
    private Double materialLength;
    /**
     * 下料宽
     */
    private Double materialWidth;
    /**
     * 产品规格长
     */
    private Double boxLength;
    /**
     * 产品规格高
     */
    private Double boxHeight;
    /**
     * 产品规格宽
     */
    private Double boxWidth;

    /**
     * 产品规格
     */
    private String productRule;

    /**
     * 下料规格
     */
    private String materialRule;

    /**
     * 库存数量
     */
    private Integer instockAmount;
    /**
     * 盘点数量
     */
    @NotNull
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
    private Date ordertime;

    /**
     * 交期时间
     */
    private Date delivery;

    /**
     * 产品名称
     */
    private String groupgoodname;

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
     * 订单状态(
     *	已付款:2
     *	已分发:3
     * 	部分入库：21
     已入库：22
     已配货：23
     已提货：24
     已装车：25
     已发车：26
     )
     */
    private String status;

    /**
     * 材料名称
     */
    private String materialname;

    /**
     * 经纬度
     */
    private String lnglat;

    /**
     * 支付时间
     */
    private Date paymenttime;

    /**
     * 地址街道
     */
    private String addressdetail;

    /**
     * 省市区
     */
    private String codeprovince;


    /**
     * 联系人
     */
    private String consignee;

    /**
     * 联系方式
     */
    private String contactway;

    /**
     * 客户名称
     */
    private String pusername;

    /**
     * 订单号
     */
    private String childorderid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单数量
     */
    private String amount;


    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway;
    }

    public String getPusername() {
        return pusername;
    }

    public void setPusername(String pusername) {
        this.pusername = pusername;
    }

    public String getChildorderid() {
        return childorderid;
    }

    public void setChildorderid(String childorderid) {
        this.childorderid = childorderid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCodeprovince() {
        return codeprovince;
    }

    public void setCodeprovince(String codeprovince) {
        this.codeprovince = codeprovince;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupgoodname() {
        return groupgoodname;
    }

    public void setGroupgoodname(String groupgoodname) {
        this.groupgoodname = groupgoodname;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public String getProductRule() {
        return productRule;
    }

    public void setProductRule(String productRule) {
        this.productRule = productRule;
    }

    public String getMaterialRule() {
        return materialRule;
    }

    public void setMaterialRule(String materialRule) {
        this.materialRule = materialRule;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFluteType() {
        return fluteType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Double getMaterialLength() {
        return materialLength;
    }

    public void setMaterialLength(Double materialLength) {
        this.materialLength = materialLength;
    }

    public Double getMaterialWidth() {
        return materialWidth;
    }

    public void setMaterialWidth(Double materialWidth) {
        this.materialWidth = materialWidth;
    }

    public Double getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(Double boxLength) {
        this.boxLength = boxLength;
    }

    public Double getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(Double boxHeight) {
        this.boxHeight = boxHeight;
    }

    public Double getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(Double boxWidth) {
        this.boxWidth = boxWidth;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
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

    public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }

    @Override
    public String toString() {
        return "SaveStocktakingOrderInfoBO{" +
                "isAdd='" + isAdd + '\'' +
                ", relativeId='" + relativeId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productName='" + productName + '\'' +
                ", fluteType='" + fluteType + '\'' +
                ", materialId='" + materialId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialLength=" + materialLength +
                ", materialWidth=" + materialWidth +
                ", boxLength=" + boxLength +
                ", boxHeight=" + boxHeight +
                ", boxWidth=" + boxWidth +
                ", productRule='" + productRule + '\'' +
                ", materialRule='" + materialRule + '\'' +
                ", instockAmount=" + instockAmount +
                ", takeStockAmount=" + takeStockAmount +
                ", differenceValue=" + differenceValue +
                ", isInventoryProfit=" + isInventoryProfit +
                ", operator='" + operator + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", ordertime=" + ordertime +
                ", delivery=" + delivery +
                ", groupgoodname='" + groupgoodname + '\'' +
                ", units='" + units + '\'' +
                ", inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                ", status='" + status + '\'' +
                ", materialname='" + materialname + '\'' +
                ", lnglat='" + lnglat + '\'' +
                ", paymenttime=" + paymenttime +
                ", addressdetail='" + addressdetail + '\'' +
                ", codeprovince='" + codeprovince + '\'' +
                ", consignee='" + consignee + '\'' +
                ", contactway='" + contactway + '\'' +
                ", pusername='" + pusername + '\'' +
                ", childorderid='" + childorderid + '\'' +
                ", remark='" + remark + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
