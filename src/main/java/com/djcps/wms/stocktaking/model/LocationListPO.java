package com.djcps.wms.stocktaking.model;

public class LocationListPO {

    private Integer trueAmount;

    private String warehouseLocId;

    private String warehouseLocName;

    private Integer allStocktakingTaskAmount;

    private Integer completeStocktakingTaskAmount;

    public Integer getTrueAmount() {
        return trueAmount;
    }

    public void setTrueAmount(Integer trueAmount) {
        this.trueAmount = trueAmount;
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

    public Integer getAllStocktakingTaskAmount() {
        return allStocktakingTaskAmount;
    }

    public void setAllStocktakingTaskAmount(Integer allStocktakingTaskAmount) {
        this.allStocktakingTaskAmount = allStocktakingTaskAmount;
    }

    public Integer getCompleteStocktakingTaskAmount() {
        return completeStocktakingTaskAmount;
    }

    public void setCompleteStocktakingTaskAmount(Integer completeStocktakingTaskAmount) {
        this.completeStocktakingTaskAmount = completeStocktakingTaskAmount;
    }

    @Override
    public String toString() {
        return "LocationListPO{" +
                "trueAmount='" + trueAmount + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", allStocktakingTaskAmount='" + allStocktakingTaskAmount + '\'' +
                ", completeStocktakingTaskAmount='" + completeStocktakingTaskAmount + '\'' +
                '}';
    }
}
