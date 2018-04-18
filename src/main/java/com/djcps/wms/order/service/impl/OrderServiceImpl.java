package com.djcps.wms.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
import com.google.gson.reflect.TypeToken;


/**
 *  订单业务层实现类
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private Gson gson = new Gson();
	
	private JsonParser jsonParser = new JsonParser();

	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderServer orderServer;

	@Override 
	public Map<String, Object> getAllOrderList(OrderParamBO param) {
		if(StringUtils.isEmpty(param.getChildOrderModel().getFstatus())){
			param.getChildOrderModel().setFstatus("2");
		}
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
		SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		BeanUtils.copyProperties(param, selectAreaByOrderId);
		List<OrderIdBO> list = new ArrayList<OrderIdBO>();
		List<WarehouseOrderDetailPO> detailList = new ArrayList<WarehouseOrderDetailPO>();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>(16);
		List<WarehouseOrderDetailPO> fromJsonDetailList = gson.fromJson(gson.toJson(result.getData()), new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
		for (WarehouseOrderDetailPO warehouseOrderDetailPO : fromJsonDetailList) {
			String orderId = warehouseOrderDetailPO.getFchildorderid();
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(orderId);
			list.add(orderIdBO);
			warehouseOrderDetailPO.setAmountSaved(0);
			//组织参数
			getOrderDetail(warehouseOrderDetailPO,warehouseOrderDetailPO);
			detailList.add(warehouseOrderDetailPO);
			map.put(warehouseOrderDetailPO.getFchildorderid(), warehouseOrderDetailPO);
		}
		selectAreaByOrderId.setOrderIds(list);
		//根据id获取在库信息
		List<WarehouseOrderDetailPO> resultList = getStockInfo(selectAreaByOrderId);
		//根据id存入到map中
		if(!ObjectUtils.isEmpty(resultList)){
			for (WarehouseOrderDetailPO warehouseOrderDetailPO : resultList) {
				WarehouseOrderDetailPO detatil = map.get(warehouseOrderDetailPO.getOrderId());
				if(detatil!=null){
					//组织参数
					getOrderDetail(detatil,detatil);
					detatil.setAreaList(warehouseOrderDetailPO.getAreaList());
					detatil.setOrderId(warehouseOrderDetailPO.getOrderId());
					detatil.setFchildorderid(warehouseOrderDetailPO.getOrderId());
					detatil.setAmountSaved(warehouseOrderDetailPO.getAmountSaved());
					detatil.setAmount(warehouseOrderDetailPO.getAmount());
					detatil.setFamount(warehouseOrderDetailPO.getFamount());
					detatil.setWarehouseId(warehouseOrderDetailPO.getWarehouseId());
					detatil.setWarehouseName(warehouseOrderDetailPO.getWarehouseName());
				}
			}
		}
		//因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
		Map<String, Object> resultMap = new HashMap<String, Object>(16);
		resultMap.put("success", true);
		resultMap.put("code", 100000);
		resultMap.put("msg", "");
		resultMap.put("total", result.getTotalCount());
		resultMap.put("data", detailList);
		return resultMap;
	}

	@Override
	public Map<String, Object> getOrderByOrderId(OrderIdBO param) {
	    
		HttpResult result = orderServer.getOrderByOrderId(param);
		if(result.isSuccess()){
			WarehouseOrderDetailPO paperOrder = new WarehouseOrderDetailPO();
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, selectAreaByOrderId);
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
				//组织参数
				getOrderDetail(warehouseOrderDetailPO,fromJson);
				return MsgTemplate.successMsg(warehouseOrderDetailPO);
			}else{
				//组织参数
				getOrderDetail(fromJson,fromJson);
				fromJson.setAmountSaved(0);
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
	
	
	/**
	 * 参数拼接
	 * @param source
	 * @param target
	 * @return
	 * @author:zdx
	 * @date:2018年1月8日
	 */
	@Override
	public WarehouseOrderDetailPO getOrderDetail(WarehouseOrderDetailPO source,WarehouseOrderDetailPO target){
		//规格长宽高都不为null,才进行拼接
		if(!ObjectUtils.isEmpty(target.getFboxlength()) && !ObjectUtils.isEmpty(target.getFboxwidth()) &&
				!ObjectUtils.isEmpty(target.getFboxheight())){
			//拼接字符串,拼接成产品规格和下料规格
			source.setFproductRule(new StringBuffer().append(target.getFboxlength()).append("*")
					.append(target.getFboxwidth()).append("*").append(target.getFboxheight()).toString());
		}
		if(!ObjectUtils.isEmpty(target.getFmateriallength()) && !ObjectUtils.isEmpty(target.getFmaterialwidth())){
			source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
					.append(target.getFmaterialwidth()).toString());
		}
		source.setFboxheight(target.getFboxheight());
		source.setFboxlength(target.getFboxlength());
		source.setFboxwidth(target.getFboxwidth());
		source.setFmateriallength(target.getFmateriallength());
		source.setFmaterialname(source.getFmaterialname());
		source.setFordertime(target.getFordertime());
		source.setFdelivery(target.getFdelivery());
		source.setFgroupgoodname(target.getFgroupgoodname());
		source.setFflutetype(target.getFflutetype());
		source.setFstatus(target.getFstatus());
		source.setFmaterialname(target.getFmaterialname());
		source.setFlnglat(target.getFlnglat());
		source.setFpaymenttime(target.getFpaymenttime());
		source.setFaddressdetail(target.getFaddressdetail());
		source.setFcodeprovince(target.getFcodeprovince());
		source.setFconsignee(target.getFconsignee());
		source.setFcontactway(target.getFcontactway());
		source.setFpusername(target.getFpusername());
		if(!ObjectUtils.isEmpty(source.getFchildorderid())){
			source.setOrderId(source.getFchildorderid());
		}
		if(!ObjectUtils.isEmpty(source.getFamount())){
			source.setAmount(source.getFamount());
		}
		if(!ObjectUtils.isEmpty(source.getAmount())){
			source.setFamount(source.getAmount());
		}
		return source;
	}
}
