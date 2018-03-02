package com.djcps.wms.allocation.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.constant.RedisPrefixContant;
import com.djcps.wms.commons.enums.SysMsgEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.djcps.wms.allocation.constant.AllocationConstant;
import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.AddExcellentAllocationBO;
import com.djcps.wms.allocation.model.AgainVerifyAddOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAllocationBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.CarInfo;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.DeliveryOrderPO;
import com.djcps.wms.allocation.model.GetAllocationManageListPO;
import com.djcps.wms.allocation.model.IntelligentAllocationPO;
import com.djcps.wms.allocation.model.LoadingPersonPO;
import com.djcps.wms.allocation.model.MergeModelBO;
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.OrderPO;
import com.djcps.wms.allocation.model.PickerPO;
import com.djcps.wms.allocation.model.SequenceBO;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.model.WarehousePO;
import com.djcps.wms.allocation.model.WaybillDeliveryOrderPO;
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
import com.djcps.wms.commons.redis.RedisClient;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import com.djcps.wms.order.model.WarehouseLocationBO;
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
	@Autowired
	@Qualifier("redisClientCluster")
	RedisClient redisClient;
	
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
		if(AllocationConstant.UNHAVE_QUERY_CONDITION.equals(flag)){
			//先查询混合配货界面的数据,这里是根据仓库划分的
			BaseUpdateAndDeleteBO base = new BaseUpdateAndDeleteBO();
			BeanUtils.copyProperties(param, base);
			HttpResult orderTypesResult = allocationServer.getAllocationOrderTypes(base);
			String str = (String) orderTypesResult.getData();
			if(str.indexOf(AllocationConstant.COMMA_SEPARATOR)!=-1){
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
			GetOrderIdByOrderType orderIdByOrderType = new GetOrderIdByOrderType();
			BeanUtils.copyProperties(param, orderIdByOrderType);
			orderIdByOrderType.setOrderTypeList(orderTypeList);
			//该方法是根据订单类型,查询出对应的仓库,然后根据仓库查询出在库的所有订单编码返回类型为[1,2,3,4],这里需要做分页
			OtherHttpResult ordersResult = allocationServer.getOrderIdByOrderType(orderIdByOrderType);
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
			OtherHttpResult redundantResult = allocationServer.getRedundantAttribute(param);
			total = redundantResult.getTotal();
			if(!ObjectUtils.isEmpty(redundantResult.getData())){
				redundantOrderList = gson.fromJson(gson.toJson(redundantResult.getData()),ArrayList.class);
				List<OrderIdBO> list = new ArrayList<OrderIdBO>();
				for (String order : redundantOrderList) {
					OrderIdBO orderId = new OrderIdBO();
					orderId.setOrderId(order);
					list.add(orderId);
				}
				//根据订单查询在库信息组织对象
				SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
				BeanUtils.copyProperties(param, selectAreaByOrderId);
				selectAreaByOrderId.setOrderIds(list);
				//组织参数获取在库信息
				stockInfo = orderService.getStockInfo(selectAreaByOrderId);
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
				}
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
					if(AllocationConstant.DELIVERY_EFFEFT.equals(deliveryIdEffect)){
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
		String allocationId = param.getAllocationId();
		IntelligentAllocationPO allocation = new IntelligentAllocationPO();
		//参与智能配货的条件:订单提醒:异+不补,异+备;订单状态必须为已入库状态
		//状态和提醒校验必须都通过,这里暂时先只做订单状态校验
		OtherHttpResult result = allocationServer.getOrderByAllocationId(param);
		List<OrderPO> orderPoList = gson.fromJson(gson.toJson(result.getData()), new TypeToken<ArrayList<OrderPO>>(){}.getType());
		
		Map<String,OrderPO> map = new HashMap<>();
		if(!ObjectUtils.isEmpty(orderPoList)){
			//将智能配货结果存入到缓存当中
			if(!ObjectUtils.isEmpty(orderPoList)){
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId, gson.toJson(orderPoList));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId, 86400);
			}
			allocation.setDate(orderPoList);
			allocation.setCarInfo(new CarInfo());
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("success",true);
			resultMap.put("code",100000);
			resultMap.put("msg", "");
			resultMap.put("data", allocation);
			resultMap.put("total", result.getTotal());
			return resultMap;
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> verifyAllocation(VerifyAllocationBO param) {
		
		
		//确认配货和确认优化操作,需要上锁,以合作方id就锁进行上锁
		if(1==redisClient.setnx(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(),"上锁")){
			redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 86400);
			//防止同时进行确认配货
			List<String> orderIds = new ArrayList<>();
			List<SequenceBO> sequenceList = param.getOrderIds();
			//取出所有的订单号
			for (SequenceBO sequenceBO : sequenceList) {
				BeanUtils.copyProperties(param, sequenceBO);
				orderIds.add(sequenceBO.getOrderId());
			}
			
			//配货之前先确认配货结果是否是已配货，此处订单状态必须为22已入库状态,否的话直接全部驳回
			HttpResult orderResult = allocationServer.getOrderByOrderIds(orderIds);
			List<OrderPO> orderPOList = gson.fromJson(gson.toJson(orderResult.getData()),new TypeToken<ArrayList<OrderPO>>(){}.getType());
			for (OrderPO orderPO : orderPOList) {
				Integer orderStatus = orderPO.getOrderStatus();
				if(!AllocationConstant.ALL_ADD_STOCK.equals(String.valueOf(orderStatus))){
					String error = orderPO.getOrderId();
					return MsgTemplate.failureMsg(SysMsgEnum.ORDER_STATUS_ERROR,error);
				}
			}
			
//				OrderIdsBO order = new OrderIdsBO();
//				order.setChildOrderIds(orderIds);
//				HttpResult orderResult = orderServer.getOrderByOrderIds(order);
//				if(!ObjectUtils.isEmpty(orderResult.getData())){
//					JsonArray asJsonArray = jsonParser.parse(gson.toJson(orderResult.getData())).getAsJsonArray();
//					for (JsonElement jsonElement : asJsonArray) {
//						String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
//						//订单筛选,去除订单中双写的订单,取值为0的数据
//						if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
//							WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
//							//已配货直接驳回
//							if(!AllocationConstant.ALL_ADD_STOCK.equals(String.valueOf(fromJson.getFstatus()))){
//								return MsgTemplate.failureMsg(SysMsgEnum.ORDER_ERROR_ALREADY_ALLOCATION);
//							}
//						}
//					}
//				}else{
//					return MsgTemplate.failureMsg(SysMsgEnum.ORDER_IS_NULL);
//				}
			
			//TODO 将所有的提货单id，和提货单号，以及指定的车辆信息id传递给TMS服务,返回成功后才能继续
			//修改订单的订单状态，修改成已配货
			OrderIdBO orderId = new OrderIdBO();
			for(int i=0;i<orderIds.size();i++){
				orderId.setStatus(AllocationConstant.ORDER_ALREADY_ALLOCATION);
				orderId.setOrderId(orderIds.get(i));
				//判断修改成功才能继续往下走(这里需要批量修改)
				HttpResult updateOrderResult = orderServer.updateOrderStatus(orderId);
				System.out.println(updateOrderResult.getMsg());
			}
			//TODO 修改提货员和装车员的状态
			//修改配货表中的标志，修改为确认配货,且插入提货单数据(插入提货单确认状态feffect为1)
			//并通过智能配货id,修改配货订单表中的提货单id(该id原先是为null的),修改装车顺序
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time  = sd.format(new Date());
			param.setAllocationIdEffect(AllocationConstant.ALLOCATION_EFFECT);
			param.setAllocationIdEffectTime(time);
			param.setWaybillIdCreateTime(time);
			param.setDeliveryCreateTime(time);
			param.setDeliveryIdEffect(AllocationConstant.DELIVERY_EFFEFT);
			HttpResult result = allocationServer.verifyAllocation(param);
			if(result.isSuccess()){
				//冗余表中插入运单号,提货单号和车牌号,并且修改订单状态
				List<UpdateOrderRedundantBO> updateList = new ArrayList<>();
				for (String string : orderIds) {
					UpdateOrderRedundantBO update = new UpdateOrderRedundantBO();
					update.setStatus(Integer.valueOf(AllocationConstant.ORDER_ALREADY_ALLOCATION));
					update.setDeliveryId(param.getDeliveryId());
					update.setWaybillId(param.getWaybillId());
					update.setOrderId(string);
					update.setPlateNumber(param.getPlateNumber());
					update.setPartnerId(param.getPartnerId());
					updateList.add(update);
				}
				HttpResult updateOrderRedunResult = allocationServer.batchUpdateOrderRedun(updateList);
				if(updateOrderRedunResult.isSuccess()){
					redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+param.getAllocationId());
					redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+param.getAllocationId());
					redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+param.getAllocationId());
				}
				//删除确认优化的锁
				redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+param.getPartnerId());
				return MsgTemplate.customMsg(updateOrderRedunResult);
			}
			return MsgTemplate.customMsg(result);
			//最后通知提货员装车员
		}else{
			return MsgTemplate.failureMsg(SysMsgEnum.COMMON_ALLOCATION_LOADING__ERROR);
		}
	}

	@Override
	public Map<String, Object> moveOrder(MoveOrderPO param) {
		String allocationId = param.getAllocationId();
		//配货中移除订单flag为0,配货管理flag为1
		if(param.getFlag().equals(AllocationConstant.ALLOCATION_REMOVE_ORDER)){
			String string = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId);
			if(!StringUtils.isEmpty(string)){
				//保存订单号的唯一性,防止误点和网络延迟的特殊情况出现
				HashSet<String> set = new HashSet<>();
				List<String> orderIds = gson.fromJson(string, List.class);
				for (String string2 : orderIds) {
					set.add(string2);
				}
				List<String> otherOrderIds =  param.getOrderIds();
				for (String string2 : otherOrderIds) {
					set.add(string2);
				}
				orderIds = new ArrayList<>();
				Iterator<String> iterator = set.iterator();
				while(iterator.hasNext()){
					String orderId = iterator.next();
					orderIds.add(orderId);
				}
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId, gson.toJson(orderIds));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId,86400);
			}else{
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId, gson.toJson(param.getOrderIds()));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId,86400);
			}
			HttpResult result = allocationServer.allocationMoveOrder(param);
			return MsgTemplate.customMsg(result);
		}else if(param.getFlag().equals(AllocationConstant.ALLOCATION_MANAGEMENT_REMOVE_ORDER)){
			String waybillId = param.getWaybillId();
			//装车优化界面移除订单缓存
			String string = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
			if(!StringUtils.isEmpty(string)){
				//保存订单号的唯一性,防止误点和网络延迟的特殊情况出现
				HashSet<String> set = new HashSet<>();
				List<String> orderIds = gson.fromJson(string, List.class);
				for (String string2 : orderIds) {
					set.add(string2);
				}
				List<String> otherOrderIds =  param.getOrderIds();
				for (String string2 : otherOrderIds) {
					set.add(string2);
				}
				orderIds = new ArrayList<>();
				Iterator<String> iterator = set.iterator();
				while(iterator.hasNext()){
					String orderId = iterator.next();
					orderIds.add(orderId);
				}
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId, gson.toJson(orderIds));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId,86400);
			}else{
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId, gson.toJson(param.getOrderIds()));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId,86400);
			}
		}
		return MsgTemplate.successMsg(); 
	}

	@Override
	public Map<String, Object> getAddOrderList(GetRedundantByAttributeBO param) {
		String allocationId = param.getAllocationId();
		String waybillId = param.getWaybillId();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>();
		List<WarehouseOrderDetailPO> orderDetailList = new ArrayList<WarehouseOrderDetailPO>();
		//订单状态:已入库
		param.setOrderStatus(AllocationConstant.ALL_ADD_STOCK);
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
			//获取在库信息
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
			
			List<WarehouseOrderDetailPO> detailList = new ArrayList<WarehouseOrderDetailPO>();
			//判断是否要走缓存,等于1表示,装车优化追加订单界面要走缓存
			if(AllocationConstant.HAVING_CACHE.equals(param.getCache())){
				String str = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
				if(!StringUtils.isEmpty(str)){
					List<String> fromOrderIds = gson.fromJson(str, List.class);
					for (String string : fromOrderIds) {
						WarehouseOrderDetailPO orderDetail = map.get(string);
						if(orderDetail!=null){
							map.remove(string);
						}
					}
					if(map.size()>0){
						for(Map.Entry<String,WarehouseOrderDetailPO> entry : map.entrySet()){
							detailList.add(entry.getValue());
						}
					}
				}
			}else{
				String addOrder = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId);
				String removeOrder = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+allocationId);
				String allocationOrder = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId);
				
				List<AddAllocationOrderBO> cacheList = null;
				List<String> orderIdsList = null;
				if(!StringUtils.isEmpty(allocationOrder)){
					List<OrderPO> allocationOrderList = gson.fromJson(allocationOrder, new TypeToken<ArrayList<OrderPO>>(){}.getType());
					for (OrderPO orderPO : allocationOrderList) {
						String orderId = orderPO.getOrderId();
						WarehouseOrderDetailPO orderDetail = map.get(orderId);
						if(orderDetail!=null){
							map.remove(orderId);
						}
					}
					//判断假如全部移除完毕了,那么就直接返回null
					if(map.size()==0){
						Map<String, Object> mapResult = new HashMap<String, Object>();
						mapResult.put("success",true);
						mapResult.put("code",100000);
						mapResult.put("msg", "");
						mapResult.put("data", null);
						mapResult.put("total",0);
						return mapResult;
					}
				}
				
				if(!StringUtils.isEmpty(addOrder) && !StringUtils.isEmpty(removeOrder)){
					cacheList = gson.fromJson(addOrder, new TypeToken<ArrayList<AddAllocationOrderBO>>(){}.getType());
					orderIdsList = gson.fromJson(removeOrder, List.class);
					Iterator<AddAllocationOrderBO> addIterator = cacheList.iterator();
					while(addIterator.hasNext()){
						AddAllocationOrderBO next = addIterator.next();
						String orderId = next.getOrderId();
						Iterator<String> orderIdsIterator = orderIdsList.iterator();
						while(orderIdsIterator.hasNext()){
							if(orderId.equals(orderIdsIterator.next())){
								orderIdsIterator.remove();
								addIterator.remove();
							}
						}
					}
				}
				if(ObjectUtils.isEmpty(cacheList)){
					cacheList = gson.fromJson(addOrder, new TypeToken<ArrayList<AddAllocationOrderBO>>(){}.getType());
				}
				
				if(!ObjectUtils.isEmpty(cacheList)){
					for (AddAllocationOrderBO orderBO : cacheList) {
						String orderId = orderBO.getOrderId();
						WarehouseOrderDetailPO orderDetail = map.get(orderId);
						if(orderDetail!=null){
							map.remove(orderId);
						}
					}
				}
				if(map.size()>0){
					for(Map.Entry<String,WarehouseOrderDetailPO> entry : map.entrySet()){
						detailList.add(entry.getValue());
					}
				}
			}
			
			//不走缓存,智能配货结果获取追加订单
			Map<String, Object> mapResult = new HashMap<String, Object>();
			mapResult.put("success",true);
			mapResult.put("code",100000);
			mapResult.put("msg", "");
			if(!ObjectUtils.isEmpty(detailList)){
				mapResult.put("data", detailList);
				mapResult.put("total", detailList.size());
			}else if(!ObjectUtils.isEmpty(orderDetailList)){
				mapResult.put("data", orderDetailList);
				mapResult.put("total", orderDetailList.size());
			}else{
				mapResult.put("data", null);
				mapResult.put("total",0);
			}
			return mapResult;
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> verifyAddOrder(List<AddAllocationOrderBO> param) {
		//TODO 智能配货判断车辆装载率
		String allocationId = param.get(0).getAllocationId();
		//缓存详细订单信息
		String cacheOrder = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId);
		if(!StringUtils.isEmpty(cacheOrder)){
			Map<String,AddAllocationOrderBO> map = new HashMap<>();
			List<AddAllocationOrderBO> cacheList = gson.fromJson(cacheOrder, new TypeToken<ArrayList<AddAllocationOrderBO>>(){}.getType());
			//保证追加订单的唯一性
			for (AddAllocationOrderBO addAllocationOrderBO : cacheList) {
				map.put(addAllocationOrderBO.getOrderId(), addAllocationOrderBO);
			}
			for (AddAllocationOrderBO addAllocationOrderBO : param) {
				map.put(addAllocationOrderBO.getOrderId(), addAllocationOrderBO);
			}
			cacheList = new ArrayList<>();
			//遍历map
			for(Map.Entry<String,AddAllocationOrderBO> entry : map.entrySet()){
				AddAllocationOrderBO value = entry.getValue();
				cacheList.add(value);
			}
			redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId, gson.toJson(cacheList));
			redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId,86400);
		}else{
			redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId, gson.toJson(param));
			redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+allocationId,86400);
		}
		//订单表中存入订单数据
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
		if(AllocationConstant.UNHAVE_QUERY_CONDITION.equals(param.getFlag())){
			//分页查询运单表
			OtherHttpResult result = allocationServer.getAlloManageQuery(param);
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
		if(ObjectUtils.isEmpty(result.getData())){
			return MsgTemplate.successMsg();
		}
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
			}else{
				return MsgTemplate.successMsg();
			}
			OrderIdsBO orderIds = new OrderIdsBO();
			orderIds.setChildOrderIds(orderIdsList);
			
			//根据订单号批量查询订单详情信息
			HttpResult orderIdsResult = orderServer.getOrderByOrderIds(orderIds);
			if(ObjectUtils.isEmpty(orderIdsResult.getData())){
				return MsgTemplate.successMsg();
			}
			JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(orderIdsResult.getData())).getAsJsonArray();
			for (JsonElement jsonElement : orderIdsJsonArray) {
				String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO orderDetail = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
					WarehouseOrderDetailPO orderDetailPO = map.get(orderDetail.getFchildorderid());
					orderDetail.setDeliveryId(orderDetailPO.getDeliveryId());
					orderDetail.setWarehouseId(orderDetailPO.getWarehouseId());
					orderDetail.setWarehouseName(orderDetailPO.getWarehouseLocName());
					orderDetail.setWarehouseAreaId(orderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseAreaName(orderDetailPO.getWarehouseAreaName());
					orderDetail.setWarehouseLocId(orderDetailPO.getWarehouseLocId());
					orderDetail.setWarehouseLocName(orderDetailPO.getWarehouseLocName());
					orderDetail.setAllocationId(orderDetailPO.getAllocationId());
					orderDetail.setDeliveryAmount(orderDetailPO.getOrderAmount());
					orderDetail.setSequence(orderDetailPO.getSequence());
					warehouseOrderDetailList.add(orderDetail);
				}
			}
		}
		
		//对数据进行排序,选择排序法
		int size = warehouseOrderDetailList.size();
		int minIndex ;
		WarehouseOrderDetailPO temp =null;
	    for (int i = 0; i < size - 1; i++) {
	        minIndex = i;
	        for (int j = i + 1; j < size; j++) {
	            if (warehouseOrderDetailList.get(j).getSequence() < warehouseOrderDetailList.get(minIndex).getSequence()) {     //寻找最小的数
	                minIndex = j;                 //将最小数的索引保存
	            }
	        }
	        temp = warehouseOrderDetailList.get(i);
	        warehouseOrderDetailList.set(i, warehouseOrderDetailList.get(minIndex));
	        warehouseOrderDetailList.set(minIndex,temp);
	    }
	    for (WarehouseOrderDetailPO warehouseOrderDetailPO : warehouseOrderDetailList) {
	    	 String fflutetype = warehouseOrderDetailPO.getFflutetype();
		        switch(Integer.valueOf(fflutetype)){
		        case 1:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_1);break;
		        case 2:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_2);break;
		        case 3:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_3);break;
		        case 4:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_4);break;
		        case 5:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_5);break;
		        case 6:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_6);break;
		        case 7:
		        	warehouseOrderDetailPO.setFflutetype(AllocationConstant.FLUTE_TYPE_7);break;
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
		String waybillId= param.getWaybillId();
		WaybillDeliveryOrderPO waybillDeliveryOrder = null;
		//获取移除订单的缓存
		String removeStr = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
		//获取追加订单的缓存
		String addOrderStr = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		
		List<String> removeOrder = null;
		List<String> addOrder = null;
		//判断,假如删除的订单为缓存中的追加的订单,则需要对两者进行处理
		if(!StringUtils.isEmpty(addOrderStr) && (!StringUtils.isEmpty(removeStr))){
			removeOrder = gson.fromJson(removeStr, List.class);
			addOrder = gson.fromJson(addOrderStr, List.class);
			Iterator<String> removeIterator = removeOrder.iterator();
			while(removeIterator.hasNext()){
				String removeId = removeIterator.next();
				Iterator<String> addIterator = addOrder.iterator();
				while(addIterator.hasNext()){
					String addId = addIterator.next();
					if(removeId.equals(addId)){
						addIterator.remove();
						removeIterator.remove();
					}
				}
			}
		}
		
		if(removeOrder==null){
			removeOrder = gson.fromJson(removeStr, List.class);
		}
		
		if(addOrder==null){
			addOrder = gson.fromJson(addOrderStr, List.class);
		}
		
		HttpResult result = allocationServer.getExcellentLoding(param);
		if(!ObjectUtils.isEmpty(addOrder)){
			HttpResult number = allocationServer.getNumber(1);
			if(!ObjectUtils.isEmpty(number.getData())){
				JsonArray numberJsonArray = jsonParser.parse(gson.toJson(number.getData())).getAsJsonObject().get("numbers").getAsJsonArray();
				String deliveryId = new StringBuffer().append(AllocationConstant.DELIVERYID_PREFIX).append(numberJsonArray.get(0).getAsString()).toString();
				//提货单做缓存
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId,deliveryId);
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId,86400);
				//装车优化,缓存追加订单,组织参数处理公共方法
				List<OrderPO> orderList = getOrderPOList(addOrder, param,deliveryId);
				waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
				List<DeliveryOrderPO> deliveryOrderList = waybillDeliveryOrder.getDeliveryOrder();
				DeliveryOrderPO deliveryOrder = new DeliveryOrderPO();
				BeanUtils.copyProperties(deliveryOrderList.get(0),deliveryOrder);
				deliveryOrder.setDeliveryId(deliveryId);
				deliveryOrder.setOrders(orderList);
				deliveryOrderList.add(deliveryOrder);
			}
		}
		
		if(!ObjectUtils.isEmpty(removeOrder)){
			if(waybillDeliveryOrder==null){
				waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
			}
			List<DeliveryOrderPO> deliveryOrderList = waybillDeliveryOrder.getDeliveryOrder();
			Iterator<DeliveryOrderPO> deliveryIter = deliveryOrderList.iterator();
	        //组织删除移除订单的数据
			while (deliveryIter.hasNext()) {
	        	DeliveryOrderPO deliveryOrderPO = deliveryIter.next();
	        	Iterator<OrderPO> orderIter = deliveryOrderPO.getOrders().iterator();
	        	while (orderIter.hasNext()) {
	        		String orderId = orderIter.next().getOrderId();
	        		for (String order : removeOrder) {
						if(orderId.equals(order)){
							orderIter.remove();
						}
					}
	        	}
	        }
			//判断订单是否全部删除,假如订单全部删除则提货单相关的信息也要删除
			Iterator<DeliveryOrderPO> otherDeliveryIter = deliveryOrderList.iterator();
			while (otherDeliveryIter.hasNext()) {
				DeliveryOrderPO deliveryOrderPO = otherDeliveryIter.next();
				String deliveryId = deliveryOrderPO.getDeliveryId();
				if(ObjectUtils.isEmpty(deliveryOrderPO.getOrders())){
					otherDeliveryIter.remove();
				}
	        }
		}
		//删除确认配货和确认优化公共锁
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
		if(waybillDeliveryOrder!=null){
			List<DeliveryOrderPO> deliveryList = waybillDeliveryOrder.getDeliveryOrder();
			for (DeliveryOrderPO deliveryOrderPO : deliveryList) {
				List<OrderPO> orders = deliveryOrderPO.getOrders();
				for (OrderPO orderPO : orders) {
					orderPO.setDeliveryAmount(orderPO.getOrderAmount());
				}
			}
		}else{
			waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
			List<DeliveryOrderPO> deliveryList = waybillDeliveryOrder.getDeliveryOrder();
			for (DeliveryOrderPO deliveryOrderPO : deliveryList) {
				List<OrderPO> orders = deliveryOrderPO.getOrders();
				for (OrderPO orderPO : orders) {
					orderPO.setDeliveryAmount(orderPO.getOrderAmount());
				}
			}
		}
		return MsgTemplate.successMsg(waybillDeliveryOrder);
	}
	
	/**
	 * 装车优化,缓存追加订单,组织参数处理公共方法
	 * @return
	 */
	private List<OrderPO> getOrderPOList(List<String> orderIdsList,GetExcellentLodingBO param,String deliveryId){
		//String是订单号,map用来存在库信息查询出来的数据
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>();
		List<OrderIdBO> orderIdBOList = new ArrayList<>();
		for (String string : orderIdsList) {
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(string);
			orderIdBOList.add(orderIdBO);
		}
		SelectAreaByOrderIdBO selectArea = new SelectAreaByOrderIdBO();
		BeanUtils.copyProperties(param, selectArea);
		selectArea.setOrderIds(orderIdBOList);
		//查询在库信息
		List<WarehouseOrderDetailPO> stockInfo = orderService.getStockInfo(selectArea);
		for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
			//将在库信息存入根据订单号存入到map当中
			map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
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
				WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getFchildorderid());
				if(!ObjectUtils.isEmpty(warehouseDetail)){
					//将订单详情信息和在库信息数据进行拼接
					orderService.getOrderDetail(warehouseDetail,orderDetail);
				}
			}
		}
		
		List<OrderPO> orderList = new ArrayList<>();
		Integer i=1;
		for(Map.Entry<String,WarehouseOrderDetailPO> entry : map.entrySet()){
			//组织装车优化需要的参数
			WarehouseOrderDetailPO value = entry.getValue();
			OrderPO orderPO = new OrderPO();
			orderPO.setOrderId(value.getOrderId());
			orderPO.setProductName(value.getFgroupgoodname());
			orderPO.setProductSize(value.getFproductRule());
			orderPO.setUnit(value.getUnit());
			orderPO.setOrderAmount(value.getAmount());
			orderPO.setDeliveryAmount(value.getAmount());
			WarehousePO warehousePO = new WarehousePO();
			warehousePO.setWarehouseId(value.getWarehouseId());
			warehousePO.setWarehouseName(value.getWarehouseName());
			warehousePO.setAreaList(value.getAreaList());
			orderPO.setWarehouse(warehousePO);
			orderPO.setOrderStatus(value.getFstatus());
			if(!StringUtils.isEmpty(value.getFdelivery())){
				orderPO.setDeliveryTime(value.getFdelivery().getTime());
			}
			orderPO.setAddress(new StringBuffer().append(value.getFcodeprovince()).append(value.getFaddressdetail()).toString());
			orderPO.setSequence(i);
			orderPO.setContacts(value.getFconsignee());
			orderPO.setCustomerName(value.getFcusername()==null?value.getFpusername():value.getFcusername());
			orderPO.setMaterialName(value.getFmaterialname());
			i++;
			orderList.add(orderPO);
		}
		
		HttpResult result = allocationServer.getExcellentLoding(param);
		WaybillDeliveryOrderPO waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
		List<DeliveryOrderPO> deliveryOrderList = waybillDeliveryOrder.getDeliveryOrder();
		DeliveryOrderPO deliveryOrder = new DeliveryOrderPO();
		BeanUtils.copyProperties(deliveryOrder, deliveryOrderList.get(0));
		deliveryOrder.setDeliveryId(deliveryId);
		deliveryOrder.setOrders(orderList);
		deliveryOrderList.add(deliveryOrder);
		return orderList;
	}
	
	@Override
	public Map<String, Object> againVerifyAddOrder(AgainVerifyAddOrderBO param) {
		//计算装载率,判断装载率是否超出,超出直接驳回
		//不超出,将订单数据存入订单表中,并重新生成提货单.提货员和装车员和原来的一致,装车顺序在原来的基础上递增
		param.setDeliveryIdEffect(AllocationConstant.DELIVERY_UNEFFEFT);
		HttpResult number = allocationServer.getNumber(1);
		if(!ObjectUtils.isEmpty(number.getData())){
			JsonArray numberJsonArray = jsonParser.parse(gson.toJson(number.getData())).getAsJsonObject().get("numbers").getAsJsonArray();
			String deliveryId = new StringBuffer().append(AllocationConstant.DELIVERYID_PREFIX).append(numberJsonArray.get(0).getAsString()).toString();
			param.setDeliveryId(deliveryId);
		}
		HttpResult result = allocationServer.againVerifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> againVerifyAllocation(List<AgainVerifyAllocationBO> param) {
		//冗余表修改订单状态
		//修改提货单确认状态修改为feffect为1
		//修改配货订单表中订单的提货单号
		//修改装车顺序
		for (AgainVerifyAllocationBO againVerifyAllocationBO : param) {
			againVerifyAllocationBO.setDeliveryIdEffect(AllocationConstant.DELIVERY_EFFEFT);
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
		param.setDeliveryIdEffect(AllocationConstant.DELIVERY_UNEFFEFT);
		//修改订单表中订单状态为已入库
		param.setOrderStatus(AllocationConstant.ALL_ADD_STOCK);
		//修改配货表的确认状态为2
		param.setAllocationIdEffect(AllocationConstant.ALLOCATION_UNEFFECT);
		HttpResult result = allocationServer.cancelAllocation(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addzhinengpeihuo(BaseAddBO base) {
		List<WarehouseOrderDetailPO> list = new ArrayList<>();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<>();
		//获取所有的在库信息id
		HttpResult result = null;
		result = allocationServer.addzhinengpeihuo();
		Object data = result.getData();
		if(ObjectUtils.isEmpty(data)){
			return MsgTemplate.successMsg("没有已入库的订单所有就没有插入的数据");
		}
		JsonArray asJsonArray = jsonParser.parse(gson.toJson(data)).getAsJsonArray();
		List<String> orderIdsList =new  ArrayList<>();
		for (JsonElement jsonElement : asJsonArray) {
			String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
			orderIdsList.add(orderId);
		}
		OrderIdsBO param = new OrderIdsBO();
		param.setChildOrderIds(orderIdsList);
		result = orderServer.getOrderByOrderIds(param);
		JsonArray orderIdsJsonArray = jsonParser.parse(gson.toJson(result.getData())).getAsJsonArray();
		String uuid = UUID.randomUUID().toString();
		AddExcellentAllocationBO allocationBO = null;
		if(!ObjectUtils.isEmpty(result.getData())){
			for(int i=0;i<orderIdsJsonArray.size();i++){
				String fdblflag = orderIdsJsonArray.get(i).getAsJsonObject().get("fdblflag").getAsString();
				//订单筛选,去除订单中双写的订单,取值为0的数据
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
					WarehouseOrderDetailPO orderDetail = gson.fromJson(orderIdsJsonArray.get(i), WarehouseOrderDetailPO.class);
					//将订单信息根据订单号存入到map中
					map.put(orderDetail.getFchildorderid(), orderDetail);
				}
			}
			//遍历入库订单的在库信息
			for (int i=0;i<asJsonArray.size();i++) {
				WarehouseOrderDetailPO fromJson = gson.fromJson(asJsonArray.get(i), WarehouseOrderDetailPO.class);
				WarehouseOrderDetailPO orderDetail = map.get(fromJson.getOrderId());
				//组织参数
				orderDetail.setWarehouseId(fromJson.getWarehouseId());
				orderDetail.setWarehouseName(fromJson.getWarehouseName());
				orderDetail.setWarehouseAreaId(fromJson.getWarehouseAreaId());
				orderDetail.setWarehouseAreaName(fromJson.getWarehouseAreaName());
				orderDetail.setWarehouseLocId(fromJson.getWarehouseLocId());
				orderDetail.setWarehouseLocName(fromJson.getWarehouseLocName());
				orderDetail.setAllocationId(uuid);
				BeanUtils.copyProperties(orderDetail, fromJson);
				String address = orderDetail.getFcodeprovince()+orderDetail.getFaddressdetail();
				fromJson.setFaddressdetail(address);
				fromJson.setSequence(i+1);
				list.add(fromJson);
			}
			String deliveryId = null;
			String waybillId = null;
			//调取订单服务获取单号并组织成运单号,提货单号
			HttpResult number = allocationServer.getNumber(2);
			if(!ObjectUtils.isEmpty(number.getData())){
				JsonArray numberJsonArray = jsonParser.parse(gson.toJson(number.getData())).getAsJsonObject().get("numbers").getAsJsonArray();
				deliveryId = new StringBuffer().append(AllocationConstant.DELIVERYID_PREFIX).append(numberJsonArray.get(0).getAsString()).toString();
				waybillId = new StringBuffer().append(AllocationConstant.WAYBILLID_PREFIX).append(numberJsonArray.get(1).getAsString()).toString();
			}
			allocationBO = new AddExcellentAllocationBO();
			allocationBO.setCarId("car001");
			allocationBO.setAllocationId(uuid);
			allocationBO.setDeliveryId(deliveryId);
			allocationBO.setWaybillId(waybillId);
			BeanUtils.copyProperties(base,allocationBO);
			//存入智能配货表数据
			result = allocationServer.addExcellentAllocation(allocationBO);
			if(result.isSuccess()){
				//调存入配货订单表接口,存入数据
				result = allocationServer.addDeliAllocOrder(list);
			}
		}
		//删除确认配货和确认优化锁
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+base.getPartnerId());
		return MsgTemplate.successMsg(allocationBO);
	}

	@Override
	public Map<String, Object> getPicker() {
		PickerPO picker1 = new PickerPO("977","周德星","15157780633","空闲");
		PickerPO picker2 = new PickerPO("977","郑杰","15157780633","空闲");
		List<PickerPO> list = new ArrayList<>();
		list.add(picker1);
		list.add(picker2);
		return MsgTemplate.successMsg(list);
	}

	@Override
	public Map<String, Object> getLoadingPerson() {
		LoadingPersonPO picker1 = new LoadingPersonPO("977","郑天伟","15157780633","空闲");
		LoadingPersonPO picker2 = new LoadingPersonPO("977","郭全凯","15157780633","空闲");
		List<LoadingPersonPO> list = new ArrayList<>();
		list.add(picker1);
		list.add(picker2);
		return MsgTemplate.successMsg(list);
	}

	@Override
	public Map<String, Object> againVerifyAllocation(MergeModelBO param, PartnerInfoBO partnerInfoBean) {
		//确认配货和确认优化操作,需要上锁,以合作方id为锁进行上锁
		if(1==redisClient.setnx(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(),"上锁")){
			redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 86400);
			//装车优化确认配货合并移除订单,确认追加订单,确认配货
			//冗余表修改订单状态
			//修改提货单确认状态修改为feffect为1
			//修改配货订单表中订单的提货单号
			String waybillId = param.getWaybillId();
			//整理装车顺序需要的数据
			HashMap<String,SequenceBO> map = new HashMap<>();
			if(!ObjectUtils.isEmpty(param.getSequenceList())){
				for(SequenceBO sequence : param.getSequenceList()){
					map.put(sequence.getOrderId(),sequence);
				}
				//修改装车顺序================
				List<AgainVerifyAllocationBO> againVerifyAllocation = new ArrayList<>();
				param.setAgainVerifyAllocation(againVerifyAllocation);
				for(Map.Entry<String,SequenceBO> entry : map.entrySet()){
					AgainVerifyAllocationBO allocation = new AgainVerifyAllocationBO();
					BeanUtils.copyProperties(partnerInfoBean,allocation);
					allocation.setDeliveryIdEffect(AllocationConstant.DELIVERY_EFFEFT);
					allocation.setSequence(String.valueOf(entry.getValue().getSequence()));
					allocation.setOrderId(entry.getKey());
					allocation.setDeliveryId(entry.getValue().getDeliveryId());
					allocation.setWaybillId(waybillId);
					allocation.setStatus(Integer.valueOf(AllocationConstant.ORDER_ALREADY_ALLOCATION));
					againVerifyAllocation.add(allocation);
				}
				//修改装车顺序================
			}
			//配货管理追加订单===================
			//计算装载率,判断装载率是否超出,超出直接驳回
			//不超出,将订单数据存入订单表中,并重新生成提货单.提货员和装车员和原来的一致,装车顺序在原来的基础上递增
			
			//从缓存中取出要追加的订单
			String str = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			if(!StringUtils.isEmpty(str)){
				List<WarehouseOrderDetailPO> cacheList = gson.fromJson(str, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
				//获取移除订单的数据
				String string = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
				if(!StringUtils.isEmpty(string)){
					List<String> orderIdsList = gson.fromJson(string, List.class);
					//追加的订单和移除的订单需要进行比较,假如追加的订单又被移除了,则需要删除缓存中追加订单的数据
					for (String orderStr : orderIdsList) {
						Iterator<WarehouseOrderDetailPO> iterator = cacheList.iterator();
						while(iterator.hasNext()){
							WarehouseOrderDetailPO next = iterator.next();
							if(orderStr.equals(next.getFchildorderid())){
								iterator.remove();
							}
						}
					}
				}
				
				//已经经过筛选处理的追加订单
				if(!ObjectUtils.isEmpty(cacheList)){
					List<String> orderIds = new ArrayList<>();
					for (WarehouseOrderDetailPO orderDetailPO : cacheList) {
						orderIds.add(orderDetailPO.getFchildorderid());
					}
					
					//判断订单状态,此处订单状态必须为22已入库状态
					HttpResult result = allocationServer.getOrderByOrderIds(orderIds);
					List<OrderPO> orderPOList = gson.fromJson(gson.toJson(result.getData()),new TypeToken<ArrayList<OrderPO>>(){}.getType());
					for (OrderPO orderPO : orderPOList) {
						Integer orderStatus = orderPO.getOrderStatus();
						if(!AllocationConstant.ALL_ADD_STOCK.equals(String.valueOf(orderStatus))){
							String error = orderPO.getOrderId();
							return MsgTemplate.failureMsg(SysMsgEnum.ORDER_STATUS_ERROR,error);
						}
					}
					
					String allocationId = param.getAllocationId();
					List<AddAllocationOrderBO> ordersList = new ArrayList<>();
					AgainVerifyAddOrderBO againVerifyAddOrder = new AgainVerifyAddOrderBO();
					param.setAgainVerifyAddOrder(againVerifyAddOrder);
					BeanUtils.copyProperties(partnerInfoBean,againVerifyAddOrder);
					BeanUtils.copyProperties(param,againVerifyAddOrder);
					//提货单做缓存
					String newDeliveryId = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId);
					againVerifyAddOrder.setDeliveryId(newDeliveryId);
					againVerifyAddOrder.setOrdersList(ordersList);
					againVerifyAddOrder.setDeliveryIdEffect(AllocationConstant.DELIVERY_UNEFFEFT);
					
					//组织新增订单所需的参数
					for (WarehouseOrderDetailPO detail : cacheList) {
						for (WarehouseAreaBO areaBO : detail.getAreaList()) {
							String areaId = areaBO.getWarehouseAreaId();
							String areaName = areaBO.getWarehouseAreaName();
							for (WarehouseLocationBO locationBO : areaBO.getLocationList()) {
								AddAllocationOrderBO order = new AddAllocationOrderBO();
								//组织追加订单需要的参数
								order.setOrderId(detail.getFchildorderid());
								order.setAllocationId(allocationId);
								order.setProductName(detail.getFgroupgoodname());
								order.setMaterialName(detail.getFmaterialname());
								order.setCustomerName(detail.getFcusername()==null?detail.getFpusername():detail.getFcusername());
								order.setContacts(detail.getFconsignee());
								if(!StringUtils.isEmpty(detail.getFdelivery())){
									order.setDeliveryTime(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(detail.getFdelivery()));
								}
								SequenceBO sequenceBO = map.get(detail.getFchildorderid());
								order.setSequence(String.valueOf(sequenceBO.getSequence()));
								order.setOrderAmount(String.valueOf(detail.getFamount()));
								order.setAddress(new StringBuffer().append(detail.getFcodeprovince()).append(detail.getFaddressdetail()).toString());
								order.setUnit(detail.getUnit());
								order.setWarehouseId(detail.getWarehouseId());
								order.setWarehouseName(detail.getWarehouseName());
								order.setWarehouseAreaId(areaId);
								order.setWarehouseAreaName(areaName);
								order.setWarehouseLocId(locationBO.getWarehouseLocId());
								order.setWarehouseLocName(locationBO.getWarehouseLocName());
								ordersList.add(order);
							}
						}
					}
					//修改订单服务订单状态修改为已配货
					for (WarehouseOrderDetailPO orderDetail : cacheList) {
						OrderIdBO orderIdBO = new OrderIdBO();
						orderIdBO.setOrderId(orderDetail.getFchildorderid());
						orderIdBO.setStatus(AllocationConstant.ORDER_ALREADY_ALLOCATION);
						//通知订单服务修改,需要批量执行
						HttpResult updateResult = orderServer.updateOrderStatus(orderIdBO);
					}
				}
			}
			//配货管理追加订单===================
			
			//配货管理移除订单===================
			String string = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
			List<WarehouseOrderDetailPO> cacheList = gson.fromJson(str, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			List<String> orderIdsList = gson.fromJson(string, List.class);
			if(!ObjectUtils.isEmpty(cacheList) &&(!ObjectUtils.isEmpty(orderIdsList))){
				//追加的订单和移除的订单需要进行比较,假如追加的订单又被移除了,则需要删除缓存中移除订单数据
				Iterator orderIterator = orderIdsList.iterator();
				while(orderIterator.hasNext()){
					String orderNext = (String)orderIterator.next();
					Iterator<WarehouseOrderDetailPO> iterator = cacheList.iterator();
					while(iterator.hasNext()){
						WarehouseOrderDetailPO next = iterator.next();
						if(orderNext.equals(next.getFchildorderid())){
							orderIterator.remove();
						}
					}
				}
			}
			
			if(!ObjectUtils.isEmpty(orderIdsList)){
				MoveOrderPO moveOrder = new MoveOrderPO();
				param.setMoveOrder(moveOrder);
				BeanUtils.copyProperties(partnerInfoBean,moveOrder);
				
				//通知订单服务修改订单状态为已入库,先判断该订单是否为已配货,是已配货才进行修改否则不修改
				moveOrder.setOrderIds(orderIdsList);
				HttpResult updateOrderStatus = allocationServer.getAlreadyAllocOrder(orderIdsList);
				List<String> list = (List<String>) updateOrderStatus.getData();
				if(!ObjectUtils.isEmpty(list)){
					for (String order : list) {
						OrderIdBO orderIdBO = new OrderIdBO();
						orderIdBO.setOrderId(order);
						orderIdBO.setStatus(AllocationConstant.ALL_ADD_STOCK);
						//通知订单服务修改,需要批量执行
						HttpResult updateResult = orderServer.updateOrderStatus(orderIdBO);
					}
				}
				//需要订单服务修改订单状态成功情况下,移除订单表数据,并且修改冗余表订单状态(该逻辑在服务端)
				moveOrder.setStatus(Integer.valueOf(AllocationConstant.ALL_ADD_STOCK));
			}
			//配货管理移除订单===================
			
			HttpResult result = allocationServer.againVerifyAllocation(param);
			if(result.isSuccess()){
				//清楚缓存
				redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
				redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
				redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			}
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.failureMsg(SysMsgEnum.COMMON_ALLOCATION_LOADING__ERROR);
		}
	}

	@Override
	public Map<String, Object> againVerifyAddOrder(List<WarehouseOrderDetailPO> detailList) {
		String waybillId = detailList.get(0).getWaybillId();
		//追加订单号缓存处理
		String str = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		if(!ObjectUtils.isEmpty(detailList)){
			List<String> orderIds = new ArrayList<>();
			for (WarehouseOrderDetailPO orderDetailPO : detailList) {
				orderIds.add(orderDetailPO.getFchildorderid());
			}
			if(!StringUtils.isEmpty(str)){
				List<String> fromJson = gson.fromJson(str, List.class);
				if(!ObjectUtils.isEmpty(orderIds)){
					//保存订单号的唯一性,防止误点和网络延迟的特殊情况出现
					HashSet<String> set = new HashSet<>();
					for (String string2 : fromJson) {
						set.add(string2);
					}
					for (String string2 : orderIds) {
						set.add(string2);
					}
					List<String> newOrderIdList = new ArrayList<>();
					Iterator<String> iterator = set.iterator();
					while(iterator.hasNext()){
						String orderId = iterator.next();
						newOrderIdList.add(orderId);
					}
					redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(newOrderIdList));
					redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId,86400);
				}
			}else{
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(orderIds));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId,86400);
			}
			
			//缓存详细订单信息
			String cacheOrder = redisClient.get(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			if(!StringUtils.isEmpty(cacheOrder)){
				Map<String,WarehouseOrderDetailPO> map = new HashMap<>();
				List<WarehouseOrderDetailPO> cacheList = gson.fromJson(cacheOrder, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
				for (WarehouseOrderDetailPO orderDetailPO : cacheList) {
					map.put(orderDetailPO.getFchildorderid(), orderDetailPO);
				}
				for (WarehouseOrderDetailPO orderDetailPO : detailList) {
					map.put(orderDetailPO.getFchildorderid(), orderDetailPO);
				}
				cacheList = new ArrayList<>();
				for(Map.Entry<String, WarehouseOrderDetailPO> entry:map.entrySet()){
					cacheList.add(entry.getValue());
				}
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(cacheList));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId,86400);
			}else{
				redisClient.set(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(detailList));
				redisClient.expire(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId,86400);
			}
		}
		return MsgTemplate.successMsg();
	}

	@Override
	public Map<String, Object> againCancelAllocation(String waybillId) {
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
		return MsgTemplate.successMsg();
	}

	@Override
	public Map<String, Object> intelligentCancelAllocation(String parameter) {
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+parameter);
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_REMOVE_ORDER+parameter);
		redisClient.del(RedisPrefixContant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ADD_ORDER+parameter);
		return MsgTemplate.successMsg();
	}
}
