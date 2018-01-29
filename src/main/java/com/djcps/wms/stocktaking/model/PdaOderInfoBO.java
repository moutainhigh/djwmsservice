package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * @title:PDA订单详细信息
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/15
 **/
public class PdaOderInfoBO {
    
    private String relativeId;
    private String partnerId;
    private String warehouseId;
    private String warehouseName;
    private String warehouseAreaId;
    private String warehouseAreaName;
    private String warehouseLocId;
    private String warehouseLocName;
    private String orderId;
    private String productName;
    private String fluteType;
    private String materialId;
    private String materialName;
    private String materialLength;
    private String materialWidth;
    private String boxLength;
    private String boxHeight;
    private String boxWidth;
    private String instockAmount;
    private String differenceValue;
    private String isInventoryProfit;
    private String remark;
    private String operator;
    private String operatorId;
    private String operatorTime;
    private String createTime;
    private String updateTime;
    private String productRule;
    private String materialRule;
    private String units;

    /**
     * 库区list
     */
    private List<WarehouseAreaAndLocBO> orderAreaAndLocList;


    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        units = units;
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

    public String getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(String boxHeight) {
        this.boxHeight = boxHeight;
    }

    public String getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(String boxWidth) {
        this.boxWidth = boxWidth;
    }

    public String getInstockAmount() {
        return instockAmount;
    }

    public void setInstockAmount(String instockAmount) {
        this.instockAmount = instockAmount;
    }


    public String getDifferenceValue() {
        return differenceValue;
    }

    public void setDifferenceValue(String differenceValue) {
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

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<WarehouseAreaAndLocBO> getOrderAreaAndLocList() {
        return orderAreaAndLocList;
    }

    public void setOrderAreaAndLocList(List<WarehouseAreaAndLocBO> orderAreaAndLocList) {
        this.orderAreaAndLocList = orderAreaAndLocList;
    }

    @Override
    public String toString() {
        return "PdaOderInfoBO{" +
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
                ", materialLength='" + materialLength + '\'' +
                ", materialWidth='" + materialWidth + '\'' +
                ", boxLength='" + boxLength + '\'' +
                ", boxHeight='" + boxHeight + '\'' +
                ", boxWidth='" + boxWidth + '\'' +
                ", instockAmount='" + instockAmount + '\'' +
                ", differenceValue='" + differenceValue + '\'' +
                ", isInventoryProfit='" + isInventoryProfit + '\'' +
                ", remark='" + remark + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", operatorTime='" + operatorTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", productRule='" + productRule + '\'' +
                ", materialRule='" + materialRule + '\'' +
                ", Units='" + units + '\'' +
                ", orderAreaAndLocList=" + orderAreaAndLocList +
                '}';
    }
}
