package com.djcps.wms.stocktaking.model;

import javax.validation.constraints.NotNull;

/**
 * @title:保存盘点结果参数类
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/12
 **/
public class SaveStocktakingOrderInfoBO {
    /**
     * 关联编号
     */
    private String relativeId;

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
     * 是否盘盈
     */
    private String isInventoryProfit;
    /**
     * 备注
     */
    private String remark;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作人编号
     */
    private String operatorId;

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

    public String getIsInventoryProfit() {
        return isInventoryProfit;
    }

    public void setIsInventoryProfit(String isInventoryProfit) {
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

    @Override
    public String toString() {
        return "SaveStocktakingOrderInfoBO{" +
                "relativeId='" + relativeId + '\'' +
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
                ", instockAmount=" + instockAmount +
                ", takeStockAmount=" + takeStockAmount +
                ", differenceValue=" + differenceValue +
                ", isInventoryProfit='" + isInventoryProfit + '\'' +
                ", remark='" + remark + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorId='" + operatorId + '\'' +
                '}';
    }
}
