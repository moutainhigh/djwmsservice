package com.djcps.wms.outorder.service;

import java.util.Map;

import com.djcps.wms.outorder.model.OrderBO;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
/**
 * 
 * @author ldh
 *
 */
public interface OutOrderService {
	/**
	 * 获取订单明细列表
	 * @param outOrderBO
	 * @return
	 */
	Map<String, Object> getOrderDetail(OutOrderBO outOrderBO);
	/**
	 * 获取所有出库单所有列表
	 * @param param
	 * @return
	 */
	Map<String, Object> getAllOutOrder(SelectOutOrderBO param);
	/**
	 * 根据出库单编号获取一条出库单信息
	 * @param param
	 * @return
	 */
	Map<String, Object> getOutOrderByOutOrderId(OutOrderBO param);
	/**
	 * 根据出库单编号获取出库单信息
	 * @param param
	 * @return
	 */
	Map<String, Object> updateOutOrderByOutOrderId(OutOrderBO param);
}
