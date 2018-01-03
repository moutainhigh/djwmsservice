package com.djcps.wms.order.service;

import java.util.Map;

import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 *  订单业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public interface OrderService {

	
	/**
	 * 获取所有订单列表
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月3日
	 */
	Map<String, Object> getAllOrderList(OrderParamBO param);

	/**
	 * 根据订单号获取订单信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> getOrderByOrderId(OrderIdBO param);
	
}
