package com.djcps.wms.workrecords.service.impl;

import java.util.Map;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.record.model.result.OperationRecordResult;
import com.djcps.wms.record.service.workRecordService;
import com.djcps.wms.workrecords.model.WorkRecordsInfoPO;
import com.djcps.wms.workrecords.model.param.WorkRecordsParam;
import com.djcps.wms.workrecords.server.WorkRecordServer;

/**
* @author panyang
* @version 创建时间：2018年4月17日 下午3:49:26
* 工作记录实现类
*/
public class WorkRecordServiceImpl implements workRecordService {

	
	
	private WorkRecordServer  workRecordServer;
	/**
	 * 
	 * 入库记录操作
	 */
	@Override
	public Map<String, Object> getentryRecordList(WorkRecordsParam fromJson) {
		 HttpResult result = workRecordServer.getEntryRecordList(fromJson);
	        return MsgTemplate.customMsg(result);
		
		
		return null;
	}

	@Override
	public Map<String, Object> loadingTableRecordList(WorkRecordsParam fromJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> deliveryRecordList(WorkRecordsParam fromJson) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
