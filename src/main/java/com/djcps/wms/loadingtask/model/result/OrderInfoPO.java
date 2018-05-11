package com.djcps.wms.loadingtask.model.result;

import java.math.BigDecimal;

/**
 * 订单服务 子订单 返回实体类
 * 
 * @author WYB
 * @since 2018/3/20
 */
public class OrderInfoPO {

    /**
     * 产品名称
     */
    private String productName;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 楞型
     */
    private String fluteType;

    /**
     * 产品规格长
     */
    private String boxLength;

    /**
     * 产品规格宽
     */
    private String boxWidth;

    /**
     * 产品规格高
     */
    private String boxHeight;

    /**
     * 下料规格长
     */
    private String materialLength;

    /**
     * 下料规格宽
     */
    private String materialWidth;

    /**
     * 材料名称
     */
    private String materialName;
    /**
     * 订单数量
     */
    private Integer orderAmount;
    /**
     * 下料规格
     */
    private String materialSize;

    /**
     * 产品规格
     */
    private String productSize;
    /**
     * 装车台数量
     */
    private Integer loadingAmount;
    /**
     * 获取订单状态
     */
    private Integer orderStatus;
    /**
     * 异常数量
     */
    private Integer abnomalAmount;
    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
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

    public String getFluteType() {
        return fluteType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getMaterialSize() {
        return materialSize;
    }

    public void setMaterialSize(String materialSize) {
        this.materialSize = materialSize;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getLoadingAmount() {
        return loadingAmount;
    }

    public void setLoadingAmount(Integer loadingAmount) {
        this.loadingAmount = loadingAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getAbnomalAmount() {
        return abnomalAmount;
    }

    public void setAbnomalAmount(Integer abnomalAmount) {
        this.abnomalAmount = abnomalAmount;
    }

    @Override
    public String toString() {
        return "OrderInfoPO [productName=" + productName + ", orderId=" + orderId + ", fluteType=" + fluteType
                + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight
                + ", materialLength=" + materialLength + ", materialWidth=" + materialWidth + ", materialName="
                + materialName + ", orderAmount=" + orderAmount + ", materialSize=" + materialSize + ", productSize="
                + productSize + ", loadingAmount=" + loadingAmount + ", orderStatus=" + orderStatus + ", abnomalAmount="
                + abnomalAmount + ", realDeliveryAmount=" + realDeliveryAmount + "]";
    }

}