package com.djcps.wms.allocation.service;


import java.util.Map;

import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.model.PartnerInfoBO;


/**
 * 混合配货业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface AllocationService {

	/**
	 *  获取订单类型枚举
	 * @description:
	 * @param baseBO
	 * @return
	 * @author:zdx
	 * @date:2017年12月12日
	 */
	Map<String, Object> getOrderType(BaseBO baseBO);

	
	/**
	 * 获取已选择的配货列表
	 * @description:
	 * @param partnern
	 * @return
	 * @author:zdx
	 * @date:2017年12月18日
	 */
	Map<String, Object> getChooseAllocation(PartnerInfoBO partnern);

	/**
	 * 保存已选择的混合配货
	 * @description:
	 * @param allocation
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> saveAllocation(AddAllocationBO allocation);

}
