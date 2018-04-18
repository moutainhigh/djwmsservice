package com.djcps.wms.workrecords.service;

/**
* @author panyang
* @version 创建时间：2018年4月17日 下午3:48:42
* 工作记录业务层
*/

import java.util.Map;

import com.djcps.wms.workrecords.model.WorkRecordsBO;


public interface WorkRecordService {
	
	/**
	 * 获取工作记录信息
	 *@author py
	 */
	Map<String, Object>   getAllRecordList(WorkRecordsBO  fromJson );  
	

}
