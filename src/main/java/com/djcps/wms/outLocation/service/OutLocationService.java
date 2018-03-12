package com.djcps.wms.outLocation.service;

import java.util.Map;

import com.djcps.wms.outLocation.model.OrderIdBO;

public interface OutLocationService {
	Map<String, Object> getOrderDetailByOrderId(OrderIdBO param);
}
