package com.djcps.wms.order.model.onlinepaperboard;


/**
 * 拆单对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class UpdateSplitOrderBO {
    /**
     * 拆单号
     */
    private String subOrderId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 拆单数量
     */
    private Integer subNumber;
    /**
     * 拆单状态
     */
    private Integer subStatus;
    /**
     * 拆单地址
     */
    private String subAddress;
    /**
     *出库数量
     */
    private Integer outStock;
    /**
     * 入库数量
     */
    private Integer inStock;
    /**
     *是否异常 1有  0无
     */
    private Integer isException;
    /**
     * 是否备货 1有  0无
     */
    private Integer isStored;
    /**
     * 是否生产 1有  0无
     */
    private Integer isProduce;
    /**
     * 区域拆分键
     */
    private String keyArea;

    public String getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getSubNumber() {
		return subNumber;
	}

	public void setSubNumber(Integer subNumber) {
		this.subNumber = subNumber;
	}

	public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    public String getSubAddress() {
        return subAddress;
    }

    public void setSubAddress(String subAddress) {
        this.subAddress = subAddress;
    }

    public Integer getOutStock() {
        return outStock;
    }

    public void setOutStock(Integer outStock) {
        this.outStock = outStock;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getIsException() {
        return isException;
    }

    public void setIsException(Integer isException) {
        this.isException = isException;
    }

    public Integer getIsStored() {
        return isStored;
    }

    public void setIsStored(Integer isStored) {
        this.isStored = isStored;
    }

    public Integer getIsProduce() {
        return isProduce;
    }

    public void setIsProduce(Integer isProduce) {
        this.isProduce = isProduce;
    }

    public String getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(String keyArea) {
        this.keyArea = keyArea;
    }
}
