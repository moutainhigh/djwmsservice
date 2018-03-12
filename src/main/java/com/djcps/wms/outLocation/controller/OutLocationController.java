package com.djcps.wms.outLocation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.wms.outLocation.model.OrderIdBO;
import com.djcps.wms.outLocation.service.OutLocationService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/outLocation")
public class OutLocationController {
	@Autowired
	private OutLocationService outLocationService;
	
	private Gson gson = new Gson();
	@RequestMapping(name="获取订单明细表",value="/getOrderDetail",method = RequestMethod.POST)
	public Map<String, Object> getOrderDetail(@RequestBody String json){
		OrderIdBO orderIdBO = gson.fromJson(json, OrderIdBO.class);
		return outLocationService.getOrderDetailByOrderId(orderIdBO);
		
	}
}
