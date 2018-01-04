package com.djcps.wms.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OrderHttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import com.djcps.wms.order.model.WarehouseLocationBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.service.StockService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


/**
 *  订单业务层实现类
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
	
	@Autowired
	private OrderServer orderServer;

	@Override 
	public Map<String, Object> getAllOrderList(OrderParamBO param) {
		OrderHttpResult result = orderServer.getAllOrderList(param);
		if(!result.isSuccess()){
			HttpResult otherResult = new HttpResult();
			BeanUtils.copyProperties(result, otherResult);
			return MsgTemplate.customMsg(otherResult); 
		}
		// data数据为空将值赋值为null,这里取到的是空数组
		if(ObjectUtils.isEmpty(result.getData())){
			return MsgTemplate.successMsg(null); 
		}
		JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();  
		SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		List list = new ArrayList<OrderIdBO>();
		List detailList = new ArrayList<WarehouseOrderDetailPO>();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>();
		for (JsonElement jsonElement : asJsonArray) {
			String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fchildorderid").getAsString();
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(orderId);
			list.add(orderIdBO);
			WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
			//规格长宽高都不为null,才进行拼接
			if(!ObjectUtils.isEmpty(fromJson.getFboxlength()) && !ObjectUtils.isEmpty(fromJson.getFboxwidth()) &&
					!ObjectUtils.isEmpty(fromJson.getFboxheight())){
				//拼接字符串,拼接成产品规格和下料规格
				fromJson.setFproductRule(new StringBuffer().append(fromJson.getFboxlength()).append("*")
						.append(fromJson.getFboxwidth()).append("*").append(fromJson.getFboxheight()).toString());
			}
			fromJson.setFmaterialRule(new StringBuffer().append(fromJson.getFmateriallength()).append("*")
					.append(fromJson.getFmaterialwidth()).toString());
			fromJson.setOrderId(fromJson.getFchildorderid());
			fromJson.setAmount(fromJson.getFamount());
			fromJson.setAmountSaved("0");
			detailList.add(fromJson);
		}
		selectAreaByOrderId.setOrderIds(list);
		//根据id获取在库信息
		List<WarehouseOrderDetailPO> resultList = getStockInfo(selectAreaByOrderId);
		if(resultList==null){
			return MsgTemplate.successMsg(detailList);
		}
		//根据id存入到map中
		for (WarehouseOrderDetailPO warehouseOrderDetailPO : resultList) {
			map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
		}
		for (JsonElement jsonElement : asJsonArray) {
			String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fchildorderid").getAsString();
			WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderId);
			if(warehouseOrderDetailPO!=null){
				WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
				//规格长宽高都不为null,才进行拼接
				if(!ObjectUtils.isEmpty(fromJson.getFboxlength()) && !ObjectUtils.isEmpty(fromJson.getFboxwidth()) &&
						!ObjectUtils.isEmpty(fromJson.getFboxheight())){
					//拼接字符串,拼接成产品规格和下料规格
					warehouseOrderDetailPO.setFproductRule(new StringBuffer().append(fromJson.getFboxlength()).append("*")
							.append(fromJson.getFboxwidth()).append("*").append(fromJson.getFboxheight()).toString());
				}
				warehouseOrderDetailPO.setFmaterialRule(new StringBuffer().append(fromJson.getFmateriallength()).append("*")
						.append(fromJson.getFmaterialwidth()).toString());
				//拼接参数
				warehouseOrderDetailPO.setFordertime(fromJson.getFordertime());
				warehouseOrderDetailPO.setFdelivery(fromJson.getFdelivery());
				warehouseOrderDetailPO.setFgroupgoodname(fromJson.getFgroupgoodname());
				warehouseOrderDetailPO.setFflutetype(fromJson.getFflutetype());
				warehouseOrderDetailPO.setFstatus(fromJson.getFstatus());
				warehouseOrderDetailPO.setFmaterialname(fromJson.getFmaterialname());
				warehouseOrderDetailPO.setFlnglat(fromJson.getFlnglat());
				warehouseOrderDetailPO.setFpaymenttime(fromJson.getFpaymenttime());
				warehouseOrderDetailPO.setFaddressdetail(fromJson.getFaddressdetail());
				warehouseOrderDetailPO.setFcodeprovince(fromJson.getFcodeprovince());
				warehouseOrderDetailPO.setFconsignee(fromJson.getFconsignee());
				warehouseOrderDetailPO.setFcontactway(fromJson.getFcontactway());
				warehouseOrderDetailPO.setFpusername(fromJson.getFpusername());
				warehouseOrderDetailPO.setFamount(warehouseOrderDetailPO.getAmount());
			}
		}
		//因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("success", true);
		resultMap.put("code", 100000);
		resultMap.put("msg", "");
		resultMap.put("total", result.getTotalCount());
		resultMap.put("data", resultList);
		return resultMap;
	}

	@Override
	public Map<String, Object> getOrderByOrderId(OrderIdBO param) {
		HttpResult result = orderServer.getOrderByOrderId(param);
		if(result.isSuccess()){
			WarehouseOrderDetailPO paperOrder = new WarehouseOrderDetailPO();
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			List list = new ArrayList<OrderIdBO>();
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject().get("fchildorderid").getAsString());
			list.add(orderIdBO);
			selectAreaByOrderId.setOrderIds(list);
			//根据id获取在库信息
			List<WarehouseOrderDetailPO> resultList = getStockInfo(selectAreaByOrderId);
			WarehouseOrderDetailPO fromJson = gson.fromJson(gson.toJson(result.getData()), WarehouseOrderDetailPO.class);
			if(resultList!=null){
				WarehouseOrderDetailPO warehouseOrderDetailPO = resultList.get(0);
				//规格长宽高都不为null,才进行拼接
				if(!ObjectUtils.isEmpty(fromJson.getFboxlength()) && !ObjectUtils.isEmpty(fromJson.getFboxwidth()) &&
						!ObjectUtils.isEmpty(fromJson.getFboxheight())){
					//拼接字符串,拼接成产品规格和下料规格
					warehouseOrderDetailPO.setFproductRule(new StringBuffer().append(fromJson.getFboxlength()).append("*")
							.append(fromJson.getFboxwidth()).append("*").append(fromJson.getFboxheight()).toString());
				}
				//拼接参数
				warehouseOrderDetailPO.setFmaterialRule(new StringBuffer().append(fromJson.getFmateriallength()).append("*")
						.append(fromJson.getFmaterialwidth()).toString());
				warehouseOrderDetailPO.setFordertime(fromJson.getFordertime());
				warehouseOrderDetailPO.setFdelivery(fromJson.getFdelivery());
				warehouseOrderDetailPO.setFgroupgoodname(fromJson.getFgroupgoodname());
				warehouseOrderDetailPO.setFflutetype(fromJson.getFflutetype());
				warehouseOrderDetailPO.setFstatus(fromJson.getFstatus());
				warehouseOrderDetailPO.setFmaterialname(fromJson.getFmaterialname());
				warehouseOrderDetailPO.setFlnglat(fromJson.getFlnglat());
				warehouseOrderDetailPO.setFpaymenttime(fromJson.getFpaymenttime());
				warehouseOrderDetailPO.setFaddressdetail(fromJson.getFaddressdetail());
				warehouseOrderDetailPO.setFcodeprovince(fromJson.getFcodeprovince());
				warehouseOrderDetailPO.setFconsignee(fromJson.getFconsignee());
				warehouseOrderDetailPO.setFcontactway(fromJson.getFcontactway());
				warehouseOrderDetailPO.setFpusername(fromJson.getFpusername());
				warehouseOrderDetailPO.setFamount(warehouseOrderDetailPO.getAmount());
				return MsgTemplate.successMsg(warehouseOrderDetailPO);
			}else{
				fromJson.setFproductRule(new StringBuffer().append(fromJson.getFboxlength()).append("*")
						.append(fromJson.getFboxwidth()).append("*").append(fromJson.getFboxheight()).toString());
				fromJson.setFmaterialRule(new StringBuffer().append(fromJson.getFmateriallength()).append("*")
						.append(fromJson.getFmaterialwidth()).toString());
				fromJson.setOrderId(fromJson.getFchildorderid());
				fromJson.setAmount(fromJson.getFamount());
				fromJson.setAmountSaved("0");
				return MsgTemplate.successMsg(fromJson);
			}
		}else{
			return MsgTemplate.customMsg(result);
		}
	}

	/**
	 * 根据id去获取在库信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年1月2日
	 */
	@Override
	public List<WarehouseOrderDetailPO> getStockInfo(SelectAreaByOrderIdBO param){
		List<WarehouseOrderDetailPO> paperOrderList = new ArrayList<WarehouseOrderDetailPO>();
		//根据id查询
		Map<String, Object> areaByOrderIdMap = stockService.getAreaByOrderId(param);
		Object object = areaByOrderIdMap.get("data");
		if(!ObjectUtils.isEmpty(object)){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(object)).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				WarehouseOrderDetailPO paperOrderBo = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
				paperOrderList.add(paperOrderBo);
				JsonElement jsoElem = jsonParser.parse(gson.toJson(jsonElement)).getAsJsonObject().get("warehouseAreaInfo");
				if(jsoElem!=null){
					JsonArray areaArray = jsoElem.getAsJsonArray();
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
			}
			return paperOrderList;
		}else{
			return null;
		}
	}

}
