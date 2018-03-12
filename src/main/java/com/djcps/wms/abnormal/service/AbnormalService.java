package com.djcps.wms.abnormal.service;

import java.util.Map;

import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;

/**
 * 异常订单业务层
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
public interface AbnormalService {


	/**
	 * 异常订单查询(包含查询条件)
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	Map<String, Object> getOrderByAttributeBO(GetOrderByAttributeBO param);

	/**
	 * 根据拆分的订单号获取订单信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	Map<String, Object> getSplitOrderByOrderId(OrderIdBO param);

	/**
	 * 修改异常订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	Map<String, Object> updateAbnormal(UpdateAbnormalBO param);

	/**
	 * 新增异常订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	
	Map<String, Object> addAbnormal(AddAbnormal param);

	/**
	 * 根据订单号获取异常订单信息(批量)
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年3月7日
	 */
	Map<String, Object> getOrderByOrderIdList(OrderIdListBO param);

}
