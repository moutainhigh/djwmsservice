package com.djcps.wms.outorder.model;

import java.io.Serializable;

import com.djcps.wms.commons.base.BaseListBO;

/**
 * 查询所有出库单所需要的条件类
 * @author ldh
 *
 */
public class SelectOutOrderBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2691916317471128384L;
	/**
	 * 出库单编号
	 */
	private String fid;
	/**
	 * 订单编号
	 */
	private String forderId;
	/**
	 * 商品名称
	 */
	private String fproductName;
	/**
     * 出库单状态
     */
	private Integer fstatus;
	/**
     * 客户名称
     */
	private String fcustomerName;
	/**
     * 开始时间
     */
	private String startTime;
	/**
     * 结束时间
     */
	private String endTime;
	private Integer pageSize;

    private Integer pageNo;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getForderId() {
		return forderId;
	}

	public void setForderId(String forderId) {
		this.forderId = forderId;
	}

	public String getFproductName() {
		return fproductName;
	}

	public void setFproductName(String fproductName) {
		this.fproductName = fproductName;
	}

	public int getFstatus() {
		return fstatus;
	}

	public void setFstatus(int fstatus) {
		this.fstatus = fstatus;
	}

	public String getFcustomerName() {
		return fcustomerName;
	}

	public void setFcustomerName(String fcustomerName) {
		this.fcustomerName = fcustomerName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "SelectOutOrderBO [fid=" + fid + ", forderId=" + forderId + ", fproductName=" + fproductName
				+ ", fstatus=" + fstatus + ", fcustomerName=" + fcustomerName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", pageSize=" + pageSize + ", pageNo=" + pageNo + "]";
	}
    
}
