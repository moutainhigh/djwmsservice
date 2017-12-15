package com.djcps.wms.allocation.service;


import java.util.Map;

import com.djcps.wms.allocation.model.AddAllocation;
import com.djcps.wms.commons.base.BaseParam;
import com.djcps.wms.commons.model.PartnerInfoBo;


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
	 * @param baseParam
	 * @return
	 * @author:zdx
	 * @date:2017年12月12日
	 */
	Map<String, Object> getOrderType(BaseParam baseParam);

	/**
	 * 获取已选择的混合配货
	 * @description:
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> getChooseAllocation(PartnerInfoBo partnern);

	/**
	 * 保存已选择的混合配货
	 * @description:
	 * @param allocation
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> saveAllocation(AddAllocation allocation);

}
