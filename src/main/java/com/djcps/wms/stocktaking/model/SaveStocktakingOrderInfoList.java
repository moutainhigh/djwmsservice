package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * @title:列表保存盘点结果
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/16
 **/
public class SaveStocktakingOrderInfoList {

    private String jobId;

    private String partnerId;

    private String operator;

    private String operatorId;

    private String warehouseId;

    /**
     * 盘点员名
     */
    private String inventoryClerk;

    /**
     * 盘点员id
     */
    private String inventoryClerkId;

    List<SaveStocktakingOrderInfoBO> saveStocktaking;

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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



    public List<SaveStocktakingOrderInfoBO> getSaveStocktaking() {
        return saveStocktaking;
    }

    public void setSaveStocktaking(List<SaveStocktakingOrderInfoBO> saveStocktaking) {
        this.saveStocktaking = saveStocktaking;
    }

    @Override
    public String toString() {
        return "SaveStocktakingOrderInfoList{" +
                "jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                ", saveStocktaking=" + saveStocktaking +
                '}';
    }
}
