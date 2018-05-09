package com.djcps.wms.order.service;

import java.util.List;
import java.util.Map;

import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.offlinepaperboard.OfflineQueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardBO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;

/**
 *  订单业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public interface OrderService {

	
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
	Map<String, Object> getOrderByOrderId(BatchOrderIdListBO param);

	/**
	 * 查询线下纸板订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年5月8日
	 */
	Map<String, Object> getOffinePaperboardList(OfflineQueryObjectBO param);

	/**
	 * 查询线下纸箱订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年5月9日
	 */
	Map<String, Object> getOffineBoxOrderList(OfflineQueryObjectBO param);

}
