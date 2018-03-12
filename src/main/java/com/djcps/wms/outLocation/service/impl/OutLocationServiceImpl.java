package com.djcps.wms.outLocation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.outLocation.model.OrderIdBO;
import com.djcps.wms.outLocation.model.OrderInfo;
import com.djcps.wms.outLocation.server.OutLocationServer;
import com.djcps.wms.outLocation.service.OutLocationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("outLocationService")
public class OutLocationServiceImpl implements OutLocationService{
	@Autowired
	private OutLocationServer outLocationServer;
	
	private Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
	@Override
	public Map<String, Object> getOrderDetailByOrderId(OrderIdBO param) {
		HttpResult result = outLocationServer.getOrderDetailByOrderId(param);
		if(result.isSuccess()){
			Object data = result.getData();
			OrderInfo orderInfo = gson.fromJson(gson.toJson(result.getData()), OrderInfo.class);
			result.setData(orderInfo);
		}
		return MsgTemplate.customMsg(result);
	}

}
