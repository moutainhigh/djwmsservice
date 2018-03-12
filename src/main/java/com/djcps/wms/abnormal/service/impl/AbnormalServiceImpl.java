package com.djcps.wms.abnormal.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.abnormal.service.AbnormalService;
import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;

/**
 * 异常订单业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
@Service
public class AbnormalServiceImpl implements AbnormalService {
	
	@Autowired
	private AbnormalServer abnormalServer;
	
	@Override
	public Map<String, Object> getOrderByAttributeBO(GetOrderByAttributeBO param) {
		HttpResult result = abnormalServer.getOrderByAttributeBO(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getSplitOrderByOrderId(OrderIdBO param) {
		HttpResult result = abnormalServer.getSplitOrderByOrderId(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> updateAbnormal(UpdateAbnormalBO param) {
		param.setSubmiter(param.getOperator());
		HttpResult result = abnormalServer.updateAbnormal(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addAbnormal(AddAbnormal param) {
		HttpResult result = abnormalServer.addAbnormal(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getOrderByOrderIdList(OrderIdListBO param) {
		HttpResult result = abnormalServer.getOrderByOrderIdList(param);
		return MsgTemplate.customMsg(result);
	}

}
