package com.djcps.wms.delivery.model;

import java.util.List;

/**
 * @author Chengw
 * @since 2018/1/31 14:05.
 */
public class DeliveryOrderPO {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 运单号
     */
    private String allocationId;
    /**
     * 提货单号
     */
    private String deliveryId;
    /**
     * 提货单的确认状态，1确认，2未确认
     */
    private Integer deliveryIdEffect;
    /**
     * 仓库ID
     */
    private String warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区ID
     */
    private String warehouseAreaId;
    /**
     * 库区名称
     */
    private String warehouseAreaName;
    /**
     * 库位ID
     */
    private String warehouseLocId;
    /**
     * 库位名称
     */
    private String warehouseLocName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 材料名称
     */
    private String materialName;
    /**
     * 客户名称(母账户名称或者子账户名称)
     */
    private String customerName;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 提货时间
     */
    private String finishTime;
    /**
     * 序列
     */
    private Integer sequence;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 提货状态
     */
    private Integer deliveryStatus;
    /**
     * 订单数量
     */
    private Integer orderAmount;
    /**
     * 提货数量
     */
    private Integer deliveryAmount;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 单位
     */
    private String unit;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     *******************************************************************************
     * 订单信息
     *******************************************************************************
     **/

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
     * 下料规格长
     */
    private String materialLength;

    /**
     * 下料规格宽
     */
    private String materialWidth;

    /**
     * 交期时间
     */
    private String deliveryTime;

    /**
     * 楞型
     */
    private String fluteType;

    /**
     * 库位库存
     */
    private List<OrderDeliveryPO> warehouseLocs;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryIdEffect() {
        return deliveryIdEffect;
    }

    public void setDeliveryIdEffect(Integer deliveryIdEffect) {
        this.deliveryIdEffect = deliveryIdEffect;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getMaterialLength() {
        return materialLength;
    }

    public void setMaterialLength(String materialLength) {
        this.materialLength = materialLength;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public String getMaterialWidth() {
        return materialWidth;
    }

    public void setMaterialWidth(String materialWidth) {
        this.materialWidth = materialWidth;
    }

    public List<OrderDeliveryPO> getWarehouseLocs() {
        return warehouseLocs;
    }

    public void setWarehouseLocs(List<OrderDeliveryPO> warehouseLocs) {
        this.warehouseLocs = warehouseLocs;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "DeliveryOrderPO{" +
                "orderId='" + orderId + '\'' +
                ", allocationId='" + allocationId + '\'' +
                ", deliveryId='" + deliveryId + '\'' +
                ", deliveryIdEffect=" + deliveryIdEffect +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAreaId='" + warehouseAreaId + '\'' +
                ", warehouseAreaName='" + warehouseAreaName + '\'' +
                ", warehouseLocId='" + warehouseLocId + '\'' +
                ", warehouseLocName='" + warehouseLocName + '\'' +
                ", productName='" + productName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", sequence=" + sequence +
                ", status=" + status +
                ", orderAmount=" + orderAmount +
                ", deliveryAmount=" + deliveryAmount +
                ", address='" + address + '\'' +
                ", unit='" + unit + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", boxHeight='" + boxHeight + '\'' +
                ", boxLength='" + boxLength + '\'' +
                ", boxWidth='" + boxWidth + '\'' +
                ", materialLength='" + materialLength + '\'' +
                ", materialWidth='" + materialWidth + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", fluteType='" + fluteType + '\'' +
                ", warehouseLocs=" + warehouseLocs +
                '}';
    }
}
