package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车优化确认配货合并逻辑对象
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class MergeModelBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = 5391909362682990757L;
	
	/**
	 * 装车优化确认配货
	 */
	@NotEmpty
	private List<AgainVerifyAllocationBO> againVerifyAllocation;
	
	/**
	 * 装车优化移除订单
	 */
	@NotNull
	private MoveOrderPO moveOrder;
	
	/**
	 * 装车优化再次追加订单
	 */
	@NotNull
	private AgainVerifyAddOrderBO againVerifyAddOrder;

	public List<AgainVerifyAllocationBO> getAgainVerifyAllocation() {
		return againVerifyAllocation;
	}

	public void setAgainVerifyAllocation(List<AgainVerifyAllocationBO> againVerifyAllocation) {
		this.againVerifyAllocation = againVerifyAllocation;
	}

	public MoveOrderPO getMoveOrder() {
		return moveOrder;
	}

	public void setMoveOrder(MoveOrderPO moveOrder) {
		this.moveOrder = moveOrder;
	}

	public AgainVerifyAddOrderBO getAgainVerifyAddOrder() {
		return againVerifyAddOrder;
	}

	public void setAgainVerifyAddOrder(AgainVerifyAddOrderBO againVerifyAddOrder) {
		this.againVerifyAddOrder = againVerifyAddOrder;
	}

	@Override
	public String toString() {
		return "MergeModelBO [againVerifyAllocation=" + againVerifyAllocation + ", moveOrder=" + moveOrder
				+ ", againVerifyAddOrder=" + againVerifyAddOrder + "]";
	}
	
	
	
}
