package com.djcps.wms.allocation.model;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 仅仅是修改库存表的,在库数量和累计入库数量
 * @company:djwms
 * @author:zdx
 * @date:2018年5月6日
 */
public class UpdateInventoryAmountBO extends BaseAddBO implements Serializable{
	
	private static final long serialVersionUID = 3889424220262304282L;

	/**
     * 订单编号
     */
    @NotBlank
    private String orderId;
    
    @NotNull
    private Integer amountInstock;
    
    @NotNull
    private Integer amountSaved;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getAmountInstock() {
		return amountInstock;
	}

	public void setAmountInstock(Integer amountInstock) {
		this.amountInstock = amountInstock;
	}

	public Integer getAmountSaved() {
		return amountSaved;
	}

	public void setAmountSaved(Integer amountSaved) {
		this.amountSaved = amountSaved;
	}

	@Override
	public String toString() {
		return "UpdateInventoryAmountBO [orderId=" + orderId + ", amountInstock=" + amountInstock + ", amountSaved="
				+ amountSaved + "]";
	}

}
