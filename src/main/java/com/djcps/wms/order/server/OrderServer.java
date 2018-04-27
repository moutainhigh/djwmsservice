package com.djcps.wms.order.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.inject.spi.Bean;

import com.alibaba.fastjson.JSONArray;
import com.djcps.wms.order.model.ChildOrderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OrderHttpResult;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.OnlinePaperboardBO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitSonOrderBO;
import com.djcps.wms.order.request.OnlinePaperboardRequest;
import com.djcps.wms.order.request.UpdateOrderHttpRequest;
import com.djcps.wms.order.request.WmsForOrderHttpRequest;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.server.StockServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rpc.plugin.http.HTTPResponse;


/**
 * 订单服务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
@Component
public class OrderServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(OrderServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private StockServer stockServer;
	
	@Autowired
	OnlinePaperboardRequest onlinePaperboardRequest;
	
	@Autowired
	private WmsForOrderHttpRequest orderHttpRequest;
	
	@Autowired
	private UpdateOrderHttpRequest  updateOrderHttpRequest;
	
	public HttpResult getOrderByOrderId(OrderIdBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = orderHttpRequest.getOrderByOrderId(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult getOrderByOrderIds(OrderIdsBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = updateOrderHttpRequest.getOrderByOrderIds(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult updateOrderStatus(OrderIdBO param){
        //将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = updateOrderHttpRequest.updateOrderStatus(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
    }
	
	public HttpResult getOnlinePaperboardList(QueryObjectBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = onlinePaperboardRequest.getOnlinePaperboardList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	/**
	 * 修改子单信息接口信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	public HttpResult updateOrderInfo(List<UpdateSplitSonOrderBO> param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = onlinePaperboardRequest.updateOrderInfo(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	/**
	 * 修改拆单信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	public HttpResult updateSplitOrderInfo(List<UpdateSplitOrderBO> param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = onlinePaperboardRequest.updateSplitOrderInfo(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	/**
	 * 修改订单状态,该订单既有可能是拆单,也有可能是子单
	 * @param orderId
	 * @param partnerArea
	 * @param orderStatus
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	public HttpResult updateOrderOrSplitOrder(String partnerArea,List<OrderIdBO> orderIdList) {
		List<UpdateSplitSonOrderBO> orderUpdateList = new ArrayList<>();
		List<UpdateSplitOrderBO> splitOrderUpdateList = new ArrayList<>();
		for (OrderIdBO orderIdBO : orderIdList) {
			String string = orderIdBO.getOrderId();
			String orderStatus = orderIdBO.getStatus();
			//这里首先需要判断,这个orderId是子订单号还是拆分订单号
			 int indexOf = string.indexOf("-");
			 if(indexOf==-1){
				//等于-1表示没有携带-是子订单号
			 	UpdateSplitSonOrderBO update = new UpdateSplitSonOrderBO();
	        	update.setOrderId(string);
	        	update.setOrderStatus(orderStatus);
	        	update.setKeyArea(partnerArea);
	        	orderUpdateList.add(update);
			 }else{
				//否则就是拆分订单号
				UpdateSplitOrderBO update = new UpdateSplitOrderBO();
	    		update.setSubOrderId(string);
	    		update.setSubStatus(Integer.valueOf(orderStatus));
	    		splitOrderUpdateList.add(update);
			 }
		}
		
		HttpResult result = null;  
		if(!ObjectUtils.isEmpty(orderUpdateList)){
			result = updateOrderInfo(orderUpdateList);
	    	if(!result.isSuccess()){
				LOGGER.error("------修改OMS子订单状态失败!!!------");
			}
		}
		
		if(!ObjectUtils.isEmpty(splitOrderUpdateList)){
			result = updateSplitOrderInfo(splitOrderUpdateList);
	    	if(!result.isSuccess()){
				LOGGER.error("------修改OMS拆单状态失败!!!------");
			}
		}
		return result;
	}
	
	
	/**
	 * 比较拆单的订单状态和子单的订单状态大小,小的取小
	 * @param orderIdList
	 * @param partnerArea
	 * @return
	 * @author:zdx
	 * @date:2018年4月20日
	 */
	public Boolean compareOrderStatus(List<String> orderIdList,String partnerArea){
		if(!ObjectUtils.isEmpty(orderIdList)){
			List<String> orderIds = new ArrayList<>();
			Set<String> orderSet = new HashSet<>();
			for (String str : orderIdList) {
				//修改成功之后判断该订单
				if(str.indexOf("-")!=-1){
					str = str.substring(0, str.indexOf("-"));
					orderSet.add(str);
				}
			}
			for (String str : orderSet) {
				orderIds.add(str);
			}
			if(orderSet.size()==0){
				return true;
			}
			BatchOrderIdListBO batch = new BatchOrderIdListBO();
			batch.setOrderIds(orderIds);
			batch.setKeyArea(partnerArea);
			HttpResult orderDeatilByIdList = getOrderDeatilByIdList(batch);
			BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
	        List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
	        List<WarehouseOrderDetailPO> joinOrderParamInfo = joinOrderParamInfo(orderList);
	        List<OrderIdBO> orderIdBOList = new ArrayList<>();
	        Map<String,WarehouseOrderDetailPO> orderDetailMap = new HashMap<>();
	        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
	        	OrderIdBO orderIdBO = new OrderIdBO();
	        	BeanUtils.copyProperties(warehouseOrderDetailPO, orderIdBO);
	        	orderIdBOList.add(orderIdBO);
	        	orderDetailMap.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
			}
	        HttpResult splitOrderResult  = getSplitOrderDeatilByIdList(orderIdBOList);
	        Map<String,List<WarehouseOrderDetailPO>> detailMap = gson.fromJson(gson.toJson(splitOrderResult.getData()), new TypeToken<Map<String,List<WarehouseOrderDetailPO>>>(){}.getType());
	        for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry : detailMap.entrySet()){
	        	List<WarehouseOrderDetailPO> detailValueList = entry.getValue();
	        	for(WarehouseOrderDetailPO orderDetailPO:detailValueList){
	        		int orderStatus = orderDetailMap.get(orderDetailPO.getOrderId()).getOrderStatus().intValue();
	        		int splitOrderStatus = orderDetailPO.getOrderStatus().intValue();
	        		//假如拆单状态小于子单状态则把状态赋值给子单
	        		if(splitOrderStatus<orderStatus){
	        			orderDetailMap.get(orderDetailPO.getOrderId()).setOrderStatus(splitOrderStatus);
	        		}
	        	}
	        }
	        List<UpdateSplitSonOrderBO> updateList = new ArrayList<>();
	        for(Map.Entry<String,WarehouseOrderDetailPO> entry : orderDetailMap.entrySet()){
	        	WarehouseOrderDetailPO orderDetailValue = entry.getValue();
	        	UpdateSplitSonOrderBO update = new UpdateSplitSonOrderBO();
 	        	update.setOrderId(orderDetailValue.getOrderId());
 	        	update.setOrderStatus(String.valueOf(orderDetailValue.getOrderStatus()));
 	        	update.setKeyArea(partnerArea);
 	        	updateList.add(update);
	        }
	        HttpResult result = updateOrderInfo(updateList);
	    	if(!result.isSuccess()){
	    		LOGGER.error("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
    		}
			return result.isSuccess();
		}
		return true;
	}
	
	public HttpResult getOrderDeatilByIdList(BatchOrderIdListBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = onlinePaperboardRequest.getOrderDeatilByIdList(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	/**
	 * 根据订单号获取拆单信息,支持批量
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月20日
	 */
	public HttpResult getSplitOrderDeatilByIdList(List<OrderIdBO> param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = onlinePaperboardRequest.getSplitOrderDeatilByI(rb);
        //校验请求是否成功
        return verifyHttpResult(http);
	}
	
	/**
	 * 拼接订单中的参数,拼接了楞型,产品规格和材料规格,地址
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月12日
	 */
	public List<WarehouseOrderDetailPO> joinOrderParamInfo(List<WarehouseOrderDetailPO> param){
		for (WarehouseOrderDetailPO onlinePaperboardPO : param) {
			//规格长宽高都不为null,才进行拼接
			if(!ObjectUtils.isEmpty(onlinePaperboardPO.getBoxLength()) && !ObjectUtils.isEmpty(onlinePaperboardPO.getBoxWidth()) &&
					!ObjectUtils.isEmpty(onlinePaperboardPO.getBoxHeight())){
				onlinePaperboardPO.setProductSize(new StringBuffer().append(onlinePaperboardPO.getBoxLength()).append("*").append(onlinePaperboardPO.getBoxWidth())
						.append("*").append(onlinePaperboardPO.getBoxHeight()).toString());
			}
			if(!ObjectUtils.isEmpty(onlinePaperboardPO.getMaterialLength()) && !ObjectUtils.isEmpty(onlinePaperboardPO.getMaterialWidth())){
				onlinePaperboardPO.setMaterialSize(new StringBuffer().append(onlinePaperboardPO.getMaterialLength()).append("*")
						.append(onlinePaperboardPO.getMaterialWidth()).toString());
			}
			String customerName = !StringUtils.isEmpty(onlinePaperboardPO.getCuserName())?onlinePaperboardPO.getCuserName():onlinePaperboardPO.getPuserName();
			onlinePaperboardPO.setCustomerName(customerName);
			onlinePaperboardPO.setOrderAmount(onlinePaperboardPO.getAmountPiece());
			onlinePaperboardPO.setAddressDetailProvince(new StringBuffer().append(onlinePaperboardPO.getCodeProvince()).append(onlinePaperboardPO.getAddressDetail()).toString());
			onlinePaperboardPO.setOrderId(onlinePaperboardPO.getChildOrderId());
		}
		return param;
	}
	
	/**
	 * 根据订单号获取订单在库信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月12日
	 */
	public List<WarehouseOrderDetailPO> getOrderStockInfo(SelectAreaByOrderIdBO param){
		HttpResult stockInfoResult = stockServer.getAreaByOrderId(param);
		if(!ObjectUtils.isEmpty(stockInfoResult.getData())){
			List<WarehouseOrderDetailPO> orderStockInfoList = gson.fromJson(gson.toJson(stockInfoResult.getData()), new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			return orderStockInfoList;
		}else{
			return null;
		}
	}
	
	/**
	 * @title:校验HTTPResponse结果是否成功
	 * @description:
	 * @param http
	 * @return
	 * @author:zdx
	 * @date:2017年11月24日
	 */
	private HttpResult verifyHttpResult(HTTPResponse http){
		HttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), HttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

	/**
	 * 批量获取订单信息,返回的可能是子订单也可能是拆单
	 * @param param
	 * @return
	 */
	public BatchOrderDetailListPO getOrderOrSplitOrder(OrderIdsBO param){
		BatchOrderIdListBO batch = new BatchOrderIdListBO();
		batch.setKeyArea(param.getPartnerArea());
		batch.setOrderIds(param.getChildOrderIds());
		HttpResult httpResult = getOrderDeatilByIdList(batch);
		BatchOrderDetailListPO batchOrderDetailListPO = null;
		if(!ObjectUtils.isEmpty(httpResult.getData())){
			batchOrderDetailListPO = gson.fromJson(gson.toJson(httpResult.getData()),BatchOrderDetailListPO.class);
		}
		return  batchOrderDetailListPO;
	}
	
	/**
	 * 批量获取订单详细信息
	 * @param param
	 * @return
	 */
	public List<ChildOrderBO> getChildOrderList(OrderIdsBO param){
		List<ChildOrderBO> childOrderBOList = new ArrayList<>();
		BatchOrderIdListBO batch = new BatchOrderIdListBO();
		batch.setKeyArea(param.getPartnerArea());
		batch.setOrderIds(param.getChildOrderIds());
		HttpResult httpResult = getOrderDeatilByIdList(batch);
		BatchOrderDetailListPO batchOrderDetailListPO = null;
		if(!ObjectUtils.isEmpty(httpResult.getData())){
			batchOrderDetailListPO = gson.fromJson(gson.toJson(httpResult.getData()),BatchOrderDetailListPO.class);
			List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
	        List<WarehouseOrderDetailPO> joinOrderParamInfo =joinOrderParamInfo(orderList);
	        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
	        	ChildOrderBO child = new ChildOrderBO();
				BeanUtils.copyProperties(warehouseOrderDetailPO, child);
				childOrderBOList.add(child);
			}
	        return  childOrderBOList;
		}else{
			return null;
		}
	}

}
