package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @title:请求pda盘点任务列表
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class PdaStocktakingTaskBO implements Serializable{


    private static final long serialVersionUID = -5335993772730122216L;

    /**
     * 盘点员
     */
    private String inventoryClerk;
    /**
     * 盘点员编号
     */
    private String inventoryClerkId;

    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;

    /**
     * 作业单号
     */
    private String jobId;

    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;

    public String getInventoryClerk() {
        return inventoryClerk;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getInventoryClerkId() {
        return inventoryClerkId;
    }

    public void setInventoryClerkId(String inventoryClerkId) {
        this.inventoryClerkId = inventoryClerkId;
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

    @Override
    public String toString() {
        return "PdaStocktakingTaskBO{" +
                "inventoryClerk='" + inventoryClerk + '\'' +
                ", inventoryClerkId='" + inventoryClerkId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}
