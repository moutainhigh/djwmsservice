package com.djcps.wms.cancelstock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.cancelstock.controller.CancelStockController;
import com.djcps.wms.cancelstock.enums.CancelStockEnum;
import com.djcps.wms.cancelstock.enums.CancelStockMsgEnum;
import com.djcps.wms.cancelstock.model.CancalOrderAttributePO;
import com.djcps.wms.cancelstock.model.CancelStockPO;
import com.djcps.wms.cancelstock.model.param.AddCancelStockBO;
import com.djcps.wms.cancelstock.model.param.AddStockBO;
import com.djcps.wms.cancelstock.model.param.CancelOrderIdBO;
import com.djcps.wms.cancelstock.model.param.PickerIdBO;
import com.djcps.wms.cancelstock.server.CancelStockServer;
import com.djcps.wms.cancelstock.service.CancelStockService;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.delivery.constant.DeliveryConstant;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * 退库实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class CancelStockServiceImpl implements CancelStockService {

	private static DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(CancelStockServiceImpl.class);
	
	private Gson gson = GsonUtils.gson;
	
	@Autowired
	private CancelStockServer cancelStockServer;

	@Autowired
	private OrderServer orderServer;
	
	@Override
	public Map<String, Object> getOrderByOrderId(CancelOrderIdBO param) {
		HttpResult result = cancelStockServer.getOrderByOrderId(param);
		if(!result.isSuccess()){
			return MsgTemplate.failureMsg(CancelStockMsgEnum.ORDER_IS_NULL);
		}
		CancalOrderAttributePO orderAttribute = gson.fromJson(gson.toJson(result.getData()), CancalOrderAttributePO.class);
		BatchOrderIdListBO batchOrderIdListBO = new BatchOrderIdListBO();
		List<String> orderIdsList = new ArrayList<>();
		orderIdsList.add(param.getOrderId());
		batchOrderIdListBO.setOrderIds(orderIdsList);
		batchOrderIdListBO.setKeyArea(param.getPartnerArea());
		HttpResult orderResult = orderServer.getOrderDeatilByIdList(batchOrderIdListBO);
		if(ObjectUtils.isEmpty(orderResult.getData())){
			return MsgTemplate.failureMsg(CancelStockMsgEnum.ORDER_IS_NULL);
		}else{
			BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(orderResult.getData()),BatchOrderDetailListPO.class);
			List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
			if(!ObjectUtils.isEmpty(orderList)){
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
				BeanUtils.copyProperties(joinOrderParamInfo.get(0), orderAttribute,"warehouseId","warehouseName");
			}else{
				List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
				List<WarehouseOrderDetailPO> joinSplitOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
				BeanUtils.copyProperties(joinSplitOrderParamInfo.get(0), orderAttribute,"warehouseId","warehouseName");
			}
			return MsgTemplate.successMsg(orderAttribute);
		}
	}

	@Override
	public Map<String, Object> addStock(AddStockBO param) {
		CancelOrderIdBO cancelOrderIdBO = new CancelOrderIdBO();
		BeanUtils.copyProperties(param, cancelOrderIdBO);
		HttpResult cancelOrderResult = cancelStockServer.getOrderByOrderId(cancelOrderIdBO);
		CancalOrderAttributePO orderAttribute = gson.fromJson(gson.toJson(cancelOrderResult.getData()), CancalOrderAttributePO.class);
		if(!param.getWarehouseId().equals(orderAttribute.getWarehouseId())){
			return MsgTemplate.failureMsg(CancelStockEnum.WAREHOUSEID_ERROR.getValue());
		}
		HttpResult result = cancelStockServer.addStock(param);
		//修改订单服务拆分订单的订单状态
		if(result.isSuccess()){
	        //通知oms服务修改,需要批量执行
			List<String> order = new ArrayList<>();
			order.add(param.getOrderId());
			
			List<OrderIdBO> orderIdBOList = new ArrayList<>();
			OrderIdBO orderIdBO = new OrderIdBO();
			orderIdBO.setOrderId(param.getOrderId());
			orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
			orderIdBOList.add(orderIdBO);
			
			result = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
			if(result.isSuccess()){
				List<String> orderId = new ArrayList<>();
				orderId.add(param.getOrderId());
				Boolean compareOrderStatus = orderServer.compareOrderStatus(orderId,  param.getPartnerArea(),param.getPartnerId());
				if(compareOrderStatus==false){
					return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
				}
			}
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getCancelStockByPickerId(PickerIdBO param) {
		HttpResult result = cancelStockServer.getCancelStockByPickerId(param);
		List<String> orderIdList = new ArrayList<>();
		Map<String,CancelStockPO> map = new HashMap<>(16);
		if(!ObjectUtils.isEmpty(result.getData())){
			List<CancelStockPO> cancelStockList = gson.fromJson(gson.toJson(result.getData()), new TypeToken<ArrayList<CancelStockPO>>(){}.getType());
			for (CancelStockPO cancelStockPO : cancelStockList) {
				map.put(cancelStockPO.getSonOrderId(), cancelStockPO);
				orderIdList.add(cancelStockPO.getSonOrderId());
			}
		}else{
			return MsgTemplate.successMsg();
		}
		BatchOrderIdListBO batchOrder = new BatchOrderIdListBO();
		batchOrder.setOrderIds(orderIdList);
		batchOrder.setKeyArea(param.getPartnerArea());
		HttpResult orderDeatilResult = orderServer.getOrderDeatilByIdList(batchOrder);
		List<CancalOrderAttributePO> orderAttributeList = new ArrayList<>();
		if(!ObjectUtils.isEmpty(orderDeatilResult.getData())){
			BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(orderDeatilResult.getData()),BatchOrderDetailListPO.class);
	        List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
	        if(!ObjectUtils.isEmpty(orderList)){
	        	List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
	 			for (WarehouseOrderDetailPO orderDetail : joinOrderParamInfo) {
	 				CancalOrderAttributePO orderAttribute = new CancalOrderAttributePO();
	 				CancelStockPO cancelStockPO = map.get(orderDetail.getChildOrderId());
	 				BeanUtils.copyProperties(cancelStockPO,orderAttribute);
	 				BeanUtils.copyProperties(orderDetail,orderAttribute);
	 				orderAttributeList.add(orderAttribute);
	 			}
	        }
	       
			List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
			if(!ObjectUtils.isEmpty(splitOrderList)){
				List<WarehouseOrderDetailPO> splitJoinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
				for (WarehouseOrderDetailPO orderDetail : splitJoinOrderParamInfo) {
						CancalOrderAttributePO orderAttribute = new CancalOrderAttributePO();
						CancelStockPO cancelStockPO = map.get(orderDetail.getChildOrderId());
						BeanUtils.copyProperties(cancelStockPO,orderAttribute);
						BeanUtils.copyProperties(orderDetail,orderAttribute);
						orderAttributeList.add(orderAttribute);
				}	
	        }
			
			
		}
		if(!ObjectUtils.isEmpty(orderAttributeList)){
			return MsgTemplate.successMsg(orderAttributeList);
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> addCancelStock(AddCancelStockBO param) {
		HttpResult result = cancelStockServer.addCancelStock(param);
		return MsgTemplate.customMsg(result);
	}

}
