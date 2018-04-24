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
    private String luteType;

    /**
     * 产品规格长
     */
    private Double boxLength;

    /**
     * 产品规格宽
     */
    private Double boxWidth;

    /**
     * 产品规格高
     */
    private Double boxHeight;

    /**
     * 下料规格长
     */
    private BigDecimal materialLength;

    /**
     * 下料规格宽
     */
    private BigDecimal materialWidth;

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
	public String getLuteType() {
		return luteType;
	}
	public void setLuteType(String luteType) {
		this.luteType = luteType;
	}
	public Double getBoxLength() {
		return boxLength;
	}
	public void setBoxLength(Double boxLength) {
		this.boxLength = boxLength;
	}
	public Double getBoxWidth() {
		return boxWidth;
	}
	public void setBoxWidth(Double boxWidth) {
		this.boxWidth = boxWidth;
	}
	public Double getBoxHeight() {
		return boxHeight;
	}
	public void setBoxHeight(Double boxHeight) {
		this.boxHeight = boxHeight;
	}
	public BigDecimal getMaterialLength() {
		return materialLength;
	}
	public void setMaterialLength(BigDecimal materialLength) {
		this.materialLength = materialLength;
	}
	public BigDecimal getMaterialWidth() {
		return materialWidth;
	}
	public void setMaterialWidth(BigDecimal materialWidth) {
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
		return "OrderInfoPO [productName=" + productName + ", orderId=" + orderId + ", luteType=" + luteType
				+ ", boxLength=" + boxLength + ", boxWidth=" + boxWidth + ", boxHeight=" + boxHeight
				+ ", materialLength=" + materialLength + ", materialWidth=" + materialWidth + ", materialName="
				+ materialName + ", orderAmount=" + orderAmount + ", materialSize=" + materialSize + ", productSize="
				+ productSize + ", loadingAmount=" + loadingAmount + ", orderStatus=" + orderStatus + ", abnomalAmount="
				+ abnomalAmount + "]";
	}

}