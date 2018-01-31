package com.djcps.wms.order.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.constant.AppConstant;

/**
 * 订单查询对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class OrderParamBO implements Serializable{

	private static final long serialVersionUID = 3975618631995060948L;
	
	/**
	 * 版本号
	 */
	@NotBlank
	private String version;
	
	/**
	 * 页数
	 */
	private String pageNum;
	
	/**
	 * 行数
	 */
	private String pageSize;
	
	/**
	 * 获取订单传参的子类
	 */
	@NotNull
	private ChildOrderParamBO childOrderModel;

	public OrderParamBO() {
		super();
		this.childOrderModel = new ChildOrderParamBO();
		this.version = AppConstant.DEFAULT_VERSION;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public ChildOrderParamBO getChildOrderModel() {
		return childOrderModel;
	}

	public void setChildOrderModel(ChildOrderParamBO childOrderModel) {
		this.childOrderModel = childOrderModel;
	}

	@Override
	public String toString() {
		return "OrderParamBO [version=" + version + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ ", childOrderModel=" + childOrderModel + "]";
	}

}
