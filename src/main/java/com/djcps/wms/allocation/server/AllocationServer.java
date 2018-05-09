package com.djcps.wms.allocation.server;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.allocation.model.AddAllocationBO;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.allocation.model.AddExcellentAllocationBO;
import com.djcps.wms.allocation.model.AgainVerifyAddOrderBO;
import com.djcps.wms.allocation.model.AgainVerifyAllocationBO;
import com.djcps.wms.allocation.model.CancelAllocationBO;
import com.djcps.wms.allocation.model.ChangeCarInfoBO;
import com.djcps.wms.allocation.model.GetDeliveryByWaybillIdsBO;
import com.djcps.wms.allocation.model.GetExcellentLodingBO;
import com.djcps.wms.allocation.model.GetIntelligentAllocaBO;
import com.djcps.wms.allocation.model.GetOrderIdByOrderType;
import com.djcps.wms.allocation.model.GetRedundantByAttributeBO;
import com.djcps.wms.allocation.model.MergeModelBO;
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.RelativeIdBO;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.request.NumberServerHttpRequest;
import com.djcps.wms.allocation.request.WmsForAllocationHttpRequest;
import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.request.WmsForOrderHttpRequest;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.google.gson.Gson;

import rpc.plugin.http.HTTPResponse;

/**
 * @title:混合配货服务
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月23日
 */
@Component
public class AllocationServer {
	
	private static final DjcpsLogger LOGGER  = DjcpsLoggerFactory.getLogger(AllocationServer.class);	
	
	private Gson gson = new Gson();
	
	@Autowired
	private WmsForAllocationHttpRequest allocationHttpRequest;
	
	@Autowired
	private NumberServerHttpRequest numberServerHttpRequest;
	
	@Autowired
	private WmsForOrderHttpRequest wmsForOrderHttpRequest;
	
	public HttpResult getOrderType(BaseBO baseBO) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(baseBO);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderType(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getChooseAllocation(PartnerInfoBO partnern) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(partnern);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getChooseAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult saveAllocation(AddAllocationBO allocation) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(allocation);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.saveAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getOrderIdByOrderType(GetOrderIdByOrderType param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderIdByOrderType(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	public HttpResult getNumber(int count) {
		//将请求参数转化为requestbody格式
		String param = "count="+count;
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),param);
		//调用借口获取信息
		HTTPResponse http = numberServerHttpRequest.getNumber(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult verifyAllocation(VerifyAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.verifyAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAddOrderList(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAddOrderList(rb);
		return verifyHttpResult(http);
		
	}
	
	public HttpResult getErrorAddOrderList(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getErrorAddOrderList(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAddOrderListByParamisNull(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAddOrderListByParamisNull(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult verifyAddOrder(List<AddAllocationOrderBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.verifyAddOrder(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getExcellentLoding(GetExcellentLodingBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getExcellentLoding(rb);
		return verifyHttpResult(http);
	}
	

	public HttpResult againVerifyAddOrder(AgainVerifyAddOrderBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.againVerifyAddOrder(rb);
		return verifyHttpResult(http);
	}

	public HttpResult againVerifyAllocation(List<AgainVerifyAllocationBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.againVerifyAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllUserCarInfo() {
		//将请求参数转化为requestbody格式
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAllUserCarInfo();
		return verifyHttpResult(http);
	}
	
	public HttpResult changeCarInfo(ChangeCarInfoBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.changeCarInfo(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult cancelAllocation(CancelAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.cancelAllocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getOrderByDeliveryId(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderByDeliveryId(rb);
		return verifyHttpResult(http);
	}

	public OtherHttpResult getRedundantAttribute(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getRedundantAttribute(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public HttpResult getDeliveryByOrderIds(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getDeliveryByOrderIds(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getWaybillByDeliveryIds(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getWaybillByDeliveryIds(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getDeliveryByWaybillIds(GetDeliveryByWaybillIdsBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getDeliveryByWaybillIds(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getAlloManageFuzzyQuery(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAlloManageFuzzyQuery(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public OtherHttpResult getAlloManageQuery(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAlloManageQuery(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

	public HttpResult getAllocationOrderTypes(BaseUpdateAndDeleteBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAllocationOrderTypes(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addzhinengpeihuo(BaseAddBO base) {
		String json = gson.toJson(base);
		//将请求参数转化为requestbody格式
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addzhinengpeihuo(rb);
		return verifyHttpResult(http);
	}

	public HttpResult addDeliAllocOrder(List<WarehouseOrderDetailPO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addDeliAllocOrder(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getOrderByAllocationId(GetIntelligentAllocaBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderByAllocationId(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			LOGGER.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public HttpResult batchAddOrderRedundant(AddOrderRedundantBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.batchAddOrderRedundant(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult batchUpdateOrderRedun(List<UpdateOrderRedundantBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.batchUpdateOrderRedun(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addExcellentAllocation(AddExcellentAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addExcellentAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult managerMoveOrder(MoveOrderPO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.managerMoveOrder(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult allocationMoveOrder(MoveOrderPO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.allocationMoveOrder(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAlreadyAllocOrder(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAlreadyAllocOrder(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult againVerifyAllocation(MergeModelBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.againVerifyAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getOrderByOrderIds(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderByOrderIds(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult existIntelligentAlloca(String allocationId) {
		//将请求参数转化为requestbody格式
		String json = "{\"allocationId\":"+allocationId+"}";
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.existIntelligentAlloca(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getRecordByRrelativeId(RelativeIdBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getRecordByRrelativeId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getDeliveryTableId(VerifyAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getDeliveryTableId(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult splitOrder(UpdateOrderBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.splitOrder(rb);
		return verifyHttpResult(http);
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

}