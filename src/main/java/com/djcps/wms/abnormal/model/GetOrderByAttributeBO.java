package com.djcps.wms.abnormal.model;

import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import java.io.Serializable;


/**
 * 条件获取异常订单参数接收类
 * @author:wzy
 * @date:2018/3/6
 **/
public class GetOrderByAttributeBO extends BaseListPartnerIdBO implements Serializable {
	
	private static final long serialVersionUID = -8901245956031247121L;

	/**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     *客户名称
     */
    private String customerName;

    /**
     *处理状态 1:已处理 0:未处理
     */
    private Integer status;

    /**
     *异常处理结果
     */
    private String result;

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "GetOrderByAttributeBO [orderStatus=" + orderStatus + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", orderId=" + orderId + ", productName=" + productName + ", customerName=" + customerName
				+ ", status=" + status + ", result=" + result + "]";
	}

}
