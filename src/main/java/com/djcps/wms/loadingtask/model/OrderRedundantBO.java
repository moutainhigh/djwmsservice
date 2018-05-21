package com.djcps.wms.loadingtask.model;

/**
 * 部分退库插入冗余表
 * @author:wzy
 * @date:2018/3/26
 **/
public class OrderRedundantBO {

    private String partnerId;

    private String partnerName;

    private Integer partnerArea;

    /**
     * 运单单号
     */
    private String wayBillId;
    /**
     * 提货单号
     */
    private String deliveryId;
    /**
     * 订单状态21,部分入库 ,22 ,已入库：23 ,已配货：24, 已提货：25, 已装车：26 ,已发车
     */
    private Integer status;
    /**
     * 是否拆分 0:正常订单(默认) 1:被拆分订单 2:拆后订单
     */
    private Integer isSplit;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 客户名称
     */
    private String customerName;
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
     * 纸箱规格长
     */
    private String boxLength;
    /**
     * 纸箱规格宽
     */
    private String boxWidth;
    /**
     * 纸箱规格高
     */
    private String boxHeight;

    /**
     * 支付时间
     */
    private String paymentTime;

    /**
     * 下单时间
     */
    private String orderTime;
    /**
     * 交期时间
     */
    private String deliveryTime;
    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 退库订单号
     */
    private String canceleStockOrderId;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public Integer getPartnerArea() {
        return partnerArea;
    }

    public void setPartnerArea(Integer partnerArea) {
        this.partnerArea = partnerArea;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
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

    public Integer getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Integer isSplit) {
        this.isSplit = isSplit;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(String boxHeight) {
        this.boxHeight = boxHeight;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCanceleStockOrderId() {
        return canceleStockOrderId;
    }

    public void setCanceleStockOrderId(String canceleStockOrderId) {
        this.canceleStockOrderId = canceleStockOrderId;
    }

    @Override
    public String toString() {
        return "OrderRedundantBO{" +
                "partnerId='" + partnerId + '\'' +
                ", partnerName='" + partnerName + '\'' +
                ", partnerArea=" + partnerArea +
                ", wayBillId='" + wayBillId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                ", status=" + status +
                ", isSplit=" + isSplit +
                ", orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialLength='" + materialLength + '\'' +
                ", materialWidth='" + materialWidth + '\'' +
                ", boxLength='" + boxLength + '\'' +
                ", boxWidth='" + boxWidth + '\'' +
                ", boxHeight='" + boxHeight + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", canceleStockOrderId='" + canceleStockOrderId + '\'' +
                '}';
    }
}
