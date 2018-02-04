package com.djcps.wms.stocktaking.model;

/**
 * 新增盘点任务对象,订单参数无f
 * @author:wzy
 * @date:2018/2/3
 **/
public class StocktakingTaskFBO {
    /**
     * 关联id
     */
    private String relativeId;

    /**
     * 作业单号
     */
    private String jobId;


    /**
     * 合作方id
     */
    private String partnerId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 仓库id
     */
    private String warehouseId;


    /**
     * 仓库名称
     */
    private String warehouseName;


    /**
     * 库区id
     */
    private String warehouseAreaId;

    /**
     * 库区名称
     */
    private String warehouseAreaName;

    /**
     * 库位id
     */
    private String warehouseLocId;


    /**
     * 库位名称
     */
    private String warehouseLocName;

    /**
     * 在库数量
     */
    private Integer trueAmount;

    /**
     * 库位关联订单详情列表
     */
    private OrderInfoBO orderDetail;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Integer getTrueAmount() {
        return trueAmount;
    }

    public void setTrueAmount(Integer trueAmount) {
        this.trueAmount = trueAmount;
    }

    public OrderInfoBO getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderInfoBO orderDetail) {
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "StocktakingTaskFBO{" +
                "relativeId='" + relativeId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", productName='" + productName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", trueAmount=" + trueAmount +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
