package com.djcps.wms.workrecords.model;

/**
 * @author Chengw
 * @create 2018/4/24 11:35.
 * @since 1.0.0
 */
public class AchievementsPO {

    /**
     * 合作方id
     */
    private String partnerId;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方区域
     */
    private Integer partnerArea;

    /**
     * 操作人Id
     */
    private String operatorId;

    /**
     * 操作人名称
     */
    private String operator;

    /**
     * 关联id
     */
    private String relativeId;

    /**
     * 楞型
     */
    private Integer fluteType;

    /**
     * 总面积
     */
    private Integer totalArea;

    /***
     * 仓库
     */
    private String warehouseName;

    /**
     * 提货事件
     */
    private String deliveryEvent;

    /**
     * 退库事件
     */
    private String cancelStockEvent;

    /**
     * 提货时间
     */
    private String deliveryCreateTime;

    /**
     * 退库时间
     */
    private String cancelStockCreateTime;

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Integer getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(Integer partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public Integer getFluteType() {
        return fluteType;
    }

    public void setFluteType(Integer fluteType) {
        this.fluteType = fluteType;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getDeliveryEvent() {
        return deliveryEvent;
    }

    public void setDeliveryEvent(String deliveryEvent) {
        this.deliveryEvent = deliveryEvent;
    }

    public String getCancelStockEvent() {
        return cancelStockEvent;
    }

    public void setCancelStockEvent(String cancelStockEvent) {
        this.cancelStockEvent = cancelStockEvent;
    }

    public String getDeliveryCreateTime() {
        return deliveryCreateTime;
    }

    public void setDeliveryCreateTime(String deliveryCreateTime) {
        this.deliveryCreateTime = deliveryCreateTime;
    }

    public String getCancelStockCreateTime() {
        return cancelStockCreateTime;
    }

    public void setCancelStockCreateTime(String cancelStockCreateTime) {
        this.cancelStockCreateTime = cancelStockCreateTime;
    }

    @Override
    public String toString() {
        return "AchievementsPO{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea=" + partnerArea +
                ", operatorId='" + operatorId + '\'' +
                ", operator='" + operator + '\'' +
                ", relativeId='" + relativeId + '\'' +
                ", fluteType=" + fluteType +
                ", totalArea=" + totalArea +
                ", warehouseName='" + warehouseName + '\'' +
                ", deliveryEvent='" + deliveryEvent + '\'' +
                ", cancelStockEvent='" + cancelStockEvent + '\'' +
                ", deliveryCreateTime='" + deliveryCreateTime + '\'' +
                ", cancelStockCreateTime='" + cancelStockCreateTime + '\'' +
                '}';
    }
}
