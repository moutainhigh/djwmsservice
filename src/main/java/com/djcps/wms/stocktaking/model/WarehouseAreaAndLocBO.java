package com.djcps.wms.stocktaking.model;

/**
 * @title:PDA请求订单详情时库区库位信息
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/15
 **/
public class WarehouseAreaAndLocBO {
    private Integer isInventoryProfit;
    private String trueAmount;
    private String warehouseAreaId;
    private String  warehouseAreaName;
    private String  warehouseLocId;
    private String  warehouseLocName;
    private String remark;
    /**
     * 盘点数量
     */
    private String takeStockAmount;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsInventoryProfit() {
        return isInventoryProfit;
    }

    public void setIsInventoryProfit(Integer isInventoryProfit) {
        this.isInventoryProfit = isInventoryProfit;
    }

    public String getTakeStockAmount() {
        return takeStockAmount;
    }

    public void setTakeStockAmount(String takeStockAmount) {
        this.takeStockAmount = takeStockAmount;
    }

    public String getTrueAmount() {
        return trueAmount;
    }

    public void setTrueAmount(String trueAmount) {
        this.trueAmount = trueAmount;
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

    @Override
    public String toString() {
        return "WarehouseAreaAndLocBO{" +
                "isInventoryProfit=" + isInventoryProfit +
                ", trueAmount='" + trueAmount + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", remark='" + remark + '\'' +
                ", takeStockAmount='" + takeStockAmount + '\'' +
                '}';
    }
}
