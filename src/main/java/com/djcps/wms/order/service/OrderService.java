package com.djcps.wms.order.service;

import java.util.List;
import java.util.Map;

import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardBO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitSonOrderBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;

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
	
	/**
	 * 根据id去获取在库信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月2日
	 */
	List<WarehouseOrderDetailPO> getStockInfo(SelectAreaByOrderIdBO param);
	
	/**
	 * 获取线上纸板订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月12日
	 */
	Map<String, Object> getOnlinePaperboardList(QueryObjectBO param);
	
	/**
	 * 线上纸板订单根据订单号获取订单详情
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月23日
	 */
	Map<String, Object> getOnlinePaperboardByOrderId(BatchOrderIdListBO param);

}
