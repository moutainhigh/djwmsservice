package com.djcps.wms.workrecords.model;

/**
 * @author Chengw
 * @create 2018/4/25 18:48.
 * @since 1.0.0
 */
public class WorkRecordsOrderPO {

    /**
     * 仓库id
     */
    private String warehouseId;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 操作时间
     */
    private String createTime;

    /**
     * 操作事件
     */
    private String event;


    /**
     * 产品名称
     */
    private String productName;

    /**
     * 材料名称
     */
    private String materialName;

    /**
     * 下料规格长
     */
    private String materialLength;

    /**
     * 下料规格宽
     */
    private String materialWidth;

    /**
     * 楞型
     */
    private String fluteType;

    /**
     * 产品规格,高
     */
    private String boxHeight;

    /**
     * 产品规格,长
     */
    private String boxLength;

    /**
     * 产品规格,宽
     */
    private String boxWidth;

    /**
     * 订单数量
     */
    private Integer orderAmount;

    /**
     * 单位
     */
    private String unit;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getFluteType() {
        return fluteType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
    }

    public String getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(String boxHeight) {
        this.boxHeight = boxHeight;
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

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "WorkRecordsOrderPO{" +
                "warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", event='" + event + '\'' +
                ", productName='" + productName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialLength='" + materialLength + '\'' +
                ", materialWidth='" + materialWidth + '\'' +
                ", fluteType='" + fluteType + '\'' +
                ", boxHeight='" + boxHeight + '\'' +
                ", boxLength='" + boxLength + '\'' +
                ", boxWidth='" + boxWidth + '\'' +
                ", orderAmount=" + orderAmount +
                ", unit='" + unit + '\'' +
                '}';
    }
}
