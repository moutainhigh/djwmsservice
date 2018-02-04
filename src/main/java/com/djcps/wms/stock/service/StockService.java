package com.djcps.wms.stock.service;

import java.util.Map;

import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;

/**
 * 入库移库业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public interface StockService {


	/**
	 * 获取推荐库位
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> getRecommendLoca(RecommendLocaBO param);

	/**
	 * 获取操作记录
	 * @param string
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getOperationRecord(OrderIdBO fromJson);

	
	/**
	 * 入库
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> addStock(AddStockBO param);

	/**
	 * 移库
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> moveStock(MoveStockBO param);

	/**
	 * 获取订单已入库数量
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getSavedStockAmount(SelectSavedStockAmountBO param);

	/**
	 * 根据订单获取库位信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getAreaByOrderId(SelectAreaByOrderIdBO param);

}
