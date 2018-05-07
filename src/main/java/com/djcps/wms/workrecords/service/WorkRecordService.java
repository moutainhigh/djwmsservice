package com.djcps.wms.workrecords.service;

/**
* @author panyang
* @version 创建时间：2018年4月17日 下午3:48:42
* 工作记录业务层
*/

import com.djcps.wms.workrecords.model.*;

import java.util.Map;

/**
 * 工作记录业务层
 * @company:djwms
 * @author:py
 * @date:2018年4月20日
 */

public interface WorkRecordService {

	/**
	 *查询入库装车工作记录信息
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> listByOperationType(WorkRecordsBO param);

	/**
	 *查询入库装车工作记录详情
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> getWorkRecordsDetail(WorkRecordsDetailBO param);

	/**
	 *查询提货工作记录信息
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> listDeliveryRecord(WorkRecordsBO param);

	/**
	 *查询入库装车工作记录详情
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> getDeliveryDetail(WorkRecordsDetailBO param);

	/**
	 * 获取pda工作记录
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaInfo(WorkRecordsListBO param);

	/**
	 * 获取pda装车的工作记录
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaLoadingTask(WorkRecordsListBO param);

	/**
	 * 获取pda提货的工作记录
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaDelivery(WorkRecordsListBO param);

	/**
	 * 获取pda盘点的工作记录
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaStockTaking(WorkRecordsListBO param);

	/**
	 * 获取pda运单明细
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaLoadingTaskInfo(WorkRecordsTaskBO param);


	/**
	 * 获取pda 提货单明细
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaDeliveryInfo(WorkRecordsTaskBO param);

	/**
	 * 获取pda盘点单明细
	 * @param param
	 * @return
	 */
	Map<String, Object> listPdaStockTakingInfo(WorkRecordsTaskBO param);

	/**
	 * 获取操作订单信息
	 * @param param
	 * @return
	 */
	Map<String, Object> getOrderDetail(WorkRecordsOrderBO param);


}
