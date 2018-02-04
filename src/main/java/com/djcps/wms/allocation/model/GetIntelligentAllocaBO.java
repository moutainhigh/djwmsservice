package com.djcps.wms.allocation.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;

/**
 *	智能配货结果
 * @company:djwms
 * @author:zdx
 * @date:2018年1月22日
 */
public class GetIntelligentAllocaBO extends BaseListPartnerIdBO implements Serializable{

	private static final long serialVersionUID = 3848765538827058096L;
	
	/**
	 * 提货单号
	 */
	@NotBlank
	private String allocationId;


	public String getAllocationId() {
		return allocationId;
	}


	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}


	@Override
	public String toString() {
		return "GetIntelligentAllocaBO [allocationId=" + allocationId + "]";
	}
	
	/**
	 * 订单状态
	 */
//	private String orderStatus;
	/**
	 * 提醒
	 */
//	private String remind;
	
	/**
	 * 仓库类型,1,2,3,4,5(纸板，纸箱，积分商城仓库，物料仓库，退货仓库)
	 */
//	private String warehouseType;
	/**
	 * 仓库编码
	 */
//	private String warehouseId;
	
	
	
}
