package com.djcps.wms.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.address.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.PaperOrderBo;
import com.djcps.wms.order.model.WarehouseAreaBo;
import com.djcps.wms.order.model.WarehouseLocationBo;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.provider.controller.ProviderController;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;
import com.djcps.wms.provider.server.ProviderServer;
import com.djcps.wms.provider.service.ProviderService;
import com.djcps.wms.stock.model.OrderIdBo;
import com.djcps.wms.stock.model.SelectAreaByOrderId;
import com.djcps.wms.stock.server.StockServer;
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
	public Map<String, Object> getAllOrderList(PaperOrderBo paperOrder) {
		PaperOrderBo PaperOrder1 = new PaperOrderBo();
		PaperOrderBo PaperOrder2 = new PaperOrderBo();
		PaperOrderBo PaperOrder3 = new PaperOrderBo();
		PaperOrderBo PaperOrder4 = new PaperOrderBo();
		PaperOrderBo PaperOrder5 = new PaperOrderBo();
		PaperOrderBo PaperOrder6 = new PaperOrderBo();
		PaperOrderBo PaperOrder7 = new PaperOrderBo();
		PaperOrderBo PaperOrder8 = new PaperOrderBo();
		PaperOrderBo PaperOrder9 = new PaperOrderBo();
		PaperOrderBo PaperOrder10 = new PaperOrderBo();
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
		OrderIdBo orderId1 = new OrderIdBo();
		OrderIdBo orderId2 = new OrderIdBo();
		OrderIdBo orderId3 = new OrderIdBo();
		OrderIdBo orderId4 = new OrderIdBo();
		OrderIdBo orderId5 = new OrderIdBo();
		OrderIdBo orderId6 = new OrderIdBo();
		OrderIdBo orderId7 = new OrderIdBo();
		OrderIdBo orderId8 = new OrderIdBo();
		OrderIdBo orderId9 = new OrderIdBo();
		OrderIdBo orderId10 = new OrderIdBo();
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
		SelectAreaByOrderId param = new SelectAreaByOrderId();
		param.setOrderIds(list);
		//根据id获取在库信息
		return  getStockInfo(param);
	}

	@Override
	public Map<String, Object> getOrderByOrderId(OrderIdBo param) {
		PaperOrderBo PaperOrder1 = new PaperOrderBo();
		PaperOrder1.setOrderId("0000");
		SelectAreaByOrderId selectAreaByOrderId = new SelectAreaByOrderId();
		List list = new ArrayList<>();
		list.add(PaperOrder1);
		selectAreaByOrderId.setOrderIds(list);
//		param.setOrderIds(list);
		return  getStockInfo(selectAreaByOrderId);
	}

	private Map<String, Object> getStockInfo(SelectAreaByOrderId param){
		List<PaperOrderBo> paperOrderList = new ArrayList<PaperOrderBo>();
		//根据id查询
		Map<String, Object> areaByOrderIdMap = stockService.getAreaByOrderId(param);
		Object object = areaByOrderIdMap.get("data");
		if(!ObjectUtils.isEmpty(object)){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(object)).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				PaperOrderBo paperOrderBo = gson.fromJson(jsonElement, PaperOrderBo.class);
				paperOrderList.add(paperOrderBo);
				JsonArray areaArray = jsonParser.parse(gson.toJson(jsonElement)).getAsJsonObject().get("warehouseAreaInfo").getAsJsonArray();
				//遍历库区
				List<WarehouseAreaBo> areaList = new ArrayList<WarehouseAreaBo>();
				paperOrderBo.setAreaList(areaList);
				for (JsonElement jsonElement2 : areaArray) {
					 WarehouseAreaBo area = gson.fromJson(jsonElement2, WarehouseAreaBo.class);
					 areaList.add(area);
					 //一个库区可能会有多个库位库位,每次遍历库区创建库位list,把list赋值给库区
					 List<WarehouseLocationBo> locationList = new ArrayList<WarehouseLocationBo>();
					 area.setLocationList(locationList);
					 JsonArray locationArray = jsonParser.parse(gson.toJson(jsonElement2)).getAsJsonObject().get("warehouseLocInfo").getAsJsonArray();
					 //遍历库位
					 for (JsonElement jsonElement3 : locationArray) {
						 WarehouseLocationBo location = gson.fromJson(jsonElement3, WarehouseLocationBo.class);
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
