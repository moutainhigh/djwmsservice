package com.djcps.wms.allocation.service;


import java.util.List;
import java.util.Map;

import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAddOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAllocationBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.GetDeliveryByWaybillIdsBO;
import com.djcps.wms.allocation.model.GetExcellentLodingBO;
import com.djcps.wms.allocation.model.GetIntelligentAllocaBO;
import com.djcps.wms.allocation.model.GetRedundantByAttributeBO;
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
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


	/**
	 * 获取配货结果
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> getAllocationResultList(GetRedundantByAttributeBO param);


	/**
	 * 智能配货结果
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> getIntelligentAllocaList(GetIntelligentAllocaBO param);


	/**
	 * 确认配货
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> verifyAllocation(VerifyAllocationBO param);


	/**
	 * 移除订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> moveOrder(MoveOrderPO param);


	/**
	 * 获取追加订单列表
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> getAddOrderList(GetRedundantByAttributeBO param);


	/**
	 * 确认追加订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> verifyAddOrder(List<AddAllocationOrderBO> param);


	/**
	 * 配货管理查询
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月22日
	 */
	Map<String, Object> getAllocationManageList(GetRedundantByAttributeBO param);


	/**
	 * 根据运单号获取提货单详情
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> getWaybillDetailByWayId(GetDeliveryByWaybillIdsBO param);


	/**
	 * 装车优化
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> getExcellentLoding(GetExcellentLodingBO param);


	/**
	 * 装车优化确认追加订单
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> againVerifyAddOrder(AgainVerifyAddOrderBO param);


	/**
	 * 装车优化再次确认配货
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> againVerifyAllocation(List<AgainVerifyAllocationBO> param);


	/**
	 * 获取所有可用车辆的信息
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> getAllUserCarInfo();

	/**
	 * 根据车辆id获取车辆详情
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> changeCarInfo(ChangeCarInfoBO param);


	/**
	 * 根据车辆id获取车辆信息
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> getCarDetailById();


	/**
	 * 取消配货
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> cancelAllocation(CancelAllocationBO param);


	/**
	 * 智能配货插入数据,假接口
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月23日
	 */
	Map<String, Object> addzhinengpeihuo();

}
