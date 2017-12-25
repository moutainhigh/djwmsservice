package com.djcps.wms.order.service;

import java.util.Map;

import com.djcps.wms.order.model.PaperOrderBO;
import com.djcps.wms.stock.model.OrderIdBO;

/**
 *  订单业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public interface OrderService {

	
	/**
	 * 获取所有的订单
	 * @param paperOrder
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> getAllOrderList(PaperOrderBO paperOrder);

	/**
	 * 根据订单号获取订单信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> getOrderByOrderId(OrderIdBO param);
	
}