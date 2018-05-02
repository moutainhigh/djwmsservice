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
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
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
				BeanUtils.copyProperties(joinOrderParamInfo.get(0), warehouseOrderDetailPO,"amountSaved","remark","instockAmount","areaList");
				return MsgTemplate.successMsg(warehouseOrderDetailPO);
			}else{
				joinOrderParamInfo.get(0).setAmountSaved(0);
				return MsgTemplate.successMsg(joinOrderParamInfo.get(0));
			}
		}else{
			return null;
		}
	}


	@Override
	public Map<String, Object> splitOrder(UpdateOrderBO param) {
		HttpResult splitOrder = orderServer.splitOrder(param);
		return MsgTemplate.customMsg(splitOrder);
	}
}
