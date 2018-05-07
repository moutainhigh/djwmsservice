package com.djcps.wms.allocation.model;



/**
 * 提货单实体类
 * 
 * @author ztw
 * @since 2018/2/7
 *
 */
public class DeliveryPO{

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 运单号
     */
    private String waybillId;

    /**
     * 提货单号
     */
    private String deliveryId;

    /**
     * 提货单状态 1:待提货 5:部分提货 10:已提货
     */
    private int status;

    /**
     * 装车台id
     */
    private String loadingTableId;

    /**
     * 装车台名称
     */
    private String loadingTableName;

    /**
     * 打印次数
     */
    private int printCount;

    /**
     * 提货人id
     */
    private String pickerId;

    /**
     * 提货人名称
     */
    private String pickerName;

    /**
     * 是否确认 1:已确认 0:未确认
     */
    private int effect;

    /**
     * 装车员id
     */
    private String loadingPersonId;

    /**
     * 装车员名称
     */
    private String loadingPersonName;

    /**
     * 提货完成时间
     */
    private String finishTime;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

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
    private int partnerArea;
    /**
     * 配货时间,就是提货单创建时间
     */
    private String deliveryCreateTime;

    /**
     * 提货确认状态 1确认，2未确认
     */
    private String deliveryIdEffect;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryIdEffect() {
        return deliveryIdEffect;
    }

    public void setDeliveryIdEffect(String deliveryIdEffect) {
        this.deliveryIdEffect = deliveryIdEffect;
    }

    public String getDeliveryCreateTime() {
        return deliveryCreateTime;
    }

    public void setDeliveryCreateTime(String deliveryCreateTime) {
        this.deliveryCreateTime = deliveryCreateTime;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
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

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
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

    public int getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(int partnerArea) {
        this.partnerArea = partnerArea;
    }

    @Override
    public String toString() {
        return "DeliveryPO [orderId=" + orderId + ", waybillId=" + waybillId + ", deliveryId=" + deliveryId
                + ", status=" + status + ", loadingTableId=" + loadingTableId + ", loadingTableName=" + loadingTableName
                + ", printCount=" + printCount + ", pickerId=" + pickerId + ", pickerName=" + pickerName + ", effect="
                + effect + ", loadingPersonId=" + loadingPersonId + ", loadingPersonName=" + loadingPersonName
                + ", finishTime=" + finishTime + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", partnerId=" + partnerId + ", partnerName=" + partnerName + ", partnerArea=" + partnerArea
                + ", deliveryCreateTime=" + deliveryCreateTime + ", deliveryIdEffect=" + deliveryIdEffect + "]";
    }

}
