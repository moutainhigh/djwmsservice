package com.djcps.wms.workrecords.service;

import java.util.List;

/**
* @author panyang
* @version 创建时间：2018年4月17日 下午3:48:42
* 工作记录业务层
*/

import java.util.Map;

import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.model.WorkRecordsPO;
import com.djcps.wms.workrecords.model.param.WorkRecordsParam;

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
	Map<String, Object> getAllRecordListByOperationType(WorkRecordsBO param);

	/**
	 *查询入库装车工作记录详情
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> getWorkRecordsDetail(WorkRecordsParam param);

	/**
	 *查询提货工作记录信息
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> getDeliveryRecordList(WorkRecordsBO param);

	/**
	 *查询入库装车工作记录详情
	 * @param param
	 * @return
	 * @throws Exception
	 * @author:py
	 * @date:2018年4月20日
	 */
	Map<String, Object> getDeliveryWorkRecordsDetail(WorkRecordsParam param);

}
