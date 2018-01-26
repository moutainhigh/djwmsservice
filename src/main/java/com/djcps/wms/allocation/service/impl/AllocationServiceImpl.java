package com.djcps.wms.allocation.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.djcps.wms.commons.base.BaseBO;
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
import com.djcps.wms.allocation.model.GetWaybillByDeliveryIdBO;
import com.djcps.wms.allocation.model.IntelligentAllocation;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.model.GetAllocationResultBO;
import com.djcps.wms.allocation.model.GetDeliveryByWaybillBO;
import com.djcps.wms.allocation.model.GetExcellentLodingBO;
import com.djcps.wms.allocation.model.GetIntelligentAllocaBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.allocation.service.AllocationService;
import com.djcps.wms.commons.httpclient.HttpResult;
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
import com.google.gson.JsonParser;

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
	public Map<String, Object> getAllocationResultList(GetAllocationResultBO param) {
		//TODO,先查询混合配货界面的数据,这里是根据仓库划分的
		List<WarehouseOrderDetailPO> stockInfo = null;
		//String是订单号,map用来存在库信息查询出来的数据
		Map<String,WarehouseOrderDetailPO> orderDetaiMap = new HashMap<String,WarehouseOrderDetailPO>();
		//订单批量查询使用
		List<OrderIdBO> orderList = new ArrayList<OrderIdBO>();
		//String为提货单号
		Map<String,WarehouseOrderDetailPO> deliveryMap= new HashMap();
		//运单批量查询是要
		List<GetWaybillByDeliveryIdBO> deliveryList = new ArrayList<>();
		//订单号为空,直接查询在库信息
		if(ObjectUtils.isEmpty(param.getFchildorderid())){
			HttpResult orderResult = allocationServer.getOrderIdByWarsehouseId(param);
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			List<OrderIdBO> list = new ArrayList<OrderIdBO>();
			if(!ObjectUtils.isEmpty(orderResult.getData())){
				JsonArray asJsonArray = jsonParser.parse(gson.toJson(orderResult.getData())).getAsJsonArray();
				for (JsonElement jsonElement : asJsonArray) {
					String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
					OrderIdBO orderIdBO = new OrderIdBO();
					orderIdBO.setOrderId(orderId);
					list.add(orderIdBO);
				}
			}
			selectAreaByOrderId.setOrderIds(list);
			//组织参数获取在库信息
			stockInfo = orderService.getStockInfo(selectAreaByOrderId);
			List<String> orderIds = new ArrayList<String>();
			if(!ObjectUtils.isEmpty(stockInfo)){
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					orderIds.add(warehouseOrderDetailPO.getFchildorderid());
					orderDetaiMap.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
					OrderIdBO order = new OrderIdBO();
					order.setChildOrderId(warehouseOrderDetailPO.getFchildorderid());
					orderList.add(order);
				}
				OrderIdsBO order = new OrderIdsBO();
				order.setChildOrderIds(orderIds);
				HttpResult orderByOrderIds = orderServer.getOrderByOrderIds(order);
				JsonArray asJsonArray = jsonParser.parse(gson.toJson(orderByOrderIds.getData())).getAsJsonArray();
				for (JsonElement jsonElement : asJsonArray) {
					String fdblflag = jsonElement.getAsJsonObject().get("fdblflag").getAsString();
					//订单筛选,去除订单中双写的订单,取值为0的数据
					if(AppConstant.GROUP_ORDER_DOUBLE.equals(fdblflag)){
						WarehouseOrderDetailPO fromJson = gson.fromJson(jsonElement, WarehouseOrderDetailPO.class);
						WarehouseOrderDetailPO warehouseOrderDetailPO = orderDetaiMap.get(fromJson.getOrderId());
						if(!ObjectUtils.isEmpty(warehouseOrderDetailPO)){
							//拼接参数
							orderService.getOrderDetail(fromJson,warehouseOrderDetailPO);
						}
					}
				}
			}
		}else{
			//订单号不为空则根据订单号模糊查询
			OrderIdBO order = new OrderIdBO();
			order.setChildOrderId(param.getFchildorderid());
			HttpResult orderByOrderId = orderServer.getOrderByOrderId(order);
			if(!ObjectUtils.isEmpty(orderByOrderId.getData())){
				WarehouseOrderDetailPO fromJson = gson.fromJson(gson.toJson(orderByOrderId.getData()), WarehouseOrderDetailPO.class);
				WarehouseOrderDetailPO warehouseOrderDetailPO = orderDetaiMap.get(fromJson.getOrderId());
				if(!ObjectUtils.isEmpty(warehouseOrderDetailPO)){
					//拼接参数
					orderService.getOrderDetail(fromJson,warehouseOrderDetailPO);
				}
			}
		}
		//根据订单号获取提货单号
		HttpResult result = allocationServer.getOrderInfoByOrderId(orderList);
		if(!ObjectUtils.isEmpty(result.getData())){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(result.getData())).getAsJsonArray();
			for (JsonElement jsonElement : asJsonArray) {
				String effect = jsonElement.getAsJsonObject().get("deliveryIdEffect").getAsString();
				String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
				//判断提货单确认有效
				if(AppConstant.DELIVERY_EFFEFT.equals(jsonElement)){
					WarehouseOrderDetailPO warehouseOrderDetailPO = orderDetaiMap.get(orderId);
					if(warehouseOrderDetailPO!=null){
						//提货单,车牌号赋值
						String plateNumber = jsonElement.getAsJsonObject().get("plateNumber").getAsString();
						String deliveryId = jsonElement.getAsJsonObject().get("deliveryId").getAsString();
						warehouseOrderDetailPO.setDeliveryId(deliveryId);
						warehouseOrderDetailPO.setPlateNumber(plateNumber);
						//组织批量查询参数
						GetWaybillByDeliveryIdBO getWaybillByDeliveryId = new GetWaybillByDeliveryIdBO();
						getWaybillByDeliveryId.setDeliveryId(deliveryId);
						deliveryList.add(getWaybillByDeliveryId);
						//根据运单号存入map
						deliveryMap.put(deliveryId,warehouseOrderDetailPO);
					}
				}
			}
			//根据提货单号获取运单号
			HttpResult waybillResult = allocationServer.getWaybillByDeliveryId(deliveryList);
			if(!ObjectUtils.isEmpty(waybillResult.getData())){
				JsonArray jsonArray = jsonParser.parse(gson.toJson(result.getData())).getAsJsonArray();
				for (JsonElement jsonElement : jsonArray) {
					String waybillId = jsonElement.getAsJsonObject().get("waybillId").getAsString();
					String deliveryId = jsonElement.getAsJsonObject().get("deliveryId").getAsString();
					WarehouseOrderDetailPO warehouseOrderDetailPO = deliveryMap.get(deliveryId);
					warehouseOrderDetailPO.setWaybillId(waybillId);
				}
			}
		}
		return MsgTemplate.successMsg(stockInfo);
	}

	@Override
	public Map<String, Object> getIntelligentAllocaList(GetIntelligentAllocaBO param) {
		String deliveryId = null;
		String waybillId = null;
		String remind = param.getRemind();
		//订单提醒:异+不补,异+备
		//订单状态必须为已入库状态
		//状态和提醒校验必须都通过,这里暂时先只做订单状态校验
		if(AppConstant.ALL_ADD_STOCK.equals(param.getOrderStatus())){
			//提货单号不为空,则根据提货单号查询配货表
			if(!ObjectUtils.isEmpty(param.getDeliveryId())){
				HttpResult result = allocationServer.getIntelligentAllocaList(param);
				if(!ObjectUtils.isEmpty(result.getData())){
					String carid = jsonParser.parse(gson.toJson(result.getData())).getAsJsonObject().get("carid").getAsString();
					//TODO 根据车辆id获取车辆信息
					CarInfo carInfo = new CarInfo();
					WarehouseOrderDetailPO warehouseOrderDetailPO = gson.fromJson(gson.toJson(result.getData()), WarehouseOrderDetailPO.class);
					IntelligentAllocation allocation = new IntelligentAllocation();
					allocation.setCarInfo(carInfo);
					allocation.setWarehouseOrderDetailPO(warehouseOrderDetailPO);
					return MsgTemplate.successMsg(allocation);
				}
			}else{
				//提货单号为空,调智能配货服务,往数据库中插入数据
				//TODO,智能配货服务插入数据,然后智能配货也需要返回插入的数据,然后智能配货id查询配货订单
				HttpResult result =  allocationServer.zhinengpeihuo();
				HttpResult number = allocationServer.getNumber(2);
				JsonArray jsonArray = jsonParser.parse(gson.toJson(number.getData())).getAsJsonObject().get("numbers").getAsJsonArray();
				deliveryId = jsonArray.get(0).getAsString();
				waybillId = jsonArray.get(1).getAsString();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> verifyAllocation(VerifyAllocationBO param) {
		//配货之前先确认配货结果是否是已配货，是的话直接全部驳回
		List<String> orderIds = param.getOrderIds();
		OrderIdsBO order = new OrderIdsBO();
		order.setChildOrderIds(orderIds);
		HttpResult orderResult = orderServer.getOrderByOrderIds(order);
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
		//修改配货表中的标志，修改为确认配货,且插入提货单数据(插入提货单确认状态feffect为1)，
		//并通过智能配货id,修改配货订单表中的提货单id(该id原先是为null的)
		param.setEffect(AppConstant.ALLOCATION_EFFECT);
		HttpResult result = allocationServer.verifyAllocation(param);
		return MsgTemplate.customMsg(result);
		//最后通知提货员装车员
	}

	@Override
	public Map<String, Object> moveOrder(OrderIdBO param) {
		HttpResult result = allocationServer.moveOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAddOrderList(OrderParamBO param) {
		//订单状态:已入库
		param.getChildOrderModel().setFstatus(AppConstant.ALL_ADD_STOCK);
		HttpResult result = allocationServer.getAddOrderList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> verifyAddOrder(List<AddAllocationOrderBO> param) {
		//TODO 智能配货判断车辆装载率
		HttpResult result = allocationServer.verifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllocationManageList(GetAllocationResultBO param) {
		HttpResult result = allocationServer.getAllocationManageList(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getDeliveryByWaybill(GetDeliveryByWaybillBO param) {
		HttpResult result = allocationServer.getDeliveryByWaybillId(param);
		//TODO,根据运单号获取提货单号,然后根据提货单号,获取订单信息,然后查询订单服务获取订单信息,然后这些信息组合起来给前端
//		HttpResult result = allocationServer.getDeliveryByWaybill(param);
//		allocationServer.get
		return MsgTemplate.customMsg(result);
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
		HttpResult result = allocationServer.againVerifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> againVerifyAllocation(AgainVerifyAllocationBO param) {
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
		// TODO Auto-generated method stub
		//传递所有订单号，修改成已入库,通知wms取消车辆
		//修改订单的订单状态，修改成已入库
		OrderIdBO orderId = new OrderIdBO();
		for(int i=0;i<param.getOrderIds().size();i++){
			orderId.setStatus(AppConstant.ALL_ADD_STOCK);
			orderId.setChildOrderId(param.getOrderIds().get(i).getOrderId());
			//判断修改成功才能继续往下走
			HttpResult updateOrderResult = orderServer.updateOrderStatus(orderId);
		}
		//修改提货单确认状态修改为feffect为2
		//修改订单表中订单状态为已入库
		HttpResult result = allocationServer.cancelAllocation(param);
		return MsgTemplate.customMsg(result);
	}
}
