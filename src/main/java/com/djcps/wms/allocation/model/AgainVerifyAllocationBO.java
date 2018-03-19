package com.djcps.wms.allocation.model;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车优化再次确认配货
 * @company:djwms
 * @author:zdx
 * @date:2018年1月23日
 */
/**
 * @author ASUS
 *
 */
public class AgainVerifyAllocationBO extends BaseAddBO implements Serializable{

	private static final long serialVersionUID = -8809202570759593832L;
	
	/**
	 * 提货单号
	 */
	@NotBlank
	private String deliveryId;
	
	/**
	 * 运单号
	 */
	@NotBlank
	private String waybillId;
	
	/**
	 * 提货单确认状态
	 */
	private String deliveryIdEffect;

	/**
	 * 订单号
	 */
	@NotBlank
	private String orderId;
	
	/**
	 * 装车顺序
	 */
	@NotNull
	private String sequence;
	
	/**
	 * 订单状态
	 */
	private Integer status;
	
	/**
	 * 车牌号
	 */
	private String plateNumber;

	public String getPlateNumber() {
		return plateNumber;
	}


	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	public String getWaybillId() {
		return waybillId;
	}


	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getDeliveryId() {
		return deliveryId;
	}


	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}


	public String getDeliveryIdEffect() {
		return deliveryIdEffect;
	}


	public void setDeliveryIdEffect(String deliveryIdEffect) {
		this.deliveryIdEffect = deliveryIdEffect;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getSequence() {
		return sequence;
	}


	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


	@Override
	public String toString() {
		return "AgainVerifyAllocationBO [deliveryId=" + deliveryId + ", waybillId=" + waybillId + ", deliveryIdEffect="
				+ deliveryIdEffect + ", orderId=" + orderId + ", sequence=" + sequence + ", status=" + status
				+ ", plateNumber=" + plateNumber + "]";
	}

}
