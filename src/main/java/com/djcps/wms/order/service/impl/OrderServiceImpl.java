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
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.offlinepaperboard.OfflineQueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.PaperboardResultDataPO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.constant.OperationTypeConstant;
import com.djcps.wms.stock.enums.StockMsgEnum;
import com.djcps.wms.stock.model.GetorderInfoListBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.server.StockServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 *  订单业务层实现类
 * @company: djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private Gson gson = GsonUtils.gson;
	
	private Gson dataFormatGson = GsonUtils.dataFormatGson;
	
	private Gson gsonNotNull = GsonUtils.gsonNotNull;

	@Autowired
	private OrderServer orderServer;
	@Autowired
    private StockServer stockServer;
	@Override
	public Map<String, Object> getOnlinePaperboardList(QueryObjectBO param) {
		HttpResult result = orderServer.getOnlinePaperboardList(param);
		//组织订单详情信息和库位信息
		return getOrderDetailInfoAndStockInfo(result,param.getPartnerId());
	}
	
	@Override
	public Map<String, Object> getOffinePaperboardList(OfflineQueryObjectBO param) {
		HttpResult result = orderServer.getOffinePaperboardList(param);
		//组织订单详情信息和库位信息
		return getOrderDetailInfoAndStockInfo(result,param.getPartnerId());
	}
	
	@Override
	public Map<String, Object> getOffineBoxOrderList(OfflineQueryObjectBO param) {
		HttpResult result = orderServer.getOffineBoxOrderList(param);
		//组织订单详情信息和库位信息
		return getOrderDetailInfoAndStockInfo(result,param.getPartnerId());
	}
	
	@Override
	public Map<String, Object> getOrderByOrderId(BatchOrderIdListBO param) {
		//判断扫面或者网页端传来的订单号是否为拆分,是的话则需要进行查询另外订单
		List<OrderIdBO> splitOrderList = new ArrayList<>();
		OrderIdBO order = new OrderIdBO();
		order.setOrderId(param.getOrderIds().get(0));
		order.setKeyArea(param.getPartnerArea());
		splitOrderList.add(order);
		HttpResult orderDeatilByIdList = orderServer.getOrderDeatilByIdList(param);
		BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gsonNotNull.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
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
		List<OrderIdBO> list = new ArrayList<>();
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
	/**
	 * 判断该订单是否为移库所对应仓库的订单
	 * @param param
	 * @return
	 */
	public boolean getorderInfoList(BatchOrderIdListBO param) {
	    GetorderInfoListBO getorderInfoListBO = new GetorderInfoListBO();
	    getorderInfoListBO.setList(param.getOrderIds());
	    getorderInfoListBO.setPartnerId(param.getPartnerId());
	    getorderInfoListBO.setWarehouseId(param.getWarehouseId());
	    HttpResult result = stockServer.getOrderInfoList(getorderInfoListBO);
	    if(ObjectUtils.isEmpty(result.getData())) {
	        return false;
	    }else {
	        return true;
	    }
	}
	@Override 
	public Map<String, Object> getPdaOrderByOrderId(BatchOrderIdListBO param) {
	    //判断该订单是否为移库所对应仓库的订单
	    if(OperationTypeConstant.REMOVE_WAREHOUSE.equals(param.getOperationType())) {
	        boolean bool = getorderInfoList(param);
	        if(!bool) {
	            return MsgTemplate.failureMsg(StockMsgEnum.NOT_REMOVE);
	        }
	    }
	    
		//判断扫面或者网页端传来的订单号是否为拆分,是的话则需要进行查询另外订单
		List<OrderIdBO> splitOrderList = new ArrayList<>();
		OrderIdBO order = new OrderIdBO();
		order.setOrderId(param.getOrderIds().get(0));
		order.setKeyArea(param.getPartnerArea());
		splitOrderList.add(order);
		HttpResult result = orderServer.getSplitOrderDeatilByIdList(splitOrderList);
		if(!ObjectUtils.isEmpty(result.getData())){
			List<WarehouseOrderDetailPO> orderDetail = null;
			Map<String,List<WarehouseOrderDetailPO>> orderMap = dataFormatGson.fromJson(gsonNotNull.toJson(result.getData()), new TypeToken<Map<String, List<WarehouseOrderDetailPO>>>() {}.getType());
			for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry:orderMap.entrySet()){
				orderDetail = entry.getValue();
			}
			if(!ObjectUtils.isEmpty(orderDetail)){
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : orderDetail) {
					if(warehouseOrderDetailPO.getSubStatus().equals(Integer.valueOf(OrderStatusTypeEnum.NO_STOCK.getValue()))){
						List<String> strList = Arrays.asList(warehouseOrderDetailPO.getSubOrderId());
						param.setOrderIds(strList);
					}
				}
			}
		}
		HttpResult orderDeatilByIdList = orderServer.getOrderDeatilByIdList(param);
		BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gsonNotNull.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
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
		List<OrderIdBO> list = new ArrayList<>();
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

	
	/**
	 * 线上,线下纸板订单,共用模代码,组织参数和库位信息
	 * @param onlinePaperResult
	 * @param partnerId
	 * @return
	 * @author:zdx
	 * @date:2018年5月8日
	 */
	private Map<String, Object> getOrderDetailInfoAndStockInfo(HttpResult onlinePaperResult,String partnerId){
		if(!ObjectUtils.isEmpty(onlinePaperResult.getData())){
			PaperboardResultDataPO paperResultData = dataFormatGson.fromJson(gsonNotNull.toJson(onlinePaperResult.getData()), PaperboardResultDataPO.class);
			//根据订单号获取在库信息
			SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
			selectAreaByOrderId.setPartnerId(partnerId);
			List<OrderIdBO> orderIdBOList = new ArrayList<OrderIdBO>();
			for (WarehouseOrderDetailPO onlinePaperboardPO : paperResultData.getContent()) {
				OrderIdBO orderIdBO = new OrderIdBO();
				orderIdBO.setOrderId(onlinePaperboardPO.getOrderId());
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
					WarehouseOrderDetailPO orderStockInfoPO = orderStockInfoPOMap.get(onlinePaperboardPO.getOrderId());
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
			BaseVO vo = new BaseVO();
			vo.setResult(null);
			vo.setTotal(0);
			onlinePaperResult.setData(vo);
			return MsgTemplate.customMsg(onlinePaperResult);
		}
	}

}
