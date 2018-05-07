package com.djcps.wms.order.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.base.BaseVO;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardResultDataPO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.google.gson.Gson;
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
	
	@Autowired
	private OrderServer orderServer;
	
	@Override
	public Map<String, Object> getOnlinePaperboardList(QueryObjectBO param) {
		HttpResult onlinePaperResult = orderServer.getOnlinePaperboardList(param);
		if(!ObjectUtils.isEmpty(onlinePaperResult.getData())){
			OnlinePaperboardResultDataPO paperResultData = gson.fromJson(gson.toJson(onlinePaperResult.getData()), OnlinePaperboardResultDataPO.class);
			//根据订单号获取在库信息
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			BeanUtils.copyProperties(param, selectAreaByOrderId);
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
						onlinePaperboardPO.setAmountSaved(orderStockInfoPO.getAmountSaved());
						onlinePaperboardPO.setWarehouseId(orderStockInfoPO.getWarehouseId());
						onlinePaperboardPO.setWarehouseName(orderStockInfoPO.getWarehouseName());
						onlinePaperboardPO.setAreaList(orderStockInfoPO.getAreaList());
					}
				}
			}else{
				for (WarehouseOrderDetailPO onlinePaperboardPO : onlinePaperboardPOList) {
					if(onlinePaperboardPO.getAmountSaved()==null){
						onlinePaperboardPO.setAmountSaved(0);
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
		//判断扫面或者网页端传来的订单号是否为拆分,是的话则需要进行查询另外订单
		List<OrderIdBO> splitOrderList = new ArrayList<>();
		OrderIdBO order = new OrderIdBO();
		order.setOrderId(param.getOrderIds().get(0));
		order.setKeyArea(param.getPartnerArea());
		HttpResult result = orderServer.getSplitOrderDeatilByIdList(splitOrderList);
		if(!ObjectUtils.isEmpty(result.getData())){
			List<WarehouseOrderDetailPO> orderDetail = null;
			Map<String,List<WarehouseOrderDetailPO>> orderMap = gson.fromJson(gson.toJson(result.getData()), new TypeToken<Map<String, List<WarehouseOrderDetailPO>>>() {}.getType());
			for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:orderMap.entrySet()){
				orderDetail = entry.getValue();
			}
			for (WarehouseOrderDetailPO warehouseOrderDetailPO : orderDetail) {
				if(warehouseOrderDetailPO.getOrderStatus().equals(Integer.valueOf(OrderStatusTypeEnum.NO_STOCK.getValue()))){
					List<String> strList = Arrays.asList(warehouseOrderDetailPO.getSubOrderId());
					param.setOrderIds(strList);
				}
			}
		}
		HttpResult orderDeatilByIdList = orderServer.getOrderDeatilByIdList(param);
		BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
	    List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
	    List<WarehouseOrderDetailPO> newSplitOrderList = batchOrderDetailListPO.getSplitOrderList();
	    List<WarehouseOrderDetailPO> joinOrderParamInfo = null;
	    if(!ObjectUtils.isEmpty(orderList)){
	    	joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
	    }else if(!ObjectUtils.isEmpty(newSplitOrderList)){
	    	joinOrderParamInfo = orderServer.joinOrderParamInfo(newSplitOrderList);
	    }else{
	    	return MsgTemplate.successMsg(null);
	    }
	    SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		BeanUtils.copyProperties(param, selectAreaByOrderId);
		List<OrderIdBO> list = new ArrayList();
		OrderIdBO orderIdBO = new OrderIdBO();
		orderIdBO.setOrderId(param.getOrderIds().get(0));
		list.add(orderIdBO);
		selectAreaByOrderId.setOrderIds(list);
		List<WarehouseOrderDetailPO> orderStockInfo = orderServer.getOrderStockInfo(selectAreaByOrderId);
		if(!ObjectUtils.isEmpty(orderStockInfo)){
			WarehouseOrderDetailPO warehouseOrderDetailPO = orderStockInfo.get(0);
			BeanUtils.copyProperties(joinOrderParamInfo.get(0), warehouseOrderDetailPO,"amountSaved","remark","instockAmount","areaList");
			return MsgTemplate.successMsg(warehouseOrderDetailPO);
		}else{
			joinOrderParamInfo.get(0).setAmountSaved(0);
			return MsgTemplate.successMsg(joinOrderParamInfo.get(0));
		}
	}

}
