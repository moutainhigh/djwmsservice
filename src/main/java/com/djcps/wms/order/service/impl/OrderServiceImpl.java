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

import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.enums.FluteTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OrderHttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.WmsOrderInvolveInfoBO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardResultDataPO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitSonOrderBO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import com.djcps.wms.order.model.WarehouseLocationBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.server.StockServer;
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
	
	@Autowired
	private StockServer stockServer;
	
	@Override 
	public Map<String, Object> getAllOrderList(OrderParamBO param) {/*
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
//			String orderId = warehouseOrderDetailPO.getFchildorderid();
			OrderIdBO orderIdBO = new OrderIdBO();
//			orderIdBO.setOrderId(orderId);
			list.add(orderIdBO);
			warehouseOrderDetailPO.setAmountSaved(0);
			//组织参数
			getOrderDetail(warehouseOrderDetailPO,warehouseOrderDetailPO);
			detailList.add(warehouseOrderDetailPO);
//			map.put(warehouseOrderDetailPO.getFchildorderid(), warehouseOrderDetailPO);
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
//					detatil.setFchildorderid(warehouseOrderDetailPO.getOrderId());
					detatil.setAmountSaved(warehouseOrderDetailPO.getAmountSaved());
//					detatil.setAmount(warehouseOrderDetailPO.getAmount());
//					detatil.setFamount(warehouseOrderDetailPO.getFamount());
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
	*/return null;
		}

	@Override
	public Map<String, Object> getOrderByOrderId(OrderIdBO param) {
//		HttpResult result = orderServer.getOrderByOrderId(param);
//		if(result.isSuccess()){
//			WarehouseOrderDetailPO paperOrder = new WarehouseOrderDetailPO();
//			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
//			BeanUtils.copyProperties(param, selectAreaByOrderId);
//			List list = new ArrayList<OrderIdBO>();
//			OrderIdBO orderIdBO = new OrderIdBO();
//			orderIdBO.setOrderId(new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject().get("fchildorderid").getAsString());
//			list.add(orderIdBO);
//			selectAreaByOrderId.setOrderIds(list);
//			//根据id获取在库信息
//			List<WarehouseOrderDetailPO> resultList = getStockInfo(selectAreaByOrderId);
//			WarehouseOrderDetailPO fromJson = gson.fromJson(gson.toJson(result.getData()), WarehouseOrderDetailPO.class);
//			if(resultList!=null){
//				WarehouseOrderDetailPO warehouseOrderDetailPO = resultList.get(0);
//				//组织参数
//				getOrderDetail(warehouseOrderDetailPO,fromJson);
//				return MsgTemplate.successMsg(warehouseOrderDetailPO);
//			}else{
//				//组织参数
//				getOrderDetail(fromJson,fromJson);
//				fromJson.setAmountSaved(0);
//				return MsgTemplate.successMsg(fromJson);
//			}
//		}else{
//			return MsgTemplate.customMsg(result);
//		}
		return null;
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
//		List<WarehouseOrderDetailPO> paperOrderList = new ArrayList<WarehouseOrderDetailPO>();
//		//根据id查询
//		Map<String, Object> areaByOrderIdMap = stockService.getAreaByOrderId(param);
//		Object object = areaByOrderIdMap.get("data");
//		if(!ObjectUtils.isEmpty(object)){
//			JsonArray asJsonArray = jsonParser.parse(gson.toJson(object)).getAsJsonArray();
//			for (JsonElement jsonElement : asJsonArray) {
//				WarehouseOrderDetailPO paperOrderBo = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
//				paperOrderList.add(paperOrderBo);
//				JsonElement jsoElem = jsonParser.parse(gson.toJson(jsonElement)).getAsJsonObject().get("warehouseAreaInfo");
//				if(jsoElem!=null){
//					JsonArray areaArray = jsoElem.getAsJsonArray();
//					//遍历库区
//					List<WarehouseAreaBO> areaList = new ArrayList<WarehouseAreaBO>();
//					paperOrderBo.setAreaList(areaList);
//					for (JsonElement jsonElement2 : areaArray) {
//						 WarehouseAreaBO area = gson.fromJson(jsonElement2, WarehouseAreaBO.class);
//						 areaList.add(area);
//						 //一个库区可能会有多个库位库位,每次遍历库区创建库位list,把list赋值给库区
//						 List<WarehouseLocationBO> locationList = new ArrayList<WarehouseLocationBO>();
//						 area.setLocationList(locationList);
//						 JsonArray locationArray = jsonParser.parse(gson.toJson(jsonElement2)).getAsJsonObject().get("warehouseLocInfo").getAsJsonArray();
//						 //遍历库位
//						 for (JsonElement jsonElement3 : locationArray) {
//							 WarehouseLocationBO location = gson.fromJson(jsonElement3, WarehouseLocationBO.class);
//							 locationList.add(location);
//						}
//					}
//				}
//			}
//			return paperOrderList;
//		}else{
//			return null;
//		}
		return null;
	}

	@Override
	public Map<String, Object> getOnlinePaperboardList(QueryObjectBO param) {
		HttpResult onlinePaperResult = orderServer.getOnlinePaperboardList(param);
		if(!ObjectUtils.isEmpty(onlinePaperResult.getData())){
			OnlinePaperboardResultDataPO paperResultData = gson.fromJson(gson.toJson(onlinePaperResult.getData()), OnlinePaperboardResultDataPO.class);
			//根据订单号获取在库信息
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, selectAreaByOrderId);
			//伪代码
			selectAreaByOrderId.setPartnerId("100");
			//伪代码
			List<OrderIdBO> orderIdBOList = new ArrayList<OrderIdBO>();
			for (WarehouseOrderDetailPO onlinePaperboardPO : paperResultData.getContent()) {
				OrderIdBO orderIdBO = new OrderIdBO();
				orderIdBO.setOrderId(onlinePaperboardPO.getChildOrderId());
				orderIdBOList.add(orderIdBO);
			}
			selectAreaByOrderId.setOrderIds(orderIdBOList);
			//根据id获取在库信息
			List<WarehouseOrderDetailPO> orderStockList = orderServer.getOrderStockInfo(selectAreaByOrderId);
			//拼接参数
			List<WarehouseOrderDetailPO> onlinePaperboardPOList = orderServer.joinOrderParamInfo(paperResultData.getContent());
			
			//key订单号
			Map<String,WarehouseOrderDetailPO> orderStockInfoPOMap = new HashMap<>(16);
			if(!ObjectUtils.isEmpty(orderStockList)){
				for (WarehouseOrderDetailPO orderStockInfoPO : orderStockList) {
					orderStockInfoPOMap.put(orderStockInfoPO.getOrderId(), orderStockInfoPO);
				}
				for (WarehouseOrderDetailPO onlinePaperboardPO : onlinePaperboardPOList) {
					WarehouseOrderDetailPO orderStockInfoPO = orderStockInfoPOMap.get(onlinePaperboardPO.getChildOrderId());
					if(orderStockInfoPO!=null){
						BeanUtils.copyProperties(orderStockInfoPO, onlinePaperboardPO);
					}
				}
			}
			BaseVO baseVO = new BaseVO();
			baseVO.setResult(onlinePaperboardPOList);
			baseVO.setTotal(paperResultData.getTotalSize());
			return MsgTemplate.successMsg(baseVO);
		}else{
			return MsgTemplate.customMsg(onlinePaperResult);
		}
	}
	
	
	@Override
	public Map<String, Object> getOnlinePaperboardByOrderId(BatchOrderIdListBO param) {
		HttpResult orderDeatilByIdList = orderServer.getOrderDeatilByIdList(param);
		if(!ObjectUtils.isEmpty(orderDeatilByIdList.getData())){
			BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
		    List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
		    List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
		    
		    SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, selectAreaByOrderId);
			List list = new ArrayList<OrderIdBO>();
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(param.getOrderIds().get(0));
			list.add(orderIdBO);
			selectAreaByOrderId.setOrderIds(list);
			List<WarehouseOrderDetailPO> orderStockInfo = orderServer.getOrderStockInfo(selectAreaByOrderId);
			if(!ObjectUtils.isEmpty(orderStockInfo)){
				WarehouseOrderDetailPO warehouseOrderDetailPO = orderStockInfo.get(0);
				BeanUtils.copyProperties(joinOrderParamInfo.get(0), warehouseOrderDetailPO,"amountSaved","remark","instockAmount","warehouseAreaInfo");
				return MsgTemplate.successMsg(warehouseOrderDetailPO);
			}else{
				joinOrderParamInfo.get(0).setAmountSaved(0);
				return MsgTemplate.successMsg(joinOrderParamInfo.get(0));
			}
		}else{
			return null;
		}
	}
}
