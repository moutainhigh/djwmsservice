package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车优化再次确认配货
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
public class AgainVerifyAllocationBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -8809202570759593832L;
	
	/**
	 * 提货单号
	 */
	@NotEmpty
	private List<String> deliveryIds;
	@NotEmpty
	private List<SequenceBO> sequence;
	public List<String> getDeliveryIds() {
		return deliveryIds;
	}
	public void setDeliveryIds(List<String> deliveryIds) {
		this.deliveryIds = deliveryIds;
	}
	public List<SequenceBO> getSequence() {
		return sequence;
	}
	public void setSequence(List<SequenceBO> sequence) {
		this.sequence = sequence;
	}
	@Override
	public String toString() {
		return "AgainVerifyAllocationBO [deliveryIds=" + deliveryIds + ", sequence=" + sequence + "]";
	}
	
}
