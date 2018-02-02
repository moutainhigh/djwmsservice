package com.djcps.wms.delivery.model;

import java.util.List;

/**
 * 提货信息 实体类
 * @author Chengw
 * @since 2018/2/1 13:21.
 */
public class DeliveryPO {

    /**
     * 运单号
     */
    private String waybillId;

    /**
     * 提货单号
     */
    private String deliveryId;

    /**
     * 提货状态
     */
    private Integer status;

    /**
     * 合作方ID
     */
    private String partnerId;

    /**
     * 合作方名称
     */
    private String partnerName;

    /**
     * 合作方区域
     */
    private String partnerArea;

    /**
     * 装车台ID
     */
    private String loadingTableId;

    /**
     * 装车台名称
     */
    private String loadingTableName;

    /**
     * 装车人员ID
     */
    private String loadingPersonId;

    /**
     * 装车人员名称
     */
    private String loadingPersonName;

    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 创新时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 打印次数
     */
    private String printCount;

    /**
     * 提货人员ID
     */
    private String pickerId;

    /**
     * 提货人员名称
     */
    private String pickerName;

    /**
     * 是否有效
     */
    private Integer effect;

    /**
     * 订单信息
     */
    private List<DeliveryOrderPO> orderList;

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(String partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getLoadingTableId() {
        return loadingTableId;
    }

    public void setLoadingTableId(String loadingTableId) {
        this.loadingTableId = loadingTableId;
    }

    public String getLoadingTableName() {
        return loadingTableName;
    }

    public void setLoadingTableName(String loadingTableName) {
        this.loadingTableName = loadingTableName;
    }

    public String getLoadingPersonId() {
        return loadingPersonId;
    }

    public void setLoadingPersonId(String loadingPersonId) {
        this.loadingPersonId = loadingPersonId;
    }

    public String getLoadingPersonName() {
        return loadingPersonName;
    }

    public void setLoadingPersonName(String loadingPersonName) {
        this.loadingPersonName = loadingPersonName;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
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

    public String getPrintCount() {
        return printCount;
    }

    public void setPrintCount(String printCount) {
        this.printCount = printCount;
    }

    public String getPickerId() {
        return pickerId;
    }

    public void setPickerId(String pickerId) {
        this.pickerId = pickerId;
    }

    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public List<DeliveryOrderPO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<DeliveryOrderPO> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "DeliveryPO{" +
                "waybillId='" + waybillId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                ", status=" + status +
                ", partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea='" + partnerArea + '\'' +
                ", loadingTableId='" + loadingTableId + '\'' +
                ", loadingTableName='" + loadingTableName + '\'' +
                ", loadingPersonId='" + loadingPersonId + '\'' +
                ", loadingPersonName='" + loadingPersonName + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", printCount='" + printCount + '\'' +
                ", pickerId='" + pickerId + '\'' +
                ", pickerName='" + pickerName + '\'' +
                ", effect=" + effect +
                ", orderList=" + orderList +
                '}';
    }
}
