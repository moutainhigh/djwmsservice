package com.djcps.wms.order.model.onlinepaperboard;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.djcps.wms.commons.model.PartnerInfoBO;

/**
 * 线上纸板订单查询对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class QueryObjectBO extends PartnerInfoBO implements Serializable{

	private static final long serialVersionUID = 5390316313423146496L;
	
	@NotNull
	private OnlinePaperboardBO queryObject;
	
	/**
	 * 当前页
	 */
	private Integer currentPage;
	
	/**
	 * 条数 
	 */
	private Integer pageSize;

	public OnlinePaperboardBO getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(OnlinePaperboardBO queryObject) {
		this.queryObject = queryObject;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "QueryObjectBO [queryObject=" + queryObject + ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ "]";
	}
	
}
