package com.djcps.wms.workrecords.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.warehouse.model.area.WarehouseAreaPO;
import com.djcps.wms.warehouse.service.impl.AreaServiceImpl;
import com.djcps.wms.workrecords.enums.WorkRecordEnums;
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.model.WorkRecordsInfoPO;
import com.djcps.wms.workrecords.model.WorkRecordsPO;
import com.djcps.wms.workrecords.model.param.WorkRecordsParam;
import com.djcps.wms.workrecords.server.WorkRecordServer;
import com.djcps.wms.workrecords.service.WorkRecordService;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import sun.awt.windows.WPrinterJob;

/**
 * @author panyang
 * @version 创建时间：2018年4月17日 下午3:49:26 工作记录实现类
 */
@Service
public class WorkRecordServiceImpl implements WorkRecordService {

	@Autowired
	private WorkRecordServer workRecordServer;

	private Gson gson = new Gson();

	/**
	 * 根据操作类类型获取相关工作记录信息
	 * 
	 * @author py
	 * @create :2018/4/18
	 */
	@Override
	public Map<String, Object> getAllRecordListByOperationType(WorkRecordsBO param) {
		HttpResult result = workRecordServer.getAllRecordListByOperationType(param);
		// int operationType = param.getOperationType();
		// BaseVO work=gson.fromJson(gson.toJson(result.getData()), BaseVO.class);
		// return MsgTemplate.successMsg(work);
		if (!ObjectUtils.isEmpty(result)) {
			return MsgTemplate.customMsg(result);
		}
		return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	}

	/**
	 * 根据id,楞型获取相关工作记录详情
	 * 
	 * @author py
	 * @create :2018/4/18
	 */
	@Override
	public Map<String, Object> getWorkRecordsDetail(WorkRecordsParam param) {
		HttpResult result = workRecordServer.getWorkRecordsDetail(param);
		if (!ObjectUtils.isEmpty(result)) {
			return MsgTemplate.customMsg(result);
		}
		return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	}


	/**
	 * 获取提货的工作记录相关信息  
	 */
	
	@Override
	public Map<String, Object> getDeliveryRecordList(WorkRecordsBO param) {
		HttpResult result = workRecordServer.getDeliveryRecordList(param);
		BaseVO baseVO = gson.fromJson(gson.toJson(result.getData()), BaseVO.class);
		List<WorkRecordsPO> list = gson.fromJson(gson.toJson(baseVO.getResult()), new TypeToken<List<WorkRecordsPO>>() {
		}.getType());
		List<WorkRecordsPO> list2 = new ArrayList<WorkRecordsPO>();
		for (WorkRecordsPO workRecordsPO : list) {
			if (workRecordsPO.getTotalArea() != 0) {
				list2.add(workRecordsPO);
			}
		}
		BaseVO b = new BaseVO();
		b.setResult(list2);
		b.setTotal(list2.size());
		return MsgTemplate.successMsg(b);
	}

	/**
	 * 工作记录详情
	 * 
	 */

	@Override
	public Map<String, Object> getDeliveryWorkRecordsDetail(WorkRecordsParam param) {
		HttpResult result = workRecordServer.getDeliveryWorkRecordsDetail(param);
		BaseVO baseVO = gson.fromJson(gson.toJson(result.getData()), BaseVO.class);
		List<WorkRecordsInfoPO> list = gson.fromJson(gson.toJson(baseVO.getResult()),
				new TypeToken<List<WorkRecordsInfoPO>>() {
				}.getType());
		List<WorkRecordsInfoPO> list2 = new ArrayList<WorkRecordsInfoPO>();
		for (WorkRecordsInfoPO workRecordsInfoPO : list) {
			if (workRecordsInfoPO.getArea() != 0) {
				list2.add(workRecordsInfoPO);
			}
		}
		BaseVO b = new BaseVO();
		b.setResult(list2);
		b.setTotal(list2.size());
		return MsgTemplate.successMsg(b);

	}
}
