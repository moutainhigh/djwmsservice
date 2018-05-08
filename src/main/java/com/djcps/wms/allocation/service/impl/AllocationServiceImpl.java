package com.djcps.wms.allocation.service.impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.base.PushExtraFieldBO;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.constant.RedisPrefixConstant;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.enums.SysMsgEnum;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.api.partneroperatingmanage.carchecksign.api.TmsCarchecksignServer;
import com.api.partneroperatingmanage.carchecksign.model.request.ReqVehicleQueuingListBiz;
import com.base.TmsJsonResult;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.allocation.constant.AllocationConstant;
import com.djcps.wms.allocation.enums.AllocationMsgEnum;
import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.AddExcellentAllocationBO;
import com.djcps.wms.allocation.model.AgainVerifyAddOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAllocationBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.CarInfo;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.DeliveryOrderPO;
import com.djcps.wms.allocation.model.DeliveryPO;
import com.djcps.wms.allocation.model.GetAllocationManageListPO;
import com.djcps.wms.allocation.model.IntelligentAllocationPO;
import com.djcps.wms.allocation.model.LoadingPersonPO;
import com.djcps.wms.allocation.model.MergeModelBO;
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.OrderPO;
import com.djcps.wms.allocation.model.PickerPO;
import com.djcps.wms.allocation.model.RelativeIdBO;
import com.djcps.wms.allocation.model.SequenceBO;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.model.WarehousePO;
import com.djcps.wms.allocation.model.WaybillDeliveryOrderPO;
import com.djcps.wms.allocation.model.WaybillPO;
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
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.commons.utils.RedisUtil;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.server.LoadingTaskServer;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseAreaBO;
import com.djcps.wms.order.model.WarehouseLocationBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.push.model.PushMsgBO;
import com.djcps.wms.push.mq.producer.AppProducer;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import httprequest.plugin.http.HTTPResponse;
import httprequest.plugin.http.HeadCookies;

/**
 * 混合配货业务层实现类
 * @description:
 * @company:djwms
 * @author:  zdx
 * @date:2017年11月30日
 */
@Service
public class AllocationServiceImpl implements AllocationService {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(AllocationServiceImpl.class);	
	
	private Gson gson = GsonUtils.gson;
	
	private Gson dataFormatGson = GsonUtils.gson;
	
	private JsonParser jsonParser = new JsonParser();

	@Autowired
	private AllocationServer allocationServer;
	@Autowired
	private OrderServer orderServer;
	@Autowired
	@Qualifier("redisClientCluster")
	private RedisClient redisClient;
	@Autowired
    private LoadingTaskServer loadingTaskServer;
    @Resource
    private AppProducer appProducer;
	@Autowired
	private TmsCarchecksignServer carchecksignServer;
	@Autowired
	private AbnormalServer abnormalServer;
	
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
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>(16);
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
			if(StringUtils.isEmpty(str)){
				BaseVO vo = new BaseVO();
				vo.setTotal(0);
				vo.setResult(null);
				return MsgTemplate.successMsg(vo);
			}
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
				stockInfo = orderServer.getOrderStockInfo(selectArea);
				//创建订单批量查询需要的list和order
				List<String> childOrderIds = new ArrayList<String>();
				if(ObjectUtils.isEmpty(stockInfo)){
					BaseVO vo = new BaseVO();
					vo.setTotal(0);
					vo.setResult(null);
					return MsgTemplate.successMsg(vo);
				}
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					String orderId = warehouseOrderDetailPO.getOrderId();
					childOrderIds.add(orderId);
					//将在库信息存入根据订单号存入到map当中
					map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
				}
				BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
				batchOrder.setKeyArea(param.getPartnerArea());
				batchOrder.setOrderIds(childOrderIds);
				//根据订单号批量查询订单详情信息
				HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
				BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
				List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
				
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
				for (WarehouseOrderDetailPO orderDetail : joinOrderParamInfo) {
					WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getOrderId());
					if(!ObjectUtils.isEmpty(warehouseDetail)){
						//将订单详情信息和在库信息数据进行拼接
						BeanUtils.copyProperties(orderDetail, warehouseDetail,"areaList","amountSaved","instockAmount","remark","warehouseId","warehouseName");
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
				stockInfo = orderServer.getOrderStockInfo(selectAreaByOrderId);
				if(ObjectUtils.isEmpty(stockInfo)){
					BaseVO vo = new BaseVO();
					vo.setTotal(0);
					vo.setResult(null);
					return MsgTemplate.successMsg(vo);
				}
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
					map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
				}
				BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
				batchOrder.setKeyArea(param.getPartnerArea());
				batchOrder.setOrderIds(redundantOrderList);
				//根据订单号批量查询订单详情信息
				HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
				BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
				List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
				for (WarehouseOrderDetailPO orderDetail : joinOrderParamInfo) {
					WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getOrderId());
					if(!ObjectUtils.isEmpty(warehouseDetail)){
						//将订单详情信息和在库信息数据进行拼接
						BeanUtils.copyProperties(orderDetail, warehouseDetail,"areaList","amountSaved","instockAmount","remark");
					}
				}
			}
		}
		if(!ObjectUtils.isEmpty(redundantOrderList)){
			List<String> newOrderList = new ArrayList();
			//根据订单号获取提货单信息
			HttpResult deliveryResult = allocationServer.getDeliveryByOrderIds(redundantOrderList);
			if(!ObjectUtils.isEmpty(deliveryResult.getData())){
				List<DeliveryPO> deliveryPOList = gson.fromJson(gson.toJson(deliveryResult.getData()), new TypeToken<ArrayList<DeliveryPO>>(){}.getType());
				for (DeliveryPO delivery : deliveryPOList) {
					String orderId = delivery.getOrderId();
					//判断提货单确认有效
					WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderId);
					if(warehouseOrderDetailPO!=null){
						//提货单赋值
						String deliveryId = delivery.getDeliveryId();
						warehouseOrderDetailPO.setDeliveryId(deliveryId);
						deliveryIdList.add(deliveryId);
					}
				}
			}
		}
		if(!ObjectUtils.isEmpty(deliveryIdList)){
			//根据提货单号获取运单信息
			HttpResult waybillResult = allocationServer.getWaybillByDeliveryIds(deliveryIdList);
			if(!ObjectUtils.isEmpty(waybillResult.getData())){
				List<WaybillPO> waybillPOList = gson.fromJson(gson.toJson(waybillResult.getData()), new TypeToken<ArrayList<WaybillPO>>(){}.getType());
				for (WaybillPO waybillPO : waybillPOList) {
					String orderId = waybillPO.getOrderId();
					String plateNumber = waybillPO.getPlateNumber();
					String waybillId = waybillPO.getWaybillId();
					WarehouseOrderDetailPO warehouseOrderDetailPO = map.get(orderId);
					if(warehouseOrderDetailPO!=null){
						warehouseOrderDetailPO.setWaybillId(waybillId);
						warehouseOrderDetailPO.setPlateNumber(plateNumber);
					}
				}
			}
		}
		BaseVO base = new BaseVO();
		base.setTotal(total);
		base.setResult(stockInfo);
		return MsgTemplate.successMsg(base);
	}

	@Override
	public Map<String, Object> getIntelligentAllocaList(GetIntelligentAllocaBO param) {
		String allocationId = param.getAllocationId();
		IntelligentAllocationPO allocation = new IntelligentAllocationPO();
		//参与智能配货的条件:订单提醒:异+不补,异+备;订单状态必须为已入库状态
		//状态和提醒校验必须都通过,这里暂时先只做订单状态校验
		OtherHttpResult result = allocationServer.getOrderByAllocationId(param);
		List<OrderPO> orderPoList = gson.fromJson(gson.toJson(result.getData()), new TypeToken<ArrayList<OrderPO>>(){}.getType());
		
		if(!ObjectUtils.isEmpty(orderPoList)){
			//将智能配货结果存入到缓存当中
			if(!ObjectUtils.isEmpty(orderPoList)){
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId, gson.toJson(orderPoList));
			}
			allocation.setDate(orderPoList);
			allocation.setCarInfo(new CarInfo());
			BaseVO vo = new BaseVO();
			vo.setTotal(result.getTotal());
			vo.setResult(allocation);
			return MsgTemplate.successMsg(vo);
		}else{
			redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId);
			BaseVO vo = new BaseVO();
			vo.setTotal(0);
			vo.setResult(null);
			return MsgTemplate.successMsg(vo);
		}
	}

	@Override
	public Map<String, Object> verifyAllocation(VerifyAllocationBO param) {
		try {
			//确认配货,确认优化公共锁
			Boolean setnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 
					"上锁", AllocationConstant.REDIS_LOCK_TIME);
			if(setnx){
				//同时确认配货锁
				Boolean allocaSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId(), 
						"上锁", AllocationConstant.REDIS_LOCK_TIME);
				if(allocaSetnx){
					return verifyAllocationSon(param);
				}else{
					//删除确认配货,确认优化公共锁
					redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
					return MsgTemplate.failureMsg(AllocationMsgEnum.VERIFY_ALLOCATION_ERROR);
				}
			}else{
				while(true){
					//确认配货,确认优化公共锁
					Boolean againSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 
							"上锁", AllocationConstant.REDIS_LOCK_TIME);
					if(againSetnx){
						//同时确认配货锁
						Boolean againAllocaSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId(), 
								"上锁", AllocationConstant.REDIS_LOCK_TIME);
						if(againAllocaSetnx){
							Map<String, Object> map = verifyAllocationSon(param);
							return map;
						}else{
							//删除确认配货,确认优化公共锁
							redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
							return MsgTemplate.failureMsg(AllocationMsgEnum.VERIFY_ALLOCATION_ERROR);
						}
					}
					//休息一秒,再进行下一次循环
					LOGGER.info("============verifyAllocation方法,等待锁循环中===========");
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	}

	/**
	 * 确认配货执行逻辑方法
	 * @param param
	 * @return
	 * @throws InterruptedException 
	 */
	private Map<String, Object> verifyAllocationSon(VerifyAllocationBO param) throws InterruptedException {
		//没有参与确认配货的订单直接驳回
		List<SequenceBO> seqOrderIds = param.getOrderIds();
		if(ObjectUtils.isEmpty(seqOrderIds)){
			return MsgTemplate.failureMsg(AllocationMsgEnum.NO_HAVING_ORDER_ALLOCATION);
		}
		//确认配货之前先校验该智能配货结果是否已配货
		HttpResult existHttpResult = allocationServer.existIntelligentAlloca(param.getAllocationId());
		if(!ObjectUtils.isEmpty(existHttpResult.getData())){
			String flag = (String)existHttpResult.getData();
			if(flag.equals(AllocationConstant.ALLOCATION_EFFECT)){
				//删除同时确认配货锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				return MsgTemplate.failureMsg(AllocationMsgEnum.ALREADY_INTELLIGENT_ALLOCATION);
			}
		}
		
		//============================================伪代码要删除
		HttpResult talbeResult =  allocationServer.getDeliveryTableId(param);
		if(!ObjectUtils.isEmpty(talbeResult.getData())){
			JsonObject asJsonObject = new JsonParser().parse(gson.toJson(talbeResult.getData())).getAsJsonObject();
			String loadingTableId = asJsonObject.get("loadingTableId").getAsString();
			String loadingTableName = asJsonObject.get("loadingTableName").getAsString();
			JsonElement jsonElement = asJsonObject.get("pickerId");
			if(jsonElement==null){
				param.setPickerId(param.getPickerId());
			}else{
				//删除同时确认配货锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				return MsgTemplate.failureMsg("当前无可用提货员,请等待");
			}
			
			if(StringUtils.isEmpty(loadingTableId) || StringUtils.isEmpty(loadingTableId)){
				//删除同时确认配货锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				return MsgTemplate.failureMsg("请绑定装车台账号");
			}
			param.setLoadingTableId(loadingTableId);
			param.setLoadingTableName(loadingTableName);
		}else{
			//删除同时确认配货锁
			redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
			//释放同时确认配货,确认优化公共锁
			redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
			return MsgTemplate.failureMsg("请检查是否有可用的装车台或检查装车台是否已绑定装车台账号");
		}
		//============================================伪代码要删除
		
		List<String> orderIds = new ArrayList<>();
		List<SequenceBO> sequenceList = param.getOrderIds();
		//取出所有的订单号
		for (SequenceBO sequenceBO : sequenceList) {
			BeanUtils.copyProperties(param, sequenceBO);
			orderIds.add(sequenceBO.getOrderId());
		}
		
		//配货之前先确认订单状态是否已配货，此处订单状态必须为22已入库状态,否的话直接全部驳回
		BatchOrderIdListBO batchOrderIdList = new BatchOrderIdListBO();
		batchOrderIdList.setKeyArea(param.getPartnerArea());
		batchOrderIdList.setOrderIds(orderIds);
		HttpResult orderResult =  orderServer.getOrderDeatilByIdList(batchOrderIdList);
	    BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(orderResult.getData()),BatchOrderDetailListPO.class);
        List<WarehouseOrderDetailPO> sonOrderList = batchOrderDetailListPO.getOrderList();
        List<WarehouseOrderDetailPO> joinOrderParamInfo = null;
        if(!ObjectUtils.isEmpty(sonOrderList)){
        	joinOrderParamInfo =  orderServer.joinOrderParamInfo(sonOrderList);
        }
        List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
        if(!ObjectUtils.isEmpty(splitOrderList)){
        	 if(!ObjectUtils.isEmpty(joinOrderParamInfo)){
             	joinOrderParamInfo.addAll(orderServer.joinOrderParamInfo(splitOrderList));
             }else{
            	 joinOrderParamInfo =  orderServer.joinOrderParamInfo(splitOrderList);
             }
        }
		for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
			Integer orderStatus = warehouseOrderDetailPO.getOrderStatus();
			if(!OrderStatusTypeEnum.ALL_ADD_STOCK.getValue().equals(String.valueOf(orderStatus))){
				String error = warehouseOrderDetailPO.getOrderId();
				//删除同时确认配货锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				String msg = new StringBuffer().append(error).append(":").append(AllocationMsgEnum.AGAIN_CHOOSE_ORDER.getMsg()).toString();
				return MsgTemplate.failureMsg(msg);
			}
		}
				
		//TODO 将所有的提货单id，和提货单号，以及指定的车辆信息id传递给TMS服务,返回成功后才能继续
		//TODO 修改提货员和装车员的状态,修改装车台状态
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
				update.setStatus(Integer.valueOf(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION.getValue()));
				update.setDeliveryId(param.getDeliveryId());
				update.setWaybillId(param.getWaybillId());
				update.setOrderId(string);
				update.setPlateNumber(param.getPlateNumber());
				update.setPartnerId(param.getPartnerId());
				updateList.add(update);
			}
			//修改冗余表订单状态
			HttpResult updateOrderRedunResult = allocationServer.batchUpdateOrderRedun(updateList);
			if(updateOrderRedunResult.isSuccess()){
				//TODO 最后通知提货员和装车台账户
				PushExtraFieldBO pushExtraFieldBO = new PushExtraFieldBO();
				pushExtraFieldBO.setUserId(param.getPickerId());
				pushExtraFieldBO.setOpenType(AppConstant.PUSH_OPEN_TYPE_DELIVERY);
				PushMsgBO push = new PushMsgBO();
				push.setUserid(param.getPickerId());
				push.setAppSystem(AppConstant.WMS);
				push.setMid(param.getDeliveryId());
				push.setType(AllocationConstant.PUSH_DELIVERY_TYPE);
				push.setTitle(AllocationConstant.PUSH_DELIVERY_TITLE);
				push.setText(AllocationConstant.PUSH_DELIVERY_TEXT);
				push.setExtraField(pushExtraFieldBO);
				//消息推送
				String json = gson.toJson(push);
				appProducer.sendPushMsg(json);
				
				//修改订单的订单状态，修改成已配货
				List<String> orderList = new ArrayList<>();
				List<OrderIdBO> orderIdBOList = new ArrayList<>();
				for(int i=0;i<orderIds.size();i++){
					//判断修改成功才能继续往下走(这里需要批量修改)
					orderList.add(orderIds.get(i));
					OrderIdBO order = new OrderIdBO();
					order.setOrderId(orderIds.get(i));
					order.setStatus(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION.getValue());
					orderIdBOList.add(order);
				}
				HttpResult updateResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
				if(!updateResult.isSuccess()){
					LOGGER.error("智能配货,确认配货修改订单状态失败!!!");
					return MsgTemplate.failureMsg("智能配货,确认配货修改订单状态失败!!!");
				}
				//比较订单状态大小
				Boolean flag = orderServer.compareOrderStatus(orderList,param.getPartnerArea(),param.getPartnerId());
				if(flag==false){
					return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
				}
				
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+param.getAllocationId());
				//删除同时确认配货锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
				//删除确认优化和确认配货公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				return MsgTemplate.customMsg(updateOrderRedunResult);
			}
		}
		//删除同时确认配货锁
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.VERIFY_ALLOCATION+param.getAllocationId());
		//删除确认优化和确认配货公共锁
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
		return MsgTemplate.customMsg(result);
		
	}
	
	@Override
	public Map<String, Object> moveOrder(MoveOrderPO param) {
		String allocationId = param.getAllocationId();
		//配货中移除订单flag为0,配货管理flag为1
		if(param.getFlag().equals(AllocationConstant.ALLOCATION_REMOVE_ORDER)){
			HttpResult result = allocationServer.allocationMoveOrder(param);
			return MsgTemplate.customMsg(result);
		}else if(param.getFlag().equals(AllocationConstant.ALLOCATION_MANAGEMENT_REMOVE_ORDER)){
			String waybillId = param.getWaybillId();
			//装车优化界面移除订单缓存
			String string = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
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
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId, gson.toJson(orderIds));
			}else{
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId, gson.toJson(param.getOrderIds()));
			}
		}
		return MsgTemplate.successMsg(); 
	}
	
	@Override
	public Map<String, Object> getAddOrderList(GetRedundantByAttributeBO param) {
		List<String> orderIdList = param.getOrderIdList();
		HttpResult result = null;
		List<String> redundantOrderList = null;
		if(ObjectUtils.isEmpty(orderIdList)){
			result = allocationServer.getAddOrderListByParamisNull(param);
		}else{
			//key和value都是订单号
			Map<String,String> orderStrMap = new HashMap<>(16);
			List<OrderIdBO> orderIdBOList = new ArrayList<>();
			for (String string : orderIdList) {
				if(string.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)!=-1){
					orderStrMap.put(string, string);
					String newStr = string.substring(0, string.indexOf(LoadingTaskConstant.SUBSTRING_ORDER));
					OrderIdBO order = new OrderIdBO();
					order.setKeyArea(param.getPartnerArea());
					order.setOrderId(newStr);
					orderIdBOList.add(order);
				}
			}
			
			if(!ObjectUtils.isEmpty(orderIdBOList)){
				//根据订单号批量查询拆单信息
				HttpResult splitOrderResult = orderServer.getSplitOrderDeatilByIdList(orderIdBOList);
				Map<String,List<WarehouseOrderDetailPO>> orderMap = dataFormatGson.fromJson(gson.toJson(splitOrderResult.getData()), new TypeToken<Map<String, List<WarehouseOrderDetailPO>>>() {}.getType());
				for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:orderMap.entrySet()){
					List<WarehouseOrderDetailPO> value = entry.getValue();
					Iterator<WarehouseOrderDetailPO> iterator = value.iterator();
					while(iterator.hasNext()){
						WarehouseOrderDetailPO next = iterator.next();
						String subOrderId = orderStrMap.get(next.getSubOrderId());
						if(subOrderId!=null){
							iterator.remove();
						}
					}
				}
				
				Map<String, List<WarehouseOrderDetailPO>> newOrderMap = new HashMap<>(16);
				for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:orderMap.entrySet()){
					List<WarehouseOrderDetailPO> value = entry.getValue();
					newOrderMap.put(entry.getKey(),new ArrayList<>());
					for (WarehouseOrderDetailPO warehouseOrderDetailPO : value) {
						Integer subStatus = warehouseOrderDetailPO.getSubStatus();
						if(subStatus.equals(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()))){
							newOrderMap.get(entry.getKey()).add(warehouseOrderDetailPO);
						}
					}
				}
				
				//判断newOrderMap value中的List的值,大小等于0则表示需要添加到orderIdList中的数据
				for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:newOrderMap.entrySet()){
					List<WarehouseOrderDetailPO> value = entry.getValue();
					if(ObjectUtils.isEmpty(value)){
						orderIdList.add(entry.getKey());
					}
				}
			}
			result = allocationServer.getErrorAddOrderList(param);
			redundantOrderList = gson.fromJson(gson.toJson(result.getData()),ArrayList.class);
			List<String> newRedundantOrderList = new ArrayList<>();
			for (String string : redundantOrderList) {
				if(string.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)==-1){
					newRedundantOrderList.add(string);
				}
			}
			if(ObjectUtils.isEmpty(newRedundantOrderList)){
				newRedundantOrderList = redundantOrderList;
			}
			param.setOrderIdList(newRedundantOrderList);
			result = allocationServer.getAddOrderList(param);
		}
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>(16);
		List<WarehouseOrderDetailPO> orderDetailList = new ArrayList<WarehouseOrderDetailPO>();
		if(!ObjectUtils.isEmpty(result.getData())){
			BaseVO base = gson.fromJson(gson.toJson(result.getData()), BaseVO.class);
			redundantOrderList = gson.fromJson(gson.toJson(base.getResult()),ArrayList.class);
			BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
	        batchOrder.setKeyArea(param.getPartnerArea());
	        batchOrder.setOrderIds(redundantOrderList);
	        //根据订单号批量查询订单详情信息
	        result = orderServer.getOrderDeatilByIdList(batchOrder);
	        BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(result.getData()),BatchOrderDetailListPO.class);
	        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(batchOrderDetailListPO.getOrderList());
	        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
				map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
				orderDetailList.add(warehouseOrderDetailPO);
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
			List<WarehouseOrderDetailPO> stockInfo = orderServer.getOrderStockInfo(selectAreaByOrderId);
			for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
				String orderId = warehouseOrderDetailPO.getOrderId();
				WarehouseOrderDetailPO orderDetail = map.get(orderId);
				if(orderDetail!=null){
					orderDetail.setAreaList(warehouseOrderDetailPO.getAreaList());
					orderDetail.setWarehouseId(warehouseOrderDetailPO.getWarehouseId());
					orderDetail.setWarehouseName(warehouseOrderDetailPO.getWarehouseName());
				}
			}
			
			List<WarehouseOrderDetailPO> detailList = new ArrayList<>();
			for(Map.Entry<String,WarehouseOrderDetailPO> entry : map.entrySet()){
				detailList.add(entry.getValue());
			}
			base.setResult(detailList);
			return MsgTemplate.successMsg(base);
			
		}else{
			BaseVO base = new BaseVO();
			base.setTotal(0);
			base.setResult(null);
			return MsgTemplate.successMsg(base);
		}
	}

	@Override
	public Map<String, Object> verifyAddOrder(List<AddAllocationOrderBO> param) {
		//TODO 智能配货判断车辆装载率
		String allocationId = param.get(0).getAllocationId();
		//已进行智能配货的订单无法在添加
		String allocationOrder = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+allocationId);
		List<OrderPO> allocationOrderList = gson.fromJson(allocationOrder, new TypeToken<ArrayList<OrderPO>>(){}.getType());
		Map<String,OrderPO> orderPOMap = new HashMap<>(16);
		for (OrderPO orderPO : allocationOrderList) {
			orderPOMap.put(orderPO.getOrderId(), orderPO);
		}
		List<String> errrOrderIdList = new ArrayList<>();
		for (AddAllocationOrderBO addAllocationOrderBO : param) {
			OrderPO orderPO = orderPOMap.get(addAllocationOrderBO.getOrderId());
			if(orderPO!=null){
				errrOrderIdList.add(orderPO.getOrderId());
			}
		}
		if(!ObjectUtils.isEmpty(errrOrderIdList)){
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("以下订单已参与智能配货,请重新选择:");
			for(int i =0;i<errrOrderIdList.size();i++){
				if(i==errrOrderIdList.size()-1){
					stringBuffer.append(errrOrderIdList.get(i));
				}else{
					stringBuffer.append(errrOrderIdList.get(i)).append(",");
				}
			}
			return MsgTemplate.failureMsg(stringBuffer.toString());
		}
		for (AddAllocationOrderBO addAllocationOrderBO : param) {
			addAllocationOrderBO.setDeliveryAmount(addAllocationOrderBO.getOrderAmount());
			addAllocationOrderBO.setDeliveryIdEffect(Integer.valueOf(AllocationConstant.DELIVERY_UNEFFEFT));
			//=====================这里前端不需要传提货单号和提货单确认状态=====
			addAllocationOrderBO.setDeliveryId(null);
			//=====================这里前端不需要传提货单号和提货单确认状态=====
		}
		HttpResult result = allocationServer.verifyAddOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAllocationManageList(GetRedundantByAttributeBO param) {
		List<GetAllocationManageListPO> allocationManageList = null;
		List<String> waybillList = new ArrayList<String>();
		//key为运单号
		Map<String,GetAllocationManageListPO> map = new HashMap<String,GetAllocationManageListPO>(16);
		int total = 0;
		// 查询标记,flag为0,则表示没有查询条件,为1表中有查询条件
		if(AllocationConstant.UNHAVE_QUERY_CONDITION.equals(param.getFlag())){
			//分页查询运单表
			OtherHttpResult result = allocationServer.getAlloManageQuery(param);
			BaseVO vo = new BaseVO();
			vo.setTotal(result.getTotal());
			vo.setResult(result.getData());
			return MsgTemplate.successMsg(vo);
		}else{
			//查询标记为1表中有查询条件
			OtherHttpResult result = allocationServer.getAlloManageFuzzyQuery(param);
			BaseVO vo = new BaseVO();
			vo.setTotal(result.getTotal());
			vo.setResult(result.getData());
			return MsgTemplate.successMsg(vo);
		}
	}

	@Override
	public Map<String, Object> getWaybillDetailByWayId(GetDeliveryByWaybillIdsBO param) {
		Map<String,WarehouseOrderDetailPO> map = new HashMap<>(16);
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
				List<WarehouseOrderDetailPO> fromJson = gson.fromJson(gson.toJson(orderResult.getData()), new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : fromJson) {
					String orderId = warehouseOrderDetailPO.getOrderId();
					orderIdsList.add(orderId);
					map.put(orderId, warehouseOrderDetailPO);
				}
			}else{
				return MsgTemplate.successMsg();
			}
			
			BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
	        batchOrder.setKeyArea(param.getPartnerArea());
	        batchOrder.setOrderIds(orderIdsList);
	        //根据订单号批量查询订单详情信息
	        HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
	        BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
	        
	        if(!ObjectUtils.isEmpty(batchOrderDetailListPO.getOrderList())){
	        	List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
		        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
		        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
		        	WarehouseOrderDetailPO orderDetail = map.get(warehouseOrderDetailPO.getOrderId());
					BeanUtils.copyProperties(orderDetail, warehouseOrderDetailPO,"orderStatus","materialSize","productSize","fluteType","deliveryTime");
					warehouseOrderDetailList.add(warehouseOrderDetailPO);
				}
	        }
	        
	        if(!ObjectUtils.isEmpty(batchOrderDetailListPO.getSplitOrderList())){
	        	List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getSplitOrderList();
	        	 List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
			        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
			        	WarehouseOrderDetailPO orderDetail = map.get(warehouseOrderDetailPO.getOrderId());
						BeanUtils.copyProperties(orderDetail, warehouseOrderDetailPO,"orderStatus","materialSize","productSize","fluteType","deliveryTime");
						warehouseOrderDetailList.add(warehouseOrderDetailPO);
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
	            if (warehouseOrderDetailList.get(j).getSequence() < warehouseOrderDetailList.get(minIndex).getSequence()) {
	                minIndex = j;
	            }
	        }
	        temp = warehouseOrderDetailList.get(i);
	        warehouseOrderDetailList.set(i, warehouseOrderDetailList.get(minIndex));
	        warehouseOrderDetailList.set(minIndex,temp);
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
		String removeStr = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
		//获取追加订单的缓存
		String addOrderStr = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		
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
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId,deliveryId);
				waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
				waybillDeliveryOrder.setAllocationId(waybillDeliveryOrder.getDeliveryOrder().get(0).getOrders().get(0).getAllocationId());
				List<DeliveryOrderPO> deliveryOrderList = waybillDeliveryOrder.getDeliveryOrder();
				List<OrderPO> againOrderList = deliveryOrderList.get(deliveryOrderList.size()-1).getOrders();
				Integer sequence =  againOrderList.get(againOrderList.size()-1).getSequence()+1;
				
				if(!ObjectUtils.isEmpty(deliveryOrderList)){
					String allocationId = deliveryOrderList.get(0).getOrders().get(0).getAllocationId();
					//装车优化,缓存追加订单,组织参数处理公共方法
					List<OrderPO> orderList = getOrderPOList(sequence ,addOrder, param,deliveryId,allocationId);
					DeliveryOrderPO deliveryOrder = new DeliveryOrderPO();
					BeanUtils.copyProperties(deliveryOrderList.get(0),deliveryOrder);
					deliveryOrder.setDeliveryId(deliveryId);
					deliveryOrder.setOrders(orderList);
					deliveryOrderList.add(deliveryOrder);
				}
			}
		}
		
		if(!ObjectUtils.isEmpty(removeOrder)){
			if(waybillDeliveryOrder==null){
				waybillDeliveryOrder = gson.fromJson(gson.toJson(result.getData()),WaybillDeliveryOrderPO.class);
				waybillDeliveryOrder.setAllocationId(waybillDeliveryOrder.getDeliveryOrder().get(0).getOrders().get(0).getAllocationId());
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
			waybillDeliveryOrder.setAllocationId(waybillDeliveryOrder.getDeliveryOrder().get(0).getOrders().get(0).getAllocationId());
			List<DeliveryOrderPO> deliveryList = waybillDeliveryOrder.getDeliveryOrder();
			for (DeliveryOrderPO deliveryOrderPO : deliveryList) {
				List<OrderPO> orders = deliveryOrderPO.getOrders();
				for (OrderPO orderPO : orders) {
					orderPO.setDeliveryAmount(orderPO.getOrderAmount());
				}
			}
		}
		
		//统一根据订单号获取订单状态,不管上面代码是否已经获取订单状态,这里统一从订单服务获取
		//key是订单号,value是订单状态
		Map<String,WarehouseOrderDetailPO> orderStatusMap = new HashMap<>(16);
		List<String> orderIdsList = new ArrayList<>();
		List<DeliveryOrderPO> newDeliveryList = waybillDeliveryOrder.getDeliveryOrder();
		//这个值为空表示所有的订单都被移除了
		if(ObjectUtils.isEmpty(newDeliveryList)){
			 return MsgTemplate.successMsg(waybillDeliveryOrder);
		}
		for (DeliveryOrderPO deliveryOrderPO : newDeliveryList) {
			List<OrderPO> newOrderList = deliveryOrderPO.getOrders();
			for (OrderPO orderPO : newOrderList) {
				String orderId = orderPO.getOrderId();
				orderIdsList.add(orderId);
			}
		}
		
		//根据订单号批量查询订单详情信息,把订单状态和产品规格和材料规格赋值上去
		BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
		batchOrder.setKeyArea(param.getPartnerArea());
		batchOrder.setOrderIds(orderIdsList);
		HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
		BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
		List<WarehouseOrderDetailPO> detailList = batchOrderDetailListPO.getOrderList();
		List<WarehouseOrderDetailPO> joinOrderParamInfo = null;
		if(!ObjectUtils.isEmpty(detailList)){
			joinOrderParamInfo = orderServer.joinOrderParamInfo(detailList);
		}
		
		List<WarehouseOrderDetailPO> splitOrderList =  batchOrderDetailListPO.getSplitOrderList();
		if(!ObjectUtils.isEmpty(splitOrderList)){
			if(!ObjectUtils.isEmpty(joinOrderParamInfo)){
				joinOrderParamInfo.addAll(orderServer.joinOrderParamInfo(splitOrderList));
			}else{
				joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
			}
		}
		for (WarehouseOrderDetailPO orderDeatil : joinOrderParamInfo) {
			orderStatusMap.put(orderDeatil.getOrderId(), orderDeatil);
		}
		//再次遍历原数据,将订单状态赋值上去
		for (DeliveryOrderPO deliveryOrderPO : newDeliveryList) {
			List<OrderPO> newOrderList = deliveryOrderPO.getOrders();
			for (OrderPO orderPO : newOrderList) {
				orderPO.setOrderStatus(orderStatusMap.get(orderPO.getOrderId()).getOrderStatus());
				orderPO.setProductSize(orderStatusMap.get(orderPO.getOrderId()).getProductSize());
			}
		}
		
		int size = 0;
		List<OrderPO> orderList = new ArrayList<>();
		List<DeliveryOrderPO> deliveryList = waybillDeliveryOrder.getDeliveryOrder();
		Map<String,DeliveryOrderPO> map = new LinkedHashMap<>();
		for (DeliveryOrderPO deliveryOrderPO : deliveryList) {
			List<OrderPO> list = deliveryOrderPO.getOrders();
			map.put(deliveryOrderPO.getDeliveryId(), deliveryOrderPO);
			size = size+list.size();
			for (OrderPO orderPO : list) {
				orderList.add(orderPO);
			}
			deliveryOrderPO.setOrders(new ArrayList<>());
		}
		//对数据进行排序,选择排序法
		int minIndex ;
		OrderPO temp =null;
	    for (int i = 0; i < size - 1; i++) {
	        minIndex = i;
	        for (int j = i + 1; j < size; j++) {
	            if (orderList.get(j).getSequence() < orderList.get(minIndex).getSequence()) {
	                minIndex = j;
	            }
	        }
	        temp = orderList.get(i);
	        orderList.set(i, orderList.get(minIndex));
	        orderList.set(minIndex,temp);
	    }
	    for (OrderPO order : orderList) {
	    	String deliveryId = order.getDeliveryId();
	    	DeliveryOrderPO deliveryOrderPO = map.get(deliveryId);
    		deliveryOrderPO.getOrders().add(order);
	    }
	    int i =0;
	    for(Map.Entry<String,DeliveryOrderPO> entry:map.entrySet()){
	    	DeliveryOrderPO value = entry.getValue();
	    	waybillDeliveryOrder.getDeliveryOrder().set(i, value);
	    	i++;
	    }
	    //删除同时确认优化锁
	    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
	    return MsgTemplate.successMsg(waybillDeliveryOrder);
	}
	
	/**
	 * 装车优化,缓存追加订单,组织参数处理公共方法
	 * @return
	 */
	private List<OrderPO> getOrderPOList(Integer sequence,List<String> orderIdsList,GetExcellentLodingBO param,String deliveryId,String allocationId){
		//String是订单号,map用来存在库信息查询出来的数据
		Map<String,WarehouseOrderDetailPO> map = new HashMap<String,WarehouseOrderDetailPO>(16);
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
		List<WarehouseOrderDetailPO> stockInfo = orderServer.getOrderStockInfo(selectArea);
		for (WarehouseOrderDetailPO warehouseOrderDetailPO : stockInfo) {
			//将在库信息存入根据订单号存入到map当中
			map.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
		}
		
		//根据订单号批量查询订单详情信息
		BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
		batchOrder.setKeyArea(param.getPartnerArea());
		batchOrder.setOrderIds(orderIdsList);
		HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
		BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
		List<WarehouseOrderDetailPO> detailList = batchOrderDetailListPO.getOrderList();
		List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
		List<WarehouseOrderDetailPO> joinOrderParamInfo = null;
		if(!ObjectUtils.isEmpty(detailList)){
			joinOrderParamInfo = orderServer.joinOrderParamInfo(detailList);
		}
		if(!ObjectUtils.isEmpty(splitOrderList)){
			if(!ObjectUtils.isEmpty(joinOrderParamInfo)){
				joinOrderParamInfo.addAll(orderServer.joinOrderParamInfo(splitOrderList));
			}else{
				joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
			}
		}
		
		for (WarehouseOrderDetailPO orderDetail : joinOrderParamInfo) {
			WarehouseOrderDetailPO warehouseDetail = map.get(orderDetail.getOrderId());
			BeanUtils.copyProperties(orderDetail, warehouseDetail,"amountSaved","remark","instockAmount","areaList","warehouseId","warehouseName");
		}
		
		List<OrderPO> orderList = new ArrayList<>();
		for(Map.Entry<String,WarehouseOrderDetailPO> entry : map.entrySet()){
			//组织装车优化需要的参数
			WarehouseOrderDetailPO value = entry.getValue();
			OrderPO orderPO = new OrderPO();
			orderPO.setDeliveryAmount(value.getOrderAmount());
			BeanUtils.copyProperties(value, orderPO);
			WarehousePO warehouse = new WarehousePO();
			BeanUtils.copyProperties(value, warehouse);
			orderPO.setWarehouse(warehouse);
			if(!StringUtils.isEmpty(value.getDeliveryTime())){
				orderPO.setDeliveryTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value.getDeliveryTime()));
			}
			orderPO.setAddress(value.getAddressDetailProvince());
			orderPO.setSequence(sequence);
			orderPO.setContacts(value.getConsignee());
			orderPO.setAllocationId(allocationId);
			orderPO.setDeliveryId(deliveryId);
			sequence++;
			orderList.add(orderPO);
		}
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
		//修改提货单确认状态修改为feffect为2
		param.setDeliveryIdEffect(AllocationConstant.DELIVERY_UNEFFEFT);
		//修改订单表中订单状态为已入库
		param.setOrderStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
		//修改配货表的确认状态为2
		param.setAllocationIdEffect(AllocationConstant.ALLOCATION_UNEFFECT);
		HttpResult result = allocationServer.cancelAllocation(param);
		if(result.isSuccess()){
			//修改订单的订单状态，修改成已入库
			List<String> orderIds = param.getOrderIds();
			List<OrderIdBO> orderIdList = new ArrayList<>();
			for (String string : orderIds) {
				OrderIdBO order = new OrderIdBO();
				order.setOrderId(string);
				order.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
				orderIdList.add(order);
			}
			HttpResult updateResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(), orderIdList);
			if(updateResult.isSuccess()){
				Boolean flag = orderServer.compareOrderStatus(orderIds, param.getPartnerArea(),param.getPartnerId());
				if(flag==false){
					return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
				}
			}else{
				LOGGER.error("------更换车辆,取消配货,修改订单状态失败------");
				return MsgTemplate.customMsg(updateResult);
			}
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addzhinengpeihuo(BaseAddBO base) {
		List<WarehouseOrderDetailPO> list = new ArrayList<>();
		Map<String,WarehouseOrderDetailPO> map = new HashMap<>(16);
		//获取所有的在库信息id
		HttpResult result = null;
		result = allocationServer.addzhinengpeihuo(base);
		Object data = result.getData();
		if(ObjectUtils.isEmpty(data)){
			return MsgTemplate.failureMsg("没有已入库的订单所有就没有插入的数据");
		}
		JsonArray asJsonArray = jsonParser.parse(gson.toJson(data)).getAsJsonArray();
		List<String> orderIdsList =new  ArrayList<>();
		List<OrderIdBO> orderIdBOList = new ArrayList<>();
		for (JsonElement jsonElement : asJsonArray) {
			String orderId = jsonElement.getAsJsonObject().get("orderId").getAsString();
			orderIdsList.add(orderId);
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(orderId);
			orderIdBOList.add(orderIdBO);
		}
		OrderIdsBO param = new OrderIdsBO();
		param.setChildOrderIds(orderIdsList);
		result = orderServer.getOrderByOrderIds(param);
		
		BatchOrderIdListBO batch = new BatchOrderIdListBO();
		batch.setOrderIds(orderIdsList);
		batch.setKeyArea(base.getPartnerArea());
		HttpResult orderResult = orderServer.getOrderDeatilByIdList(batch);
		BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(orderResult.getData()),BatchOrderDetailListPO.class);
		
		List<WarehouseOrderDetailPO> orderList =null;
		if(!ObjectUtils.isEmpty(batchOrderDetailListPO.getOrderList())){
			orderList = orderServer.joinOrderParamInfo(batchOrderDetailListPO.getOrderList());
		}
		if(!ObjectUtils.isEmpty(batchOrderDetailListPO.getSplitOrderList())){
			if(!ObjectUtils.isEmpty(orderList)){
				orderList.addAll(orderServer.joinOrderParamInfo(batchOrderDetailListPO.getSplitOrderList()));
			}else{
				orderList = orderServer.joinOrderParamInfo(batchOrderDetailListPO.getSplitOrderList());
			}
		}
		String uuid = UUID.randomUUID().toString();
		AddExcellentAllocationBO allocationBO = null;
		if(!ObjectUtils.isEmpty(orderList)){
			for(int i=0;i<orderList.size();i++){
				WarehouseOrderDetailPO orderDetail = orderList.get(i);
				//将订单信息根据订单号存入到map中
				map.put(orderDetail.getOrderId(), orderDetail);
			}
			
			SelectAreaByOrderIdBO selectArea = new SelectAreaByOrderIdBO();
			selectArea.setOrderIds(orderIdBOList);
			BeanUtils.copyProperties(base, selectArea);
			List<WarehouseOrderDetailPO> stockInfo = orderServer.getOrderStockInfo(selectArea);
			//key订单号,value为trueAmount实时在库数量
			Map<String,Integer> trueAmountMap = new HashMap<>(16);
			for (WarehouseOrderDetailPO orderDetailPO : stockInfo) {
				List<WarehouseAreaBO> areaList = orderDetailPO.getAreaList();
				for (WarehouseAreaBO areaBO : areaList) {
					List<WarehouseLocationBO> locationList = areaBO.getLocationList();
					for (WarehouseLocationBO locationBO : locationList) {
						trueAmountMap.put(orderDetailPO.getOrderId(), locationBO.getTrueAmount());
					}
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
				orderDetail.setDeliveryAmount(trueAmountMap.get(orderDetail.getOrderId()));
				BeanUtils.copyProperties(orderDetail, fromJson);
				String address = orderDetail.getCodeProvince()+orderDetail.getAddressDetail();
				fromJson.setAddressDetail(address);
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
		return MsgTemplate.successMsg(allocationBO);
	}

	@Override
	public Map<String, Object> getPicker(BaseAddBO param) {
		PickerPO picker2 = new PickerPO(param.getOperatorId(),param.getOperator(),"15157780633","空闲");
		PickerPO picker3 = new PickerPO("81","Admin","1000000","空闲");
		List<PickerPO> list = new ArrayList<>();
		list.add(picker2);
		list.add(picker3);
		return MsgTemplate.successMsg(list);
	}

	@Override
	public Map<String, Object> getLoadingPerson() {
		LoadingPersonPO picker1 = new LoadingPersonPO("1000933","郑天伟","15157780633","空闲");
		List<LoadingPersonPO> list = new ArrayList<>();
		list.add(picker1);
		return MsgTemplate.successMsg(list);
	}

	@Override
	public Map<String, Object> againVerifyAllocation(MergeModelBO param, PartnerInfoBO partnerInfoBean) {
		//确认配货,确认优化公共锁
		Boolean setnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 
				"上锁", AllocationConstant.REDIS_LOCK_TIME);
		if(setnx){
			//同时确认配货锁
			Boolean waybillSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+param.getWaybillId(), 
					"上锁", AllocationConstant.REDIS_LOCK_TIME);
			if(waybillSetnx){
				//确认配货执行逻辑方法
				return againVerifyAllocationSon(param,partnerInfoBean);
			}else{
				//删除确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				return MsgTemplate.failureMsg(AllocationMsgEnum.AGAIN_VERIFY_ALLOCATION_ERROR);
			}
		}else{
			try {
				while(true){
					//确认配货,确认优化公共锁
					Boolean againSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId(), 
							"上锁", AllocationConstant.REDIS_LOCK_TIME);
					if(againSetnx){
						//同时确认配货锁
						Boolean againWaybillSetnx = RedisUtil.setnx(redisClient, RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+param.getWaybillId(), 
								"上锁", AllocationConstant.REDIS_LOCK_TIME);
						if(againWaybillSetnx){
							//确认配货执行逻辑方法
							return againVerifyAllocationSon(param,partnerInfoBean);
						}else{
							//删除确认配货,确认优化公共锁
							redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
							return MsgTemplate.failureMsg(AllocationMsgEnum.AGAIN_VERIFY_ALLOCATION_ERROR);
						}
					}
					//休息一秒,再进行下一次循环
					LOGGER.info("============againVerifyAllocation方法,等待锁循环中===========");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
	}
	
	/**
	 * 装车优化确认配货执行逻辑方法
	 * @param param
	 * @param partnerInfoBean
	 * @return
	 */
	private Map<String, Object> againVerifyAllocationSon(MergeModelBO param, PartnerInfoBO partnerInfoBean){
		String waybillId = param.getWaybillId();
		//校验数据的一致性
		GetDeliveryByWaybillIdsBO waybillDeatil = new GetDeliveryByWaybillIdsBO();
		BeanUtils.copyProperties(param, waybillDeatil);
		List<String> stringList = new ArrayList<>();
		stringList.add(param.getWaybillId());
		waybillDeatil.setWaybillIds(stringList);
		Map<String, Object> waybillDetailByWayId = getWaybillDetailByWayId(waybillDeatil);
		List<WarehouseOrderDetailPO> orderDetailList = (List<WarehouseOrderDetailPO>) waybillDetailByWayId.get("data");
		//装车顺序为空
		if(ObjectUtils.isEmpty(param.getSequenceList())){
			//从缓存中取出要追加的订单
			String str = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			String string = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
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
						if(orderNext.equals(next.getOrderId())){
							orderIterator.remove();
						}
					}
				}
			}
			if(orderIdsList.size()!=orderDetailList.size()){
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				//删除同时确认优化锁
			    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
				return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
			}
			
			Iterator<String> reomoveIterator = orderIdsList.iterator();
			while(reomoveIterator.hasNext()){
				String strRemove = reomoveIterator.next();
				for (WarehouseOrderDetailPO orderDetailPO : orderDetailList) {
					if(orderDetailPO.getOrderId().equals(strRemove)){
						reomoveIterator.remove();
					}
				}
			}
			//不等于表示数据有问题
			if(orderIdsList.size()!=0){
				//释放同时确认配货,确认优化公共锁
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
				//删除同时确认优化锁
			    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
				return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
			}
		}else{
			List<SequenceBO> addOrder = new ArrayList<>();
			//装车顺序map
			Map<String,SequenceBO> sequenceMap = new HashMap<>(16);
			//原数据map
			Map<String,WarehouseOrderDetailPO> formerMap = new HashMap<>(16);
			for(SequenceBO sequenceBO : param.getSequenceList()){
				sequenceMap.put(sequenceBO.getOrderId(), sequenceBO);
			}
			//被移除的订单
			List<WarehouseOrderDetailPO> removeOrder = new ArrayList<>();
			if(!ObjectUtils.isEmpty(orderDetailList)) {
				for (WarehouseOrderDetailPO orderDetailPO : orderDetailList) {
					formerMap.put(orderDetailPO.getOrderId(), orderDetailPO);
					SequenceBO sequenceBO = sequenceMap.get(orderDetailPO.getOrderId());
					//等于null说明,原数据该订单被移除了,则存入新增订单号
					if(sequenceBO==null){
						removeOrder.add(orderDetailPO);
					}
				}
			}
			//新增的订单
			for(SequenceBO sequenceBO : param.getSequenceList()){
				WarehouseOrderDetailPO orderDetailPO = formerMap.get(sequenceBO.getOrderId());
				//等于null,说明订单是新增的订单
				if(orderDetailPO==null){
					addOrder.add(sequenceBO);
				}
			}
			//从缓存中取出要追加的订单
			String addStr = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			//从缓存中取出要移除的订单
			String removeStr = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
			List<WarehouseOrderDetailPO> addOrderList = gson.fromJson(addStr, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			List<String> reomoveOrderList = gson.fromJson(removeStr, List.class);
			if( !ObjectUtils.isEmpty(addOrderList) && (!ObjectUtils.isEmpty(reomoveOrderList)) ){
				Iterator<WarehouseOrderDetailPO> addIterator = addOrderList.iterator();
				while(addIterator.hasNext()){
					WarehouseOrderDetailPO add = addIterator.next();
					Iterator<String> removeIterator = reomoveOrderList.iterator();
					while(removeIterator.hasNext()){
						String remove = removeIterator.next();
						if(remove.equals(add.getOrderId())){
							removeIterator.remove();
							addIterator.remove();
						}
					}
				}
			}
			
			if(!ObjectUtils.isEmpty(addOrderList)){
				if(addOrderList.size()!=addOrder.size()){
					//释放同时确认配货,确认优化公共锁
					redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
					//删除同时确认优化锁
				    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
					return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
				}
				Iterator<WarehouseOrderDetailPO> addIterator = addOrderList.iterator();
				while(addIterator.hasNext()){
					WarehouseOrderDetailPO add = addIterator.next();
					for (SequenceBO sequenceBO : addOrder) {
						if(add.getOrderId().equals(sequenceBO.getOrderId())){
							addIterator.remove();
						}
						
					}
				}
				//不等于表示新增订单里还有数据
				if(addOrderList.size()!=0){
					//释放同时确认配货,确认优化公共锁
					redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
					//删除同时确认优化锁
				    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
					return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
				}
			}
			
			if(!ObjectUtils.isEmpty(reomoveOrderList)){
				if(reomoveOrderList.size()!=removeOrder.size()){
					//释放同时确认配货,确认优化公共锁
					redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
					//删除同时确认优化锁
				    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
					return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
				}
				Iterator<String> reomoveIterator = reomoveOrderList.iterator();
				while(reomoveIterator.hasNext()){
					String str = reomoveIterator.next();
					for (WarehouseOrderDetailPO orderDetailPO : removeOrder) {
						if(orderDetailPO.getOrderId().equals(str)){
							reomoveIterator.remove();
						}
					}
				}
				//不等于表示新增订单里还有数据
				if(reomoveOrderList.size()!=0){
					//释放同时确认配货,确认优化公共锁
					redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
					//删除同时确认优化锁
				    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
					return MsgTemplate.failureMsg(AllocationConstant.OPERATION_ERROR);
				}
			}
		}
		//装车优化确认配货合并移除订单,确认追加订单,确认配货
		//冗余表修改订单状态
		//修改提货单确认状态修改为feffect为1
		//修改配货订单表中订单的提货单号
		
		//整理装车顺序需要的数据
		HashMap<String,SequenceBO> map = new HashMap<>(16);
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
				allocation.setStatus(Integer.valueOf(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION.getValue()));
				allocation.setPlateNumber(param.getPlateNumber());
				allocation.setAllocationId(param.getAllocationId());
				againVerifyAllocation.add(allocation);
			}
			//修改装车顺序================
		}
		//配货管理追加订单==================
		//计算装载率,判断装载率是否超出,超出直接驳回
		//不超出,将订单数据存入订单表中,并重新生成提货单.提货员和装车员和原来的一致,装车顺序在原来的基础上递增
		
		//从缓存中取出要追加的订单
		List<String> orderIdList = new ArrayList<>();
		List<OrderIdBO> orderIdBOList = new ArrayList<>();
		String str = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
		if(!StringUtils.isEmpty(str)){
			List<WarehouseOrderDetailPO> cacheList = gson.fromJson(str, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			//获取移除订单的数据
			String string = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
			if(!StringUtils.isEmpty(string)){
				List<String> orderIdsList = gson.fromJson(string, List.class);
				//追加的订单和移除的订单需要进行比较,假如追加的订单又被移除了,则需要删除缓存中追加订单的数据
				for (String orderStr : orderIdsList) {
					Iterator<WarehouseOrderDetailPO> iterator = cacheList.iterator();
					while(iterator.hasNext()){
						WarehouseOrderDetailPO next = iterator.next();
						if(orderStr.equals(next.getOrderId())){
							iterator.remove();
						}
					}
				}
			}
			
			//已经经过筛选处理的追加订单
			if(!ObjectUtils.isEmpty(cacheList)){
				List<String> orderIds = new ArrayList<>();
				for (WarehouseOrderDetailPO orderDetailPO : cacheList) {
					orderIds.add(orderDetailPO.getOrderId());
				}
				
				//判断订单状态,此处订单状态必须为22已入库状态
				BatchOrderIdListBO batchOrderIdList = new BatchOrderIdListBO();
				batchOrderIdList.setKeyArea(param.getPartnerArea());
				batchOrderIdList.setOrderIds(orderIds);
				HttpResult orderResult =  orderServer.getOrderDeatilByIdList(batchOrderIdList);
			    BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(orderResult.getData()),BatchOrderDetailListPO.class);
		        List<WarehouseOrderDetailPO> sonOrderList = batchOrderDetailListPO.getOrderList();
		        List<WarehouseOrderDetailPO> joinOrderParamInfo = null;
		        if(!ObjectUtils.isEmpty(sonOrderList)){
		        	joinOrderParamInfo =  orderServer.joinOrderParamInfo(sonOrderList);
		        }
		        List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
		        if(!ObjectUtils.isEmpty(splitOrderList)){
		        	 if(!ObjectUtils.isEmpty(joinOrderParamInfo)){
		             	joinOrderParamInfo.addAll(orderServer.joinOrderParamInfo(splitOrderList));
		             }else{
		            	 joinOrderParamInfo =  orderServer.joinOrderParamInfo(splitOrderList);
		             }
		        }
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
					Integer orderStatus = warehouseOrderDetailPO.getOrderStatus();
					if(!OrderStatusTypeEnum.ALL_ADD_STOCK.getValue().equals(String.valueOf(orderStatus))){
						String error = warehouseOrderDetailPO.getOrderId();
						//释放同时确认配货,确认优化公共锁
						redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
						//删除同时确认优化锁
					    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
						String msg = new StringBuffer().append(error).append(":").append(AllocationMsgEnum.AGAIN_CHOOSE_ORDER.getMsg()).toString();
						return MsgTemplate.failureMsg(msg);
					}
				}
				
				String allocationId = param.getAllocationId();
				List<AddAllocationOrderBO> ordersList = new ArrayList<>();
				AgainVerifyAddOrderBO againVerifyAddOrder = new AgainVerifyAddOrderBO();
				param.setAgainVerifyAddOrder(againVerifyAddOrder);
				BeanUtils.copyProperties(partnerInfoBean,againVerifyAddOrder);
				BeanUtils.copyProperties(param,againVerifyAddOrder);
				//提货单做缓存
				String newDeliveryId = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId);
				againVerifyAddOrder.setDeliveryId(newDeliveryId);
				againVerifyAddOrder.setOrdersList(ordersList);
				againVerifyAddOrder.setDeliveryIdEffect(AllocationConstant.DELIVERY_EFFEFT);
				
				//组织新增订单所需的参数
				for (WarehouseOrderDetailPO detail : cacheList) {
					for (WarehouseAreaBO areaBO : detail.getAreaList()) {
						String areaId = areaBO.getWarehouseAreaId();
						String areaName = areaBO.getWarehouseAreaName();
						for (WarehouseLocationBO locationBO : areaBO.getLocationList()) {
							AddAllocationOrderBO order = new AddAllocationOrderBO();
							//组织追加订单需要的参数
							BeanUtils.copyProperties(detail, order);
							order.setAllocationId(allocationId);
							order.setContacts(detail.getConsignee());
							if(!StringUtils.isEmpty(detail.getDeliveryTime())){
								order.setDeliveryTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(detail.getDeliveryTime()));
							}
							SequenceBO sequenceBO = map.get(detail.getOrderId());
							order.setSequence(String.valueOf(sequenceBO.getSequence()));
							order.setAddress(detail.getAddressDetailProvince());
							order.setWarehouseId(detail.getWarehouseId());
							order.setWarehouseName(detail.getWarehouseName());
							order.setWarehouseAreaId(areaId);
							order.setWarehouseAreaName(areaName);
							order.setWarehouseLocId(locationBO.getWarehouseLocId());
							order.setWarehouseLocName(locationBO.getWarehouseLocName());
							order.setDeliveryAmount(detail.getOrderAmount());
							order.setDeliveryIdEffect(Integer.valueOf(AllocationConstant.DELIVERY_EFFEFT));
							order.setDeliveryId(newDeliveryId);
							ordersList.add(order);
						}
					}
				}
				//修改订单服务订单状态修改为已配货
				for (WarehouseOrderDetailPO orderDetail : cacheList) {
					//通知订单服务修改,需要批量执行
					orderIdList.add(orderDetail.getOrderId());
					OrderIdBO order = new OrderIdBO();
					order.setOrderId(orderDetail.getOrderId());
					order.setStatus(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION.getValue());
					orderIdBOList.add(order);
				}
				
			}
		}
		//配货管理追加订单===================
		
		//配货管理移除订单===================
		String string = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
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
					if(orderNext.equals(next.getOrderId())){
						orderIterator.remove();
					}
				}
			}
		}
		
		BatchOrderIdListBO batchOrder = null;
		if(!ObjectUtils.isEmpty(orderIdsList)){
			MoveOrderPO moveOrder = new MoveOrderPO();
			param.setMoveOrder(moveOrder);
			BeanUtils.copyProperties(partnerInfoBean,moveOrder);
			
			//创建订单批量查询需要的list和order
			List<String> childOrderIds = new ArrayList<String>();
			for (String order : orderIdsList) {
				childOrderIds.add(order);
			}
			batchOrder = new BatchOrderIdListBO();
	        batchOrder.setKeyArea(param.getPartnerArea());
	        batchOrder.setOrderIds(childOrderIds);
	        
	        //需要订单服务修改订单状态成功情况下,移除订单表数据,并且修改冗余表订单状态(该逻辑在服务端)
			moveOrder.setStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
			moveOrder.setOrderIds(orderIdsList);
			moveOrder.setAllocationId(param.getAllocationId());
			
		}
		//配货管理移除订单===================
		HttpResult result = allocationServer.againVerifyAllocation(param);
		if(result.isSuccess()){
			//出库,中的追加订单处理界面,修改追加订单平方数订单的相关信息
			if(AllocationConstant.FLAG_ADD_ORDER_HANDLE.equals(param.getFlag())){
			    RejectRequestBO rejectRequest = new RejectRequestBO();
			    BeanUtils.copyProperties(param, rejectRequest);
			    rejectRequest.setDisposeStatus(LoadingTaskConstant.DISPOSESTATUS_1);
			    rejectRequest.setWayBillId(param.getWaybillId());
			    rejectRequest.setHandler(param.getOperator());
			    rejectRequest.setHandlerId(param.getOperatorId());
			    rejectRequest.setHandlerTime(new Date());
			    result = loadingTaskServer.rejectRequest(rejectRequest);
			}
			
			if(result.isSuccess()){
				//根据订单号批量查询订单详情信息
				if(batchOrder!=null){
					HttpResult batchOrderResult = orderServer.getOrderDeatilByIdList(batchOrder);
			        BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(batchOrderResult.getData()),BatchOrderDetailListPO.class);
			        List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
			        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
					for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
						//判断该订单只有为已配货的情况下,才允许移除订单
						if(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION.getValue().equals(String.valueOf(warehouseOrderDetailPO.getOrderStatus()))){
							List<String> updateList = new ArrayList<>();
							List<OrderIdBO> updateOrderIdBOList = new ArrayList<>();
							for (String order : orderIdsList) {
								//通知订单服务修改,需要批量执行
								updateList.add(order);
								
								OrderIdBO orderIdBO = new OrderIdBO();
								orderIdBO.setOrderId(order);
								orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
								updateOrderIdBOList.add(orderIdBO);
								
							}
							//移除订单,通知订单服务修改,需要批量执行
							HttpResult updateSplitOrderResult =  orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),updateOrderIdBOList);
							if(!updateSplitOrderResult.isSuccess()){
								LOGGER.error("装车优化中的移除订单,该订单已配货允许移除,但是修改订单状态失败!!!");
								return MsgTemplate.failureMsg("装车优化中的移除订单,该订单已配货允许移除,但是修改订单状态失败!!!");
							}
							Boolean flag = orderServer.compareOrderStatus(orderIdList,param.getPartnerArea(),param.getPartnerId());
							if(flag==false){
								return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
							}
						}else{
							String error = warehouseOrderDetailPO.getOrderId();
							String msg = new StringBuffer().append(error).append(":").append(AllocationMsgEnum.AGAIN_CHOOSE_ORDER.getMsg()).toString();
							//释放同时确认配货,确认优化公共锁
							redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
							//删除同时确认优化锁
						    redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ALLOCATION+waybillId);
							return MsgTemplate.failureMsg(msg);
						}
					}
				}
				
				//追加订单,修改订单服务订单状态修改为已配货
				if(!ObjectUtils.isEmpty(orderIdList)){
					HttpResult updateSplitOrderResult = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
					if(!updateSplitOrderResult.isSuccess()){
						LOGGER.error("装车优化中的追加订单,追加已配货订单,修改订单状态失败!!!");
						return MsgTemplate.failureMsg("装车优化中的追加订单,追加已配货订单,修改订单状态失败!!!");
					}
					Boolean flag = orderServer.compareOrderStatus(orderIdList,param.getPartnerArea(),param.getPartnerId());
					if(flag==false){
						return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
					}
				}
				
				//清楚缓存
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
				redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId);
			}
		}
		//删除确认配货,确认优化公共锁
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.COMMON_ALLOCATION_LOADING+param.getPartnerId());
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> againVerifyAddOrder(List<WarehouseOrderDetailPO> detailList) {
		String waybillId = detailList.get(0).getWaybillId();
		//追加订单号缓存处理
		String str = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		if(!ObjectUtils.isEmpty(detailList)){
			List<String> orderIds = new ArrayList<>();
			for (WarehouseOrderDetailPO orderDetailPO : detailList) {
				orderIds.add(orderDetailPO.getOrderId());
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
					redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(newOrderIdList));
				}
			}else{
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(orderIds));
			}
			
			//缓存详细订单信息
			String cacheOrder = redisClient.get(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
			if(!StringUtils.isEmpty(cacheOrder)){
				Map<String,WarehouseOrderDetailPO> map = new HashMap<>(16);
				List<WarehouseOrderDetailPO> cacheList = gson.fromJson(cacheOrder, new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
				for (WarehouseOrderDetailPO orderDetailPO : cacheList) {
					map.put(orderDetailPO.getOrderId(), orderDetailPO);
				}
				for (WarehouseOrderDetailPO orderDetailPO : detailList) {
					map.put(orderDetailPO.getOrderId(), orderDetailPO);
				}
				cacheList = new ArrayList<>();
				for(Map.Entry<String, WarehouseOrderDetailPO> entry:map.entrySet()){
					cacheList.add(entry.getValue());
				}
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(cacheList);
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(joinOrderParamInfo));
			}else{
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(detailList);
				redisClient.set(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId, gson.toJson(joinOrderParamInfo));
			}
		}
		return MsgTemplate.successMsg();
	}

	@Override
	public Map<String, Object> againCancelAllocation(String waybillId) {
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.AGAIN_VERIFY_ADDORDER+waybillId);
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.REMOVE_ORDER+waybillId);
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.CACHE_AGAIN_VERIFY_ADDORDER+waybillId);
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.DELIVERYID+waybillId);
		return MsgTemplate.successMsg();
	}

	@Override
	public Map<String, Object> intelligentCancelAllocation(String parameter) {
		redisClient.del(RedisPrefixConstant.REDIS_ALLOCATION_ORDER_PREFIX+AllocationConstant.INTELLIGENT_ALLOCATION+parameter);
		return MsgTemplate.successMsg();
	}

	@Override
	public Map<String, Object> getRecordByRrelativeId(RelativeIdBO param) {
		HttpResult result = allocationServer.getRecordByRrelativeId(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> splitOrder(UpdateOrderBO param) {
		String orderId = param.getDeleteOrdeIdList().get(0);
		BatchOrderIdListBO batch = new BatchOrderIdListBO();
		List<String> orderIdList = Arrays.asList(orderId);
		batch.setKeyArea(param.getPartnerArea());
		batch.setOrderIds(orderIdList);
		//根据订单号获取订单详情
		HttpResult result = orderServer.getOrderDeatilByIdList(batch);
		if(!ObjectUtils.isEmpty(result.getData())){
			UpdateSplitOrderBO firstSpiltOrder = param.getFirstSpiltOrder();
			UpdateSplitOrderBO secondSpiltOrder = param.getSecondSpiltOrder();
			
			BatchOrderDetailListPO batchOrder = dataFormatGson.fromJson(gson.toJson(result.getData()),BatchOrderDetailListPO.class);
			List<WarehouseOrderDetailPO> orderList = batchOrder.getOrderList();
			WarehouseOrderDetailPO warehouseOrder = null;
			Integer orderStatus = null;
			Integer orderAmount = null;
			if(!ObjectUtils.isEmpty(orderList)){
				warehouseOrder = orderServer.joinOrderParamInfo(orderList).get(0);
				orderStatus = warehouseOrder.getOrderStatus();
				orderAmount = warehouseOrder.getOrderAmount();
			}else{
				//拆单数据
				orderList = batchOrder.getSplitOrderList();
				warehouseOrder = orderServer.joinOrderParamInfo(orderList).get(0);
				orderStatus = warehouseOrder.getSubStatus();
				orderAmount = warehouseOrder.getSubNumber();
			}
			
			//判断提醒字段标记,含有备,或生,不允许拆单
			Integer isProduce = warehouseOrder.getIsProduce();
			Integer isStored = warehouseOrder.getIsStored();
			if(1==isStored || isProduce==1){
				return MsgTemplate.failureMsg(AllocationMsgEnum.SPLIT_ORDER_PRODUCE_STORED_ERROR);
			}
			//获取库存信息
			SelectAreaByOrderIdBO select  = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, select);
			OrderIdBO order = new OrderIdBO();
			order.setOrderId(param.getOrderId());
			select.setOrderIds(Arrays.asList(order));
			List<WarehouseOrderDetailPO> orderStockInfo = orderServer.getOrderStockInfo(select);
			WarehouseOrderDetailPO orderDetail = orderStockInfo.get(0);
			
			//只有部分入库和已入库订单才允许拆分
			if(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(String.valueOf(orderStatus))){
				//判断库存数量和前端传来的库存数量相等
				if(!orderDetail.getAmountSaved().equals(firstSpiltOrder.getSubNumber())){
					return MsgTemplate.failureMsg(AllocationMsgEnum.LESS_STOK_FIRST_ORDER_AMOUNT_ERROR);
				}
				//另外订单的拆分数据
				Integer lessAmount = orderAmount-orderDetail.getAmountSaved();
				if(!lessAmount.equals(secondSpiltOrder.getSubNumber())){
					return MsgTemplate.failureMsg(AllocationMsgEnum.LESS_STOK_SECOND_ORDER_AMOUNT_ERROR);
				}
				
				//处理wms的异常订单表,将其修改为正常订单
				UpdateAbnormalBO updateAbnormalBO = new UpdateAbnormalBO();
				BeanUtils.copyProperties(param, updateAbnormalBO);
				updateAbnormalBO.setStatus(1);
				updateAbnormalBO.setResult(0);
				HttpResult abnormalResult = abnormalServer.updateAbnormal(updateAbnormalBO);
				if(!abnormalResult.isSuccess()){
					return MsgTemplate.customMsg(abnormalResult);
				}
				//处理wms的异常订单表,将其修改为正常订单
				firstSpiltOrder.setSubStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
				secondSpiltOrder.setSubStatus(Integer.valueOf(OrderStatusTypeEnum.NO_STOCK.getValue()));
			}else if(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue().equals(String.valueOf(orderStatus))){
				//已入库订单只需要判断,两个拆单的拆单数量之后等于订单数量
				if(!orderAmount.equals(firstSpiltOrder.getSubNumber()+secondSpiltOrder.getSubNumber())){
					return MsgTemplate.failureMsg(AllocationMsgEnum.ALL_STOCK_ORDER_SUBNUMBER_ERROR);
				}
				firstSpiltOrder.setSubStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
				secondSpiltOrder.setSubStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
			}else{
				return MsgTemplate.failureMsg(AllocationMsgEnum.NOT_ALLOW_SPLIT_ORDER);
			}
			
			//获取真正的子单需要的订单状态
			
			String updateOrderId = param.getOrderId();
			if(updateOrderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)!=-1){
				updateOrderId = updateOrderId.substring(0, updateOrderId.indexOf(LoadingTaskConstant.SUBSTRING_ORDER));
			}
			List<String> orderIds = Arrays.asList(updateOrderId);
			BatchOrderIdListBO batchOrderIdListBO = new BatchOrderIdListBO();
			batchOrderIdListBO.setOrderIds(orderIds);
			batchOrderIdListBO.setKeyArea(param.getPartnerArea());
			HttpResult orderDeatilByIdList = orderServer.getOrderDeatilByIdList(batch);
			BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gson.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
	        List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(batchOrderDetailListPO.getOrderList());
	        List<OrderIdBO> orderIdBOList = new ArrayList<>();
	        Map<String,WarehouseOrderDetailPO> orderDetailMap = new HashMap<>(16);
	        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
	        	OrderIdBO orderIdBO = new OrderIdBO();
	        	orderIdBO.setOrderId(warehouseOrderDetailPO.getOrderId());
	        	orderIdBO.setKeyArea(param.getPartnerArea());
	        	orderIdBOList.add(orderIdBO);
	        	orderDetailMap.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
			}
	        
	        String newOrderStatus = null;
	        HttpResult splitOrderResult  = orderServer.getSplitOrderDeatilByIdList(orderIdBOList);
	        Map<String,List<WarehouseOrderDetailPO>> detailMap = dataFormatGson.fromJson(gson.toJson(splitOrderResult.getData()), new TypeToken<Map<String,List<WarehouseOrderDetailPO>>>(){}.getType());
	        for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry : detailMap.entrySet()){
	        	List<WarehouseOrderDetailPO> detailValueList = entry.getValue();
	        	if(!ObjectUtils.isEmpty(detailValueList)){
	        		for(WarehouseOrderDetailPO orderDetailPO:detailValueList){
		        		int otherOrderStatus = orderDetailMap.get(orderDetailPO.getOrderId()).getOrderStatus().intValue();
		        		int splitOrderStatus = orderDetailPO.getSubStatus().intValue();
		        		//假如拆单状态小于子单状态则把状态赋值给子单
		        		if(splitOrderStatus<otherOrderStatus){
		        			newOrderStatus = String.valueOf(splitOrderStatus);
		        		}
		        	}
	        	}
	        }
			
			//组织oms需要的拆单数据
	        param.setOrderStatus(newOrderStatus==null?String.valueOf(orderStatus):newOrderStatus);
			param.setKeyArea(param.getPartnerArea());
			param.setSplitStatus(1);
			List<UpdateSplitOrderBO> splitOrders = new ArrayList<>();
			firstSpiltOrder.setKeyArea(param.getPartnerArea());
			firstSpiltOrder.setInStock(firstSpiltOrder.getSubNumber());
			firstSpiltOrder.setIsException(0);
			firstSpiltOrder.setIsProduce(0);
			firstSpiltOrder.setIsStored(0);
			
			secondSpiltOrder.setKeyArea(param.getPartnerArea());
			secondSpiltOrder.setInStock(secondSpiltOrder.getSubNumber());
			secondSpiltOrder.setIsException(0);
			secondSpiltOrder.setIsProduce(0);
			secondSpiltOrder.setIsStored(0);
			
			splitOrders.add(firstSpiltOrder);
			splitOrders.add(secondSpiltOrder);
			param.setSplitOrders(splitOrders);
			
			//假如是部分入库则删除secondSplit
			if(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(String.valueOf(orderStatus))){
				param.setSecondSpiltOrder(null);
			}
			result = allocationServer.splitOrder(param);
			
			//并且假如是部分入库的情况下,修改冗余表的订单状态,改成待入库
			String deleteOrder = param.getDeleteOrdeIdList().get(0);
			if(deleteOrder.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)==-1){
				List<UpdateOrderRedundantBO> updateList = new ArrayList<>();
				UpdateOrderRedundantBO update = new UpdateOrderRedundantBO();
				update.setStatus(Integer.valueOf(OrderStatusTypeEnum.NO_STOCK.getValue()));
				update.setOrderId(deleteOrder);
				update.setPartnerId(param.getPartnerId());
				updateList.add(update);
				result = allocationServer.batchUpdateOrderRedun(updateList);
			}
			if(result.isSuccess()){
				//调用oms的拆单接口
				result = orderServer.splitOrder(param);
			}
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.failureMsg(AllocationMsgEnum.SPLIT_ORDER_ERROR);
		}
	}

	@Override
	public Map<String, Object> getSplitOrderByOrderId(OrderIdBO param) {
		param.setKeyArea(param.getPartnerArea());
		List<OrderIdBO> list = new ArrayList<>();
		list.add(param);
		HttpResult result = orderServer.getSplitOrderDeatilByIdList(list);
		List<WarehouseOrderDetailPO> orderDetail = null;
		List<String> orderIdStrList = new ArrayList<>();
		if(!ObjectUtils.isEmpty(result.getData())){
			Map<String,List<WarehouseOrderDetailPO>> orderMap = dataFormatGson.fromJson(gson.toJson(result.getData()), new TypeToken<Map<String, List<WarehouseOrderDetailPO>>>() {}.getType());
			for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:orderMap.entrySet()){
				String orderId = entry.getKey();
				orderDetail = orderServer.joinOrderParamInfo(entry.getValue());
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : orderDetail) {
					warehouseOrderDetailPO.setChildOrderId(orderId);
					String subOrderId = warehouseOrderDetailPO.getSubOrderId();
					orderIdStrList.add(subOrderId);
				}
			}
			
			if(!ObjectUtils.isEmpty(orderIdStrList)){
				//根据订单号获取提货单信息
				HttpResult deliveryResult = allocationServer.getDeliveryByOrderIds(orderIdStrList);
				if(!ObjectUtils.isEmpty(deliveryResult.getData())){
					List<DeliveryPO> deliveryPOList = gson.fromJson(gson.toJson(deliveryResult.getData()), new TypeToken<ArrayList<DeliveryPO>>(){}.getType());
					DeliveryPO deliveryPO = deliveryPOList.get(0);
					String deliveryId = deliveryPO.getDeliveryId();
					List<String> deliveryIdStr = Arrays.asList(deliveryId);
					//根据提货单号获取运单信息
					HttpResult waybillResult = allocationServer.getWaybillByDeliveryIds(deliveryIdStr);
					List<WaybillPO> waybillPOList = gson.fromJson(gson.toJson(waybillResult.getData()), new TypeToken<ArrayList<WaybillPO>>(){}.getType());
					String plateNumber = waybillPOList.get(0).getPlateNumber();
					String waybillId = waybillPOList.get(0).getWaybillId();
					
					for (WarehouseOrderDetailPO warehouseOrderDetailPO : orderDetail) {
						warehouseOrderDetailPO.setPlateNumber(plateNumber);
						warehouseOrderDetailPO.setDeliveryId(deliveryId);
						warehouseOrderDetailPO.setWaybillId(waybillId);
					}
				}
			}
			return MsgTemplate.successMsg(orderDetail);
		}else{
			return MsgTemplate.failureMsg(AllocationMsgEnum.ORDER_IS_NULL);
		}
	}
	
	@Override
    public Map<String, Object> TmsVehicleQueuingList() {
        HeadCookies headCookies = new HeadCookies((x) -> {
            x.put("devTokenId", "123456");
            x.put("token", "123456");
        });
        ReqVehicleQueuingListBiz reqVehicleQueuingListBiz = new ReqVehicleQueuingListBiz()
                .setFactoryid("FactoryId");
        HTTPResponse<TmsJsonResult> response = carchecksignServer.TmsVehicleQueuingList(reqVehicleQueuingListBiz, headCookies, "123456", "123456");
        System.out.println(response.getBodyString());
        return MsgTemplate.successMsg();
    }
	
}
