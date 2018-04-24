package com.djcps.wms.order.model;

/**
 * wms订单相关的信息
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class WmsOrderInvolveInfoBO {
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 订单提醒
	 */
	private String remind;
	/**
	 * 提货单状态
	 */
	private String deliveryIdStatus;
	/**
	 * 装车顺序
	 */
	private Integer sequence;
	
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	
	/**
	 * 提货数量
	 */
	private Integer deliveryAmount;
}
