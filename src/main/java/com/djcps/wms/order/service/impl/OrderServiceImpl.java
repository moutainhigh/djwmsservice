package com.djcps.wms.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.PaperOrderBO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import com.djcps.wms.order.model.WarehouseLocationBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.OrderIdBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.service.StockService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 * 订单业务层实现类
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);	
	
	private Gson gson = new Gson();
	
	private JsonParser jsonParser = new JsonParser();

	@Autowired
	private StockService stockService;

	@Override
	public Map<String, Object> getAllOrderList(PaperOrderBO paperOrder) {
		PaperOrderBO PaperOrder1 = new PaperOrderBO();
		PaperOrderBO PaperOrder2 = new PaperOrderBO();
		PaperOrderBO PaperOrder3 = new PaperOrderBO();
		PaperOrderBO PaperOrder4 = new PaperOrderBO();
		PaperOrderBO PaperOrder5 = new PaperOrderBO();
		PaperOrderBO PaperOrder6 = new PaperOrderBO();
		PaperOrderBO PaperOrder7 = new PaperOrderBO();
		PaperOrderBO PaperOrder8 = new PaperOrderBO();
		PaperOrderBO PaperOrder9 = new PaperOrderBO();
		PaperOrderBO PaperOrder10 = new PaperOrderBO();
		PaperOrder1.setOrderId("0000");
		PaperOrder2.setOrderId("1111");
		PaperOrder3.setOrderId("2222");
		PaperOrder4.setOrderId("3333");
		PaperOrder5.setOrderId("4444");
		PaperOrder6.setOrderId("5555");
		PaperOrder7.setOrderId("6666");
		PaperOrder8.setOrderId("7777");
		PaperOrder9.setOrderId("8888");
		PaperOrder10.setOrderId("9999");
		List list = new ArrayList<>();
		OrderIdBO orderId1 = new OrderIdBO();
		OrderIdBO orderId2 = new OrderIdBO();
		OrderIdBO orderId3 = new OrderIdBO();
		OrderIdBO orderId4 = new OrderIdBO();
		OrderIdBO orderId5 = new OrderIdBO();
		OrderIdBO orderId6 = new OrderIdBO();
		OrderIdBO orderId7 = new OrderIdBO();
		OrderIdBO orderId8 = new OrderIdBO();
		OrderIdBO orderId9 = new OrderIdBO();
		OrderIdBO orderId10 = new OrderIdBO();
		orderId1.setOrderId((PaperOrder1.getOrderId()));
		orderId2.setOrderId((PaperOrder2.getOrderId()));
		orderId3.setOrderId((PaperOrder3.getOrderId()));
		orderId4.setOrderId((PaperOrder4.getOrderId()));
		orderId5.setOrderId((PaperOrder5.getOrderId()));
		orderId6.setOrderId((PaperOrder6.getOrderId()));
		orderId7.setOrderId((PaperOrder7.getOrderId()));
		orderId8.setOrderId((PaperOrder8.getOrderId()));
		orderId9.setOrderId((PaperOrder9.getOrderId()));
		orderId10.setOrderId((PaperOrder10.getOrderId()));
		list.add(orderId1);
		list.add(orderId2);
		list.add(orderId3);
		list.add(orderId4);
		list.add(orderId5);
		list.add(orderId6);
		list.add(orderId7);
		list.add(orderId8);
		list.add(orderId9);
		list.add(orderId10);
		SelectAreaByOrderIdBO param = new SelectAreaByOrderIdBO();
		param.setOrderIds(list);
		//根据id获取在库信息
		return  getStockInfo(param);
	}

	@Override
	public Map<String, Object> getOrderByOrderId(OrderIdBO param) {
		PaperOrderBO PaperOrder1 = new PaperOrderBO();
		PaperOrder1.setOrderId("0000");
		SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		List list = new ArrayList<>();
		list.add(PaperOrder1);
		selectAreaByOrderId.setOrderIds(list);
//		param.setOrderIds(list);
		Map<String, Object> map = getStockInfo(selectAreaByOrderId);
		List dateList =(List)map.get("data");
		map.put("data", dateList.get(0));
		return map;
	}

	private Map<String, Object> getStockInfo(SelectAreaByOrderIdBO param){
		List<PaperOrderBO> paperOrderList = new ArrayList<PaperOrderBO>();
		//根据id查询
		Map<String, Object> areaByOrderIdMap = stockService.getAreaByOrderId(param);
		Object object = areaByOrderIdMap.get("data");
		if(!ObjectUtils.isEmpty(object)){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(object)).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				PaperOrderBO paperOrderBo = gson.fromJson(jsonElement, PaperOrderBO.class);
				paperOrderList.add(paperOrderBo);
				JsonArray areaArray = jsonParser.parse(gson.toJson(jsonElement)).getAsJsonObject().get("warehouseAreaInfo").getAsJsonArray();
				//遍历库区
				List<WarehouseAreaBO> areaList = new ArrayList<WarehouseAreaBO>();
				paperOrderBo.setAreaList(areaList);
				for (JsonElement jsonElement2 : areaArray) {
					 WarehouseAreaBO area = gson.fromJson(jsonElement2, WarehouseAreaBO.class);
					 areaList.add(area);
					 //一个库区可能会有多个库位库位,每次遍历库区创建库位list,把list赋值给库区
					 List<WarehouseLocationBO> locationList = new ArrayList<WarehouseLocationBO>();
					 area.setLocationList(locationList);
					 JsonArray locationArray = jsonParser.parse(gson.toJson(jsonElement2)).getAsJsonObject().get("warehouseLocInfo").getAsJsonArray();
					 //遍历库位
					 for (JsonElement jsonElement3 : locationArray) {
						 WarehouseLocationBO location = gson.fromJson(jsonElement3, WarehouseLocationBO.class);
						 locationList.add(location);
					}
				}
			}
			return MsgTemplate.successMsg(paperOrderList);
		}else{
			return areaByOrderIdMap;
		}
	}

}
