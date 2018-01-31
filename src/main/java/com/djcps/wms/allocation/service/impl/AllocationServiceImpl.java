package com.djcps.wms.allocation.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAddOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAllocationBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.CarInfo;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.GetAllocationManageListPO;
import com.djcps.wms.allocation.model.IntelligentAllocationPO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.model.GetDeliveryByWaybillIdsBO;
import com.djcps.wms.allocation.model.GetExcellentLodingBO;
import com.djcps.wms.allocation.model.GetIntelligentAllocaBO;
import com.djcps.wms.allocation.model.GetOrderIdByOrderType;
import com.djcps.wms.allocation.model.GetRedundantByAttributeBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.allocation.service.AllocationService;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 混合配货业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class AllocationServiceImpl implements AllocationService {
	
	private static final Logger logger = LoggerFactory.getLogger(AllocationServiceImpl.class);	
	
	private Gson gson = new Gson();
	
	private JsonParser jsonParser = new JsonParser();

	@Autowired
	private AllocationServer allocationServer;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderServer orderServer;
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Map<String, Object> getOrderType(BaseBO baseBO){
		HttpResult result = allocationServer.getOrderType(baseBO);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getChooseAllocation(PartnerInfoBO partnern) {
		HttpResult result = allocationServer.getChooseAllocation(partnern);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> saveAllocation(AddAllocationBO allocation) {
		HttpResult result = allocationServer.saveAllocation(allocation);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllocationResultList(GetRedundantByAttributeBO param) {
		int total = 0;
		//返回给前端的数据对象
		List<WarehouseOrderDetailPO> stockInfo = null;
		//String是订单号,map用来存在库信息查询出来的数据
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>();
		//订单类型
		List<String> orderTypeList = new ArrayList<String>();
		//订单号集合
		List<String> redundantOrderList = null;
		//提货单号集合
		List<String> deliveryIdList = new ArrayList<String>();
		//订单号为空,直接查询在库信息
		String flag = param.getFlag();
		// 查询标记,flag为0,则表示没有查询条件,为1表中有查询条件
		if("0".equals(flag)){
			//先查询混合配货界面的数据,这里是根据仓库划分的
			BaseUpdateAndDeleteBO base = new BaseUpdateAndDeleteBO();
			BeanUtils.copyProperties(param, base);
			HttpResult orderTypesResult = allocationServer.getAllocationOrderTypes(base);
			String str = (String) orderTypesResult.getData();
			if(str.indexOf(",")!=-1){
				String[] split = str.split(",");
				for (String string : split) {
					if(AppConstant.PLATFORM_PAPERBOARD_ORDER.equals(string) 
							|| AppConstant.OFFLINE_PAPERBOARD_ORDER.equals(string)){
						orderTypeList.add(AppConstant.PAPERBOARD_WAREHOUSE);
					}else if(AppConstant.CARTON_ORDER.equals(string)){
						orderTypeList.add(AppConstant.CARTON_WAREHOUSE);
					}
				}
			}else{
				if(AppConstant.PLATFORM_PAPERBOARD_ORDER.equals(str) 
						|| AppConstant.OFFLINE_PAPERBOARD_ORDER.equals(str)){
					orderTypeList.add(AppConstant.PAPERBOARD_WAREHOUSE);
				}else if(AppConstant.CARTON_ORDER.equals(str)){
					orderTypeList.add(AppConstant.CARTON_WAREHOUSE);
				}
			}
			GetOrderIdByOrderType OrderIdByOrderType = new GetOrderIdByOrderType();
			BeanUtils.copyProperties(param, OrderIdByOrderType);
			OrderIdByOrderType.setOrderTypeList(orderTypeList);
			//该方法是根据订单类型,查询出对应的仓库,然后根据仓库查询出在库的所有订单编码返回类型为[1,2,3,4],这里需要做分页
			OtherHttpResult ordersResult = allocationServer.getOrderIdByOrderType(OrderIdByOrderType);
			//判断查询出来的订单号是否为空,为空直接返回null
			Object data = ordersResult.getData();
			if(!ObjectUtils.isEmpty(data)){
				total = ordersResult.getTotal();
				//组织订单号
				redundantOrderList = gson.fromJson(gson.toJson(data),ArrayList.class);
				//根据订单查询在库信息组织对象
				SelectAreaByOrderIdBO selectArea = new SelectAreaByOrderIdBO();
				BeanUtils.copyProperties(param, selectArea);
				List<OrderIdBO> list = new ArrayList<OrderIdBO>();
				JsonArray ordersJsonArray = jsonParser.parse(gson.toJson(data)).getAsJsonArray();
				for (JsonElement jsonElement : ordersJsonArray) {
					OrderIdBO orderId = new OrderIdBO();
					orderId.setOrderId(jsonElement.getAsString());
					list.add(orderId);
				}
				selectArea.setOrderIds(list);
				//组织参数获取在库信息
				stockInfo = orderService.getStockInfo(selectArea);
				//创建订单批量查询需要的list和order
				List<String> childOrderIds = new ArrayList<String>();
				OrderIdsBO orderIds = new OrderIdsBO();
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					String orderId = warehouseOrderDetailPO.getOrderId();
					childOrderIds.add(orderId);
					//将在库信息存入根据订单号存入到map当中
					map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
				}
				orderIds.setChildOrderIds(childOrderIds);
				//根据订单号批量查询订单详情信息
				HttpResult orderIdsResult = orderServer.getOrderByOrderIds(orderIds);
				JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderIdsResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : orderIdsJsonArray) {
					String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
					//订单筛选,去除订单中双写的订单,取值为0的数据
					if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
						WarehouseOrderDetailPO orderDetail = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
						WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getFchildorderid());
						if(!ObjectUtils.isEmpty(warehouseDetail)){
							//将订单详情信息和在库信息数据进行拼接
							orderService.getOrderDetail(warehouseDetail,orderDetail);
						}
					}
				}
			}
		}else{
			// 查询标记为1表中有查询条件
			//redundantResult字段返回的为订单号,格式为[1,2,3,4],这里需要分页
			HttpResult redundantResult = allocationServer.getRedundantAttribute(param);
			if(!ObjectUtils.isEmpty(redundantResult.getData())){
				redundantOrderList = gson.fromJson(gson.toJson(redundantResult.getData()),ArrayList.class);
				OrderIdsBO orderIds = new OrderIdsBO();
				orderIds.setChildOrderIds(redundantOrderList);
				//根据订单号批量查询订单详情信息
				HttpResult orderIdsResult = orderServer.getOrderByOrderIds(orderIds);
				JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderIdsResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : orderIdsJsonArray) {
					String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
					//订单筛选,去除订单中双写的订单,取值为0的数据
					if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
						WarehouseOrderDetailPO orderDetail = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
						//根据订单查询在库信息组织对象
						SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
						BeanUtils.copyProperties(param, selectAreaByOrderId);
						List<OrderIdBO> list = new ArrayList<OrderIdBO>();
						for (String order : redundantOrderList) {
							OrderIdBO orderId = new OrderIdBO();
							orderId.setOrderId(order);
							list.add(orderId);
						}
						selectAreaByOrderId.setOrderIds(list);
						//组织参数获取在库信息
						stockInfo = orderService.getStockInfo(selectAreaByOrderId);
						for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
							map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
						}
						WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getFchildorderid());
						if(!ObjectUtils.isEmpty(warehouseDetail)){
							//将订单详情信息和在库信息数据进行拼接
							orderService.getOrderDetail(warehouseDetail,orderDetail);
						}
					}
				}
			}
		}
		if(!ObjectUtils.isEmpty(redundantOrderList)){
			//根据订单号获取提货单信息
			HttpResult deliveryResult = allocationServer.getDeliveryByOrderIds(redundantOrderList);
			if(!ObjectUtils.isEmpty(deliveryResult.getData())){
				JsonArray deliveryArray = jsonParser.parse(gson.toJson(deliveryResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : deliveryArray) {
					JsonObject deliveryJsonObject = jsonElement.getAsJsonObject();
					String deliveryIdEffect = deliveryJsonObject.get("deliveryIdEffect").getAsString();
					String orderId = deliveryJsonObject.get("orderId").getAsString();
					//判断提货单确认有效
					if(AppConstant.DELIVERY_EFFEFT.equals(deliveryIdEffect)){
						WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderId);
						if(warehouseOrderDetailPO!=null){
							//提货单赋值
							String deliveryId = deliveryJsonObject.get("deliveryId").getAsString();
							warehouseOrderDetailPO.setDeliveryId(deliveryId);
							deliveryIdList.add(deliveryId);
						}
					}
				}
			}
		}
		if(!ObjectUtils.isEmpty(deliveryIdList)){
			//根据提货单号获取运单信息
			HttpResult waybillResult = allocationServer.getWaybillByDeliveryIds(deliveryIdList);
			if(!ObjectUtils.isEmpty(waybillResult.getData())){
				JsonArray waybillJsonArray = jsonParser.parse(gson.toJson(waybillResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : waybillJsonArray) {
					JsonObject waybillObject = jsonElement.getAsJsonObject();
					String orderId = waybillObject.get("orderId").getAsString();
					String waybillId = waybillObject.get("waybillId").getAsString();
					String plateNumber = waybillObject.get("plateNumber").getAsString();
					WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderId);
					if(warehouseOrderDetailPO!=null){
						warehouseOrderDetailPO.setWaybillId(waybillId);
						warehouseOrderDetailPO.setPlateNumber(plateNumber);
					}
				}
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
        result.put("success",true);
        result.put("code",100000);
        result.put("msg", "");
        result.put("data", stockInfo);
        result.put("total", total);
		return result;
	}

	@Override
	public Map<String, Object> getIntelligentAllocaList(GetIntelligentAllocaBO param) {
		IntelligentAllocationPO allocation = new IntelligentAllocationPO();
		String deliveryId = null;
		String waybillId = null;
//		String remind = param.getRemind();
		//参与智能配货的条件:订单提醒:异+不补,异+备;订单状态必须为已入库状态
		//状态和提醒校验必须都通过,这里暂时先只做订单状态校验
		HttpResult result = allocationServer.getOrderByAllocationId(param);
		if(!ObjectUtils.isEmpty(result.getData())){
			HttpResult number = allocationServer.getNumber(2);
			if(!ObjectUtils.isEmpty(number.getData())){
				JsonArray asJsonArray = jsonParser.parse(gson.toJson(number.getData())).getAsJsonObject().get("numbers").getAsJsonArray();
				deliveryId = new StringBuffer().append(AppConstant.DELIVERYID_PREFIX).append(asJsonArray.get(0).getAsString()).toString();
				waybillId = new StringBuffer().append(AppConstant.WAYBILLID_PREFIX).append(asJsonArray.get(1).getAsString()).toString();
			}
			allocation.setDate(result.getData());
			allocation.setDeliveryId(deliveryId);
			allocation.setWaybillId(waybillId);
			allocation.setCarInfo(new CarInfo());
			return MsgTemplate.successMsg(allocation);
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> verifyAllocation(VerifyAllocationBO param) {
		//配货之前先确认配货结果是否是已配货，是的话直接全部驳回
		List<String> orderIds = param.getOrderIds();
		OrderIdsBO order = new OrderIdsBO();
		order.setChildOrderIds(orderIds);
		HttpResult orderResult = orderServer.getOrderByOrderIds(order);
		if(!ObjectUtils.isEmpty(orderResult.getData())){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(orderResult.getData())).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
					//已配货直接驳回
					if(!AppConstant.ALL_ADD_STOCK.equals(fromJson.getFstatus())){
						return MsgTemplate.failureMsg(SysMsgEnum.ORDER_ERROR_ALREADY_ALLOCATION);
					}
				}
			}
		}else{
			return MsgTemplate.failureMsg(SysMsgEnum.ORDER_IS_NULL);
		}
		//TODO 将所有的提货单id，和提货单号，以及指定的车辆信息id传递给TMS服务,返回成功后才能继续
		//修改订单的订单状态，修改成已配货
		OrderIdBO orderId = new OrderIdBO();
		for(int i=0;i<orderIds.size();i++){
			orderId.setStatus(AppConstant.ORDER_ALREADY_ALLOCATION);
			orderId.setChildOrderId(orderIds.get(i));
			//判断修改成功才能继续往下走
			HttpResult updateOrderResult = orderServer.updateOrderStatus(orderId);
		}
		//TODO 修改提货员和装车员的状态
		//修改配货表中的标志，修改为确认配货,且插入提货单数据(插入提货单确认状态feffect为1)
		//并通过智能配货id,修改配货订单表中的提货单id(该id原先是为null的)
		String time  = sd.format(new Date());
		param.setAllocationIdEffect(AppConstant.ALLOCATION_EFFECT);
		param.setAllocationIdEffectTime(time);
		param.setWaybillIdCreateTime(time);
		param.setDeliveryIdEffect(AppConstant.DELIVERY_EFFEFT);
		HttpResult result = allocationServer.verifyAllocation(param);
		return MsgTemplate.customMsg(result);
		//最后通知提货员装车员
	}

	@Override
	public Map<String, Object> moveOrder(String[] orderIds) {
		HttpResult result = allocationServer.moveOrder(orderIds);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAddOrderList(GetRedundantByAttributeBO param) {
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>();
		List<WarehouseOrderDetailPO> orderDetailList = new ArrayList<WarehouseOrderDetailPO>();
		//订单状态:已入库
		param.setOrderStatus(AppConstant.ALL_ADD_STOCK);
		OtherHttpResult result = allocationServer.getAddOrderList(param);
		if(!ObjectUtils.isEmpty(result.getData())){
			List<String> redundantOrderList = gson.fromJson(gson.toJson(result.getData()),ArrayList.class);
			OrderIdsBO orderIds = new OrderIdsBO();
			orderIds.setChildOrderIds(redundantOrderList);
			//根据订单号批量查询订单详情信息
			HttpResult orderIdsResult = orderServer.getOrderByOrderIds(orderIds);
			JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderIdsResult.getData())).getAsJsonArray();
			for (JsonElement jsonElement : orderIdsJsonArray) {
				String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO orderDetail = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
					map.put(orderDetail.getFchildorderid(), orderDetail);
					orderDetailList.add(orderDetail);
				}
			}
			
			//根据订单查询在库信息组织对象
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, selectAreaByOrderId);
			List<OrderIdBO> list = new ArrayList<OrderIdBO>();
			for (String order : redundantOrderList) {
				OrderIdBO orderId = new OrderIdBO();
				orderId.setOrderId(order);
				list.add(orderId);
			}
			selectAreaByOrderId.setOrderIds(list);
			List<WarehouseOrderDetailPO> stockInfo = orderService.getStockInfo(selectAreaByOrderId);
			if(!ObjectUtils.isEmpty(stockInfo)){
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					String orderId = warehouseOrderDetailPO.getOrderId();
					WarehouseOrderDetailPO orderDetail = map.get(orderId);
					orderDetail.setAreaList(warehouseOrderDetailPO.getAreaList());
					orderDetail.setWarehouseId(warehouseOrderDetailPO.getWarehouseId());
					orderDetail.setWarehouseName(warehouseOrderDetailPO.getWarehouseName());
				}
			}
			Map<String, Object> mapResult = new HashMap<String, Object>();
			mapResult.put("success",true);
			mapResult.put("code",100000);
			mapResult.put("msg", "");
			mapResult.put("data", orderDetailList);
			mapResult.put("total", result.getTotal());
			return mapResult;
		}else{
			return MsgTemplate.successMsg();
		}
		
	}

	@Override
	public Map<String, Object> verifyAddOrder(List<AddAllocationOrderBO> param) {
		//TODO 智能配货判断车辆装载率
		HttpResult result = allocationServer.verifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllocationManageList(GetRedundantByAttributeBO param) {
		List<GetAllocationManageListPO> allocationManageList = null;
		List<String> waybillList = new ArrayList<String>();
		//key为运单号
		Map<String,GetAllocationManageListPO> map = new HashMap<String,GetAllocationManageListPO>();
		int total = 0;
		// 查询标记,flag为0,则表示没有查询条件,为1表中有查询条件
		if("0".equals(param.getFlag())){
			//分页查询运单表
			OtherHttpResult result = allocationServer.getAlloManageQuery(param);
//			Object data = result.getData();
//			total = jsonParser.parse(gson.toJson(data)).getAsJsonObject().get("total").getAsInt();
//			if(!ObjectUtils.isEmpty(data)){
//				allocationManageList = gson.fromJson(gson.toJson(data), new TypeToken<ArrayList<GetAllocationManageListPO>>(){}.getType());
//				for (GetAllocationManageListPO getAllocationManageListPO : allocationManageList) {
//					String waybillId = getAllocationManageListPO.getWaybillId();
//					waybillList.add(waybillId);
//					map.put(waybillId, getAllocationManageListPO);
//				}
//				GetDeliveryByWaybillIdsBO waybills = new GetDeliveryByWaybillIdsBO();
//				BeanUtils.copyProperties(param, waybills);
//				waybills.setWaybillIds(waybillList);
//				//根据运单号批量查询获取提货单号
//				HttpResult deliveryResult = allocationServer.getDeliveryByWaybillIds(waybills);
//				Object deliveryData = deliveryResult.getData();
//				if(!ObjectUtils.isEmpty(deliveryData)){
//					JsonArray deliveryJsonArray = jsonParser.parse(gson.toJson(deliveryData)).getAsJsonArray();
//					for (JsonElement jsonElement : deliveryJsonArray) {
//						GetAllocationManageListPO fromJson = gson.fromJson(jsonElement, GetAllocationManageListPO.class);
//						GetAllocationManageListPO getMapAllocation = map.get(fromJson.getWaybillId());
//						if(getMapAllocation!=null){
//							getMapAllocation.setDeliveryCreateTime(fromJson.getDeliveryCreateTime());
//							getMapAllocation.setLoadingtableName(fromJson.getLoadingtableName());
//						}
//					}
//				}
//			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",true);
			resultMap.put("code",100000);
			resultMap.put("msg", "");
			resultMap.put("data", result.getData());
			resultMap.put("total", result.getTotal());
			return resultMap;
		}else{
			//查询标记为1表中有查询条件
			OtherHttpResult result = allocationServer.getAlloManageFuzzyQuery(param);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",true);
			resultMap.put("code",100000);
			resultMap.put("msg", "");
			resultMap.put("data", result.getData());
			resultMap.put("total", result.getTotal());
			return resultMap;
		}
	}

	@Override
	public Map<String, Object> getWaybillDetailByWayId(GetDeliveryByWaybillIdsBO param) {
		Map<String,WarehouseOrderDetailPO> map = new HashMap<>();
		//根据运单号获取提货单号
		HttpResult result = allocationServer.getDeliveryByWaybillIds(param);
		List<String> deliveryIdsList = new ArrayList<String>();
		List<String> orderIdsList = new ArrayList<String>();
		List<WarehouseOrderDetailPO> warehouseOrderDetailList = new ArrayList<WarehouseOrderDetailPO>();
		//解析获取提货单号
		if(!ObjectUtils.isEmpty(result.getData())){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(result.getData())).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				String deliveryId = jsonElement.getAsJsonObject().get("deliveryId").getAsString();
				deliveryIdsList.add(deliveryId);
			}
			//根据提货单号获取订单信息
			HttpResult orderResult = allocationServer.getOrderByDeliveryId(deliveryIdsList);
			if(!ObjectUtils.isEmpty(orderResult.getData())){
				JsonArray asJsonArray2 = jsonParser.parse(gson.toJson(orderResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : asJsonArray2) {
					String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
					orderIdsList.add(orderId);
					WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
					map.put(orderId, fromJson);
				}
			}
			OrderIdsBO orderIds = new OrderIdsBO();
			orderIds.setChildOrderIds(orderIdsList);
			//根据订单号批量查询订单详情信息
			HttpResult orderIdsResult = orderServer.getOrderByOrderIds(orderIds);
			JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderIdsResult.getData())).getAsJsonArray();
			for (JsonElement jsonElement : orderIdsJsonArray) {
				String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO orderDetail = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
					WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderDetail.getFchildorderid());
					orderDetail.setDeliveryId(warehouseOrderDetailPO.getDeliveryId());
					orderDetail.setWarehouseId(warehouseOrderDetailPO.getWarehouseId());
					orderDetail.setWarehouseName(warehouseOrderDetailPO.getWarehouseLocName());
					orderDetail.setWarehouseAreaId(warehouseOrderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseAreaName(warehouseOrderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseLocId(warehouseOrderDetailPO.getWarehouseLocId());
					orderDetail.setWarehouseLocName(warehouseOrderDetailPO.getWarehouseLocName());
					orderDetail.setAllocationId(warehouseOrderDetailPO.getAllocationId());
					orderDetail.setSequence(warehouseOrderDetailPO.getSequence());
					warehouseOrderDetailList.add(orderDetail);
				}
			}
		}
		if(!ObjectUtils.isEmpty(warehouseOrderDetailList)){
			return MsgTemplate.successMsg(warehouseOrderDetailList);
		}else{
			return MsgTemplate.successMsg();
		}
		
	}

	@Override
	public Map<String, Object> getExcellentLoding(GetExcellentLodingBO param) {
		HttpResult result = allocationServer.getExcellentLoding(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> againVerifyAddOrder(AgainVerifyAddOrderBO param) {
		//TODO
		//计算装载率,判断装载率是否超出,超出直接驳回
		//不超出,将订单数据存入订单表中,并重新生成提货单.提货员和装车员和原来的一致,装车顺序在原来的基础上递增
		param.setDeliveryIdEffect(AppConstant.DELIVERY_UNEFFEFT);
		HttpResult result = allocationServer.againVerifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> againVerifyAllocation(List<AgainVerifyAllocationBO> param) {
		for (AgainVerifyAllocationBO againVerifyAllocationBO : param) {
			againVerifyAllocationBO.setDeliveryIdEffect(AppConstant.DELIVERY_EFFEFT);
		}
		HttpResult result = allocationServer.againVerifyAllocation(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllUserCarInfo() {
		CarInfo car1 = new CarInfo();
		CarInfo car2 = new CarInfo("周德星");
		CarInfo car3 = new CarInfo("郑天伟");
		CarInfo car4 = new CarInfo("郑杰");
		List list = new ArrayList<>();
		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
//		HttpResult result = allocationServer.getAllUserCarInfo();
		return MsgTemplate.successMsg(list);
	}

	@Override
	public Map<String, Object> changeCarInfo(ChangeCarInfoBO param) {
		// TODO 通知tms运单服务,传递运单号和车辆id
		//修改配货结果中的车辆id，修改提货单中的车牌号
		HttpResult result = allocationServer.changeCarInfo(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getCarDetailById() {
		CarInfo car1 = new CarInfo();
		return MsgTemplate.successMsg(car1);
	}

	@Override
	public Map<String, Object> cancelAllocation(CancelAllocationBO param) {
		// TODO
		//传递所有订单号，修改成已入库,通知wms取消车辆
		//修改订单的订单状态，修改成已入库
		OrderIdBO orderId = new OrderIdBO();
		List<String> orderIds = param.getOrderIds();
//		for (String string : orderIds) {
//			orderId.setStatus(AppConstant.ALL_ADD_STOCK);
//			orderId.setChildOrderId(string);
//			//判断修改成功才能继续往下走
//			HttpResult updateOrderResult = orderServer.updateOrderStatus(orderId);
//		}
		//修改提货单确认状态修改为feffect为2
		param.setDeliveryIdEffect(AppConstant.DELIVERY_UNEFFEFT);
		//修改订单表中订单状态为已入库
		param.setOrderStatus(AppConstant.ALL_ADD_STOCK);
		//修改配货表的确认状态为2
		param.setAllocationIdEffect(AppConstant.ALLOCATION_UNEFFECT);
		HttpResult result = allocationServer.cancelAllocation(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addzhinengpeihuo() {
		List<WarehouseOrderDetailPO> list = new ArrayList<>();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<>();
		HttpResult result = allocationServer.addzhinengpeihuo();
		Object data = result.getData();
		JsonArray asJsonArray = jsonParser.parse(gson.toJson(data)).getAsJsonArray();
		List<String> orderIdsList =new  ArrayList<>();
		for (JsonElement jsonElement : asJsonArray) {
			String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
			WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
			map.put(fromJson.getOrderId(), fromJson);
			orderIdsList.add(orderId);
		}
		OrderIdsBO param = new OrderIdsBO();
		param.setChildOrderIds(orderIdsList);
		HttpResult orderByOrderIds = orderServer.getOrderByOrderIds(param);
		JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderByOrderIds.getData())).getAsJsonArray();
		String uuid = UUID.randomUUID().toString();
		if(!ObjectUtils.isEmpty(orderByOrderIds.getData())){
			for(int i=0;i<orderIdsJsonArray.size();i++){
				String fdblflag = orderIdsJsonArray.get(i).getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO orderDetail = gson.fromJson(orderIdsJsonArray.get(i), WarehouseOrderDetailPO.class);
					WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderDetail.getFchildorderid());
					//组织参数
					orderDetail.setWarehouseId(warehouseOrderDetailPO.getWarehouseId());
					orderDetail.setWarehouseName(warehouseOrderDetailPO.getWarehouseLocName());
					orderDetail.setWarehouseAreaId(warehouseOrderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseAreaName(warehouseOrderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseLocId(warehouseOrderDetailPO.getWarehouseLocId());
					orderDetail.setWarehouseLocName(warehouseOrderDetailPO.getWarehouseLocName());
					orderDetail.setAllocationId(uuid);
					orderDetail.setSequence(String.valueOf(i));
					String add = orderDetail.getFcodeprovince()+orderDetail.getFaddressdetail();
					orderDetail.setFaddressdetail(add);
					list.add(orderDetail);
				}
			}
			HttpResult otherResult = allocationServer.batchAddAllocationOrder(list);
		}else{
			uuid = null;
		}
		return MsgTemplate.successMsg(uuid);
	}
}
