package com.djcps.wms.delivery.model;

import java.util.List;

/**
 * @author Chengw
 * @since 2018/2/1 15:06.
 */
public class DeliveryOrderDetailPO {

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 提货单号
     */
    private String deliveryId;

    /**
     * 提货是否有效
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
     * 序列
     */
    private Integer sequence;

    /**
     * 订单数量
     */
    private Integer orderAmount;

    /**
     * 提货数量
     */
    private Integer deliveryAmount;

    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;

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
     * 库位提货信息
     */
    private List<OrderDeliveryPO> warehouseLocs;
    
    /**
     * 是否备货 1有  0无
     */
    private Integer isStored;
    
    public Integer getIsStored() {
		return isStored;
	}

	public void setIsStored(Integer isStored) {
		this.isStored = isStored;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getProductName() {
        return productName;
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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
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

    public List<OrderDeliveryPO> getWarehouseLocs() {
        return warehouseLocs;
    }

    public void setWarehouseLocs(List<OrderDeliveryPO> warehouseLocs) {
        this.warehouseLocs = warehouseLocs;
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

	@Override
	public String toString() {
		return "DeliveryOrderDetailPO [orderId=" + orderId + ", deliveryId=" + deliveryId + ", deliveryIdEffect="
				+ deliveryIdEffect + ", warehouseId=" + warehouseId + ", warehouseName=" + warehouseName
				+ ", productName=" + productName + ", materialName=" + materialName + ", materialLength="
				+ materialLength + ", materialWidth=" + materialWidth + ", fluteType=" + fluteType + ", boxHeight="
				+ boxHeight + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth + ", sequence=" + sequence
				+ ", orderAmount=" + orderAmount + ", deliveryAmount=" + deliveryAmount + ", realDeliveryAmount="
				+ realDeliveryAmount + ", unit=" + unit + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", warehouseLocs=" + warehouseLocs + ", isStored=" + isStored + "]";
	}
}
