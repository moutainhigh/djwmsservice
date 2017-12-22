package com.djcps.wms.stock.service;

import java.util.Map;

import com.djcps.wms.stock.model.AddStock;
import com.djcps.wms.stock.model.MoveStock;
import com.djcps.wms.stock.model.RecommendLocaBo;
import com.djcps.wms.stock.model.SelectAreaByOrderId;
import com.djcps.wms.stock.model.SelectSavedStockAmount;

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
	Map<String, Object> getRecommendLoca(RecommendLocaBo param);

	/**
	 * 获取操作记录
	 * @param string
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getOperationRecord(String string);

	
	/**
	 * 入库
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月22日
	 */
	Map<String, Object> addStock(AddStock param);

	/**
	 * 移库
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> moveStock(MoveStock param);

	/**
	 * 获取订单已入库数量
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getSavedStockAmount(SelectSavedStockAmount param);

	/**
	 * 根据订单获取库位信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月20日
	 */
	Map<String, Object> getAreaByOrderId(SelectAreaByOrderId param);

}
