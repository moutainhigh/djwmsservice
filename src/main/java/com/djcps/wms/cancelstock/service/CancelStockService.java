package com.djcps.wms.cancelstock.service;

import java.util.Map;

import com.djcps.wms.cancelstock.model.param.AddCancelStockBO;
import com.djcps.wms.cancelstock.model.param.AddStockBO;
import com.djcps.wms.cancelstock.model.param.CancelOrderIdBO;
import com.djcps.wms.cancelstock.model.param.PickerIdBO;


/**
 *  地址业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface CancelStockService {

	/**
	 * 根据订单号和退库人获取订单详情信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	Map<String, Object> getOrderByOrderId(CancelOrderIdBO param);

	/**
	 * 新增库存信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	Map<String, Object> addStock(AddStockBO param);

	/**
	 * 根据退库人id获取退库任务
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	Map<String, Object> getCancelStockByPickerId(PickerIdBO param);

	/**
	 * 新增退库任务
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月20日
	 */
	Map<String, Object> addCancelStock(AddCancelStockBO param);


	
}
