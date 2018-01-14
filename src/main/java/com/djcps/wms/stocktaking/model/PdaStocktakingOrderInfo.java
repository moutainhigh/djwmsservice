package com.djcps.wms.stocktaking.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @title:PDA盘点订单详情
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/13
 **/
public class PdaStocktakingOrderInfo {
    /**
     * 作业单号
     */
    @NotBlank
    private String jobId;

    /**
     * 订单id
     */
    @NotBlank
    private String orderId;

    /**
     * 合作方编号
     */
    @NotBlank
    private String partnerId;

    /**
     * 仓库编号
     */
    @NotBlank
    private String warehouseId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "PdaStocktakingOrderInfo{" +
                "jobId='" + jobId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                '}';
    }
}
