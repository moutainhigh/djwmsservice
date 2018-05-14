package com.djcps.wms.order.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.djcps.wms.order.constant.OrderConstant;
import com.djcps.wms.order.model.ChildOrderBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.offlinepaperboard.OfflineQueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.QueryObjectBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.request.OffinePaperboardRequest;
import com.djcps.wms.order.request.OnlinePaperboardRequest;
import com.djcps.wms.order.request.UpdateOrderHttpRequest;
import com.djcps.wms.order.request.WmsForOrderHttpRequest;
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
	
	private Gson gson = GsonUtils.gson;
	
	private Gson dataFormatGson = GsonUtils.dataFormatGson;
	
	private Gson gsonNotNulL = GsonUtils.gsonNotNulL;
	
	@Autowired
	private StockServer stockServer;
	
	@Autowired
	OnlinePaperboardRequest onlinePaperboardRequest;
	
	@Autowired
	OffinePaperboardRequest offinePaperboardRequest;
	
	@Autowired
	private WmsForOrderHttpRequest orderHttpRequest;
	
	@Autowired
	private AllocationServer allocationServer;
	 
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
        return updateOMSCode(http);
	}
	
	/**
	 * 修改子单信息接口信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	public HttpResult updateOrderInfo(List<UpdateOrderBO> param) {
		List<UpdateOrderBO> onlineList = new ArrayList<>();
		List<UpdateOrderBO> offlineList = new ArrayList<>();
		for (UpdateOrderBO updateOrderBO : param) {
			String orderId = updateOrderBO.getOrderId();
			if(orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER_CGR)!=-1){
				onlineList.add(updateOrderBO);
	        }else if(orderId.indexOf(OrderConstant.OFFLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.OFFLINE_BOX_ORDER)!=-1){
	        	offlineList.add(updateOrderBO);
	        }
		}
		if(!ObjectUtils.isEmpty(onlineList)){
			param = onlineList;
    	}else{
    		param = offlineList;
    	}
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http = null;
    	if(!ObjectUtils.isEmpty(onlineList)){
    		//调用借口获取信息
        	http = onlinePaperboardRequest.updateOrderInfo(rb);
    	}else{
    		//调用借口获取信息
        	http = offinePaperboardRequest.updateOrderInfo(rb);
    	}
        //校验请求是否成功
        return updateOMSCode(http);
	}
	
	/**
	 * 修改拆单信息
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月19日
	 */
	public HttpResult updateSplitOrderInfo(List<UpdateSplitOrderBO> param) {
        List<UpdateSplitOrderBO> onlineList = new ArrayList<>();
		List<UpdateSplitOrderBO> offlineList = new ArrayList<>();
		for (UpdateSplitOrderBO updateOrderBO : param) {
			String orderId = updateOrderBO.getSubOrderId();
			if(orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER_CGR)!=-1){
				onlineList.add(updateOrderBO);
	        }else if(orderId.indexOf(OrderConstant.OFFLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.OFFLINE_BOX_ORDER)!=-1){
	        	offlineList.add(updateOrderBO);
	        }
		}
		if(!ObjectUtils.isEmpty(onlineList)){
			param = onlineList;
    	}else{
    		param = offlineList;
    	}
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http = null;
    	if(!ObjectUtils.isEmpty(onlineList)){
    		//调用借口获取信息
        	http = onlinePaperboardRequest.updateSplitOrderInfo(rb);
    	}else{
    		//调用借口获取信息
        	http = offinePaperboardRequest.updateSplitOrderInfo(rb);
    	}
        //校验请求是否成功
        return updateOMSCode(http);
        
        
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
		List<UpdateOrderBO> orderUpdateList = new ArrayList<>();
		List<UpdateSplitOrderBO> splitOrderUpdateList = new ArrayList<>();
		for (OrderIdBO orderIdBO : orderIdList) {
			String string = orderIdBO.getOrderId();
			String orderStatus = orderIdBO.getStatus();
			//这里首先需要判断,这个orderId是子订单号还是拆分订单号
			 int indexOf = string.indexOf(LoadingTaskConstant.SUBSTRING_ORDER);
			 if(indexOf==-1){
				//等于-1表示没有携带-是子订单号
			 	UpdateOrderBO update = new UpdateOrderBO();
	        	update.setOrderId(string);
	        	update.setOrderStatus(orderStatus);
	        	update.setKeyArea(partnerArea);
	        	orderUpdateList.add(update);
			 }else{
				//否则就是拆分订单号
				UpdateSplitOrderBO update = new UpdateSplitOrderBO();
	    		update.setSubOrderId(string);
	    		update.setSubStatus(Integer.valueOf(orderStatus));
	    		update.setKeyArea(partnerArea);
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
	public Boolean compareOrderStatus(List<String> orderIdList,String partnerArea,String partnerId){
		Boolean flag = false;
		if(!ObjectUtils.isEmpty(orderIdList)){
			List<String> orderIds = new ArrayList<>();
			Set<String> orderSet = new HashSet<>();
			for (String str : orderIdList) {
				if(str.indexOf(LoadingTaskConstant.SUBSTRING_ORDER)!=-1){
					str = str.substring(0, str.indexOf(LoadingTaskConstant.SUBSTRING_ORDER));
					orderSet.add(str);
				}
			}
			if(orderSet.size()==0){
				return true;
			}
			for (String str : orderSet) {
				orderIds.add(str);
			}
			
			BatchOrderIdListBO batch = new BatchOrderIdListBO();
			batch.setOrderIds(orderIds);
			batch.setKeyArea(partnerArea);
			HttpResult orderDeatilByIdList = getOrderDeatilByIdList(batch);
			BatchOrderDetailListPO batchOrderDetailListPO = dataFormatGson.fromJson(gsonNotNulL.toJson(orderDeatilByIdList.getData()),BatchOrderDetailListPO.class);
	        List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
	        List<WarehouseOrderDetailPO> joinOrderParamInfo = joinOrderParamInfo(orderList);
	        List<OrderIdBO> orderIdBOList = new ArrayList<>();
	        Map<String,WarehouseOrderDetailPO> orderDetailMap = new HashMap<>();
	        for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
	        	OrderIdBO orderIdBO = new OrderIdBO();
	        	orderIdBO.setOrderId(warehouseOrderDetailPO.getOrderId());
	        	orderIdBO.setKeyArea(partnerArea);
	        	orderIdBOList.add(orderIdBO);
	        	orderDetailMap.put(warehouseOrderDetailPO.getOrderId(), warehouseOrderDetailPO);
			}
	        HttpResult splitOrderResult  = getSplitOrderDeatilByIdList(orderIdBOList);
	        Map<String,List<WarehouseOrderDetailPO>> detailMap = dataFormatGson.fromJson(gsonNotNulL.toJson(splitOrderResult.getData()), new TypeToken<Map<String,List<WarehouseOrderDetailPO>>>(){}.getType());
	        for(Map.Entry<String, List<WarehouseOrderDetailPO>> entry : detailMap.entrySet()){
	        	List<WarehouseOrderDetailPO> detailValueList = entry.getValue();
	        	if(!ObjectUtils.isEmpty(detailValueList)){
	        		for(WarehouseOrderDetailPO orderDetailPO:detailValueList){
		        		Integer orderStatus = orderDetailMap.get(orderDetailPO.getOrderId()).getOrderStatus();
		        		String splitOrderStatus = String.valueOf(orderDetailPO.getSubStatus());
		        		
		        		if(splitOrderStatus.equals(OrderStatusTypeEnum.NO_STOCK) || splitOrderStatus.equals(OrderStatusTypeEnum.LESS_ADD_STOCK) 
		        				|| splitOrderStatus.equals(OrderStatusTypeEnum.ALL_ADD_STOCK)|| splitOrderStatus.equals(OrderStatusTypeEnum.ORDER_ALREADY_ALLOCATION)
		        				||splitOrderStatus.equals(OrderStatusTypeEnum.ORDER_ALREADY_DELIVERY) || splitOrderStatus.equals(OrderStatusTypeEnum.ORDER_ALREADY_LOADING) 
		        				|| splitOrderStatus.equals(OrderStatusTypeEnum.ORDER_ALREADY_GOTO) ){
		        			
			        		//假如拆单状态小于子单状态则把状态赋值给子单
			        		if(Integer.valueOf(splitOrderStatus).intValue()<orderStatus.intValue()){
			        			//一个开关代表需要进行修改
			        			flag = true;
			        			orderDetailMap.get(orderDetailPO.getOrderId()).setOrderStatus(orderDetailPO.getSubStatus());
			        			//这里暂时拿这个字段拿来用只是为了判断orderStatus进行了修改需要进行update操作
			        			orderDetailMap.get(orderDetailPO.getOrderId()).setAmountPiece(666);
			        		}
		        		}
		        	}
	        	}
	        	
	        }
	        if(flag == true){
	        	List<UpdateOrderBO> updateList = new ArrayList<>();
	        	List<UpdateOrderRedundantBO> orderRedundantBOList = new ArrayList<>();
	 	        for(Map.Entry<String,WarehouseOrderDetailPO> entry : orderDetailMap.entrySet()){
	 	        	WarehouseOrderDetailPO orderDetailValue = entry.getValue();
	 	        	if(orderDetailValue.getAmountPiece()==666){
	 	        		UpdateOrderBO update = new UpdateOrderBO();
		  	        	update.setOrderId(orderDetailValue.getOrderId());
		  	        	update.setOrderStatus(String.valueOf(orderDetailValue.getOrderStatus()));
		  	        	update.setKeyArea(partnerArea);
		  	        	updateList.add(update);
		  	        	
		  	        	//修改冗余表参数
		  	        	UpdateOrderRedundantBO updateOrderRedundant = new UpdateOrderRedundantBO();
		  	        	updateOrderRedundant.setOrderId(orderDetailValue.getOrderId());
		  	        	updateOrderRedundant.setStatus(orderDetailValue.getOrderStatus());
		  	        	updateOrderRedundant.setPartnerId(partnerId);
		  	        	orderRedundantBOList.add(updateOrderRedundant);
	 	        	}
	 	        }
	 	        HttpResult result = updateOrderInfo(updateList);
	 	        //关闭开关
	 	        flag = false;
	 	    	if(!result.isSuccess()){
	 	    		LOGGER.error("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
	     		}else{
	     			//这里已经写好逻辑了,所有这里暂时就不做修改了,应该是先修改wms的订单状态在修改oms
	     			//修改冗余表订单状态
	     			result = allocationServer.batchUpdateOrderRedun(orderRedundantBOList);
	     		}
	 			return result.isSuccess();
	        }else{
	        	return true;
	        }
		}
		return true;
	}
	
	public HttpResult getOrderDeatilByIdList(BatchOrderIdListBO param) {
		List<String> orderIdList = param.getOrderIds();
		String orderId = orderIdList.get(0);
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        HTTPResponse http = null;
		if(orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER_CGR)!=-1){
			http = onlinePaperboardRequest.getOnlinePaperboardByIdList(rb);
        }else if(orderId.indexOf(OrderConstant.OFFLINE_PAPERBOARD_ORDER)!=-1){
        	http = offinePaperboardRequest.getOfflinePaperboardByIdList(rb);
        }else if(orderId.indexOf(OrderConstant.OFFLINE_BOX_ORDER)!=-1){
        	http = offinePaperboardRequest.getOfflineBoxOrderByIdList(rb);
        }
        //校验请求是否成功
        return updateOMSCode(http);
	}
	
	/**
	 * 根据订单号获取拆单信息,支持批量
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2018年4月20日
	 */
	public HttpResult getSplitOrderDeatilByIdList(List<OrderIdBO> param) {
		String orderId = param.get(0).getOrderId();
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = null;
        if(orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER_CGR)!=-1){
			http = onlinePaperboardRequest.getSplitOrderDeatilByI(rb);
        }else if(orderId.indexOf(OrderConstant.OFFLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.OFFLINE_BOX_ORDER)!=-1){
        	http = offinePaperboardRequest.getSplitOrderDeatilByI(rb);
        }
        //校验请求是否成功
        return updateOMSCode(http);
	}
	
	public HttpResult splitOrder(UpdateOrderBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = null;
        String orderId = param.getOrderId();
		if(orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.ONLINE_PAPERBOARD_ORDER_CGR)!=-1){
			//调用借口获取信息
        	http = onlinePaperboardRequest.splitOrder(rb);
        }else if(orderId.indexOf(OrderConstant.OFFLINE_PAPERBOARD_ORDER)!=-1 || orderId.indexOf(OrderConstant.OFFLINE_BOX_ORDER)!=-1){
        	//调用借口获取信息
        	http = offinePaperboardRequest.splitOrder(rb);
        }
        //校验请求是否成功
        return updateOMSCode(http);
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
			onlinePaperboardPO.setAddressDetailProvince(new StringBuffer().append(onlinePaperboardPO.getCodeProvince()).append(onlinePaperboardPO.getAddressDetail()).toString());
			String customerName = null;
			if(!StringUtils.isEmpty(onlinePaperboardPO.getCuserName())){
				customerName = onlinePaperboardPO.getCuserName();
			}else if(!StringUtils.isEmpty(onlinePaperboardPO.getPuserName())){
				customerName = onlinePaperboardPO.getPuserName();
			}else{
				customerName = onlinePaperboardPO.getCustomerName();
			}
			if(!StringUtils.isEmpty(onlinePaperboardPO.getFluteTypeString())){
				onlinePaperboardPO.setFluteType(onlinePaperboardPO.getFluteTypeString());
			}
			onlinePaperboardPO.setCustomerName(customerName);
			String subOrderId = onlinePaperboardPO.getSubOrderId();
			if(StringUtils.isEmpty(subOrderId)){
				//为空则表示子单,否则就是拆单	
				onlinePaperboardPO.setOrderAmount(onlinePaperboardPO.getAmountPiece());
				if(!StringUtils.isEmpty(onlinePaperboardPO.getChildOrderId())){
					onlinePaperboardPO.setOrderId(onlinePaperboardPO.getChildOrderId());
				}
			}else{
				onlinePaperboardPO.setOrderAmount(onlinePaperboardPO.getSubNumber());
				onlinePaperboardPO.setOrderId(subOrderId);
				onlinePaperboardPO.setOrderStatus(onlinePaperboardPO.getSubStatus());
			}
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
	
	public HttpResult getOffinePaperboardList(OfflineQueryObjectBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = offinePaperboardRequest.getOffinePaperboardList(rb);
        //校验请求是否成功
        return updateOMSCode(http);
	}
	
	public HttpResult getOffineBoxOrderList(OfflineQueryObjectBO param) {
		//将请求参数转化为requestbody格式
        String json = gson.toJson(param);
        okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //调用借口获取信息
        HTTPResponse http = offinePaperboardRequest.getOffineBoxOrderList(rb);
        //校验请求是否成功
        return updateOMSCode(http);
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
	 * 涉及到oms订单服务的内容,那边code码和我们这边不一致,一律全部修改成100000
	 * @param http
	 * @return
	 * @author:zdx
	 * @date:2018年5月2日
	 */
	private HttpResult updateOMSCode(HTTPResponse http){
		HttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), HttpResult.class);
			if(result.isSuccess()){
				result.setCode(100000);
			}
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
			batchOrderDetailListPO = dataFormatGson.fromJson(gsonNotNulL.toJson(httpResult.getData()),BatchOrderDetailListPO.class);
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
			batchOrderDetailListPO = dataFormatGson.fromJson(gsonNotNulL.toJson(httpResult.getData()),BatchOrderDetailListPO.class);
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
