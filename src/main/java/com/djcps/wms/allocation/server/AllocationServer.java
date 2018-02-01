package com.djcps.wms.allocation.server;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.djcps.wms.allocation.model.MoveOrderPO;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.model.VerifyAllocationBO;
import com.djcps.wms.allocation.model.ZhiNengHttpResult;
import com.djcps.wms.allocation.request.NumberServerHttpRequest;
import com.djcps.wms.allocation.request.WmsForAllocationHttpRequest;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.base.BaseUpdateAndDeleteBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.httpclient.OtherHttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.order.model.OrderParamBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AllocationServer.class);	
	
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
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderType(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getChooseAllocation(PartnerInfoBO partnern) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(partnern);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getChooseAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult saveAllocation(AddAllocationBO allocation) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(allocation);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.saveAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getOrderIdByOrderType(GetOrderIdByOrderType param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderIdByOrderType(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	public HttpResult getNumber(int count) {
		//将请求参数转化为requestbody格式
//		String json = gson.toJson(deliveryList);
//		System.out.println("---http请求参数转化为json格式---:"+json);
		String param = "count="+count;
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),param);
		//调用借口获取信息
		HTTPResponse http = numberServerHttpRequest.getNumber(rb);
		return verifyHttpResult(http);
	}
	public HttpResult zhinengpeihuo() {
		//将请求参数转化为requestbody格式
//		String json = gson.toJson(null);
		String json =  "{\"id\":11,\"name\":\"zhagnsan\"}";
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.zhinengpeihuo(rb);
		//调用借口获取信息
		return verifyHttpResult(http);
	}
	
	public HttpResult verifyAllocation(VerifyAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.verifyAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult moveOrder(MoveOrderPO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.moveOrder(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getAddOrderList(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAddOrderList(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
		
	}
	
	public HttpResult verifyAddOrder(List<AddAllocationOrderBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.verifyAddOrder(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getExcellentLoding(GetExcellentLodingBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getExcellentLoding(rb);
		return verifyHttpResult(http);
	}
	

	public HttpResult againVerifyAddOrder(AgainVerifyAddOrderBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.againVerifyAddOrder(rb);
		return verifyHttpResult(http);
	}

	public HttpResult againVerifyAllocation(List<AgainVerifyAllocationBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.againVerifyAllocation(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getAllUserCarInfo() {
		//将请求参数转化为requestbody格式
//		String json = gson.toJson(param);
//		System.out.println("---http请求参数转化为json格式---:"+json);
//		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAllUserCarInfo();
		return verifyHttpResult(http);
	}
	
	public HttpResult changeCarInfo(ChangeCarInfoBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.changeCarInfo(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult cancelAllocation(CancelAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.cancelAllocation(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getOrderByDeliveryId(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderByDeliveryId(rb);
		return verifyHttpResult(http);
	}

	public HttpResult getRedundantAttribute(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getRedundantAttribute(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getDeliveryByOrderIds(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getDeliveryByOrderIds(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getWaybillByDeliveryIds(List<String> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getWaybillByDeliveryIds(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult getDeliveryByWaybillIds(GetDeliveryByWaybillIdsBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getDeliveryByWaybillIds(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getAlloManageFuzzyQuery(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAlloManageFuzzyQuery(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public OtherHttpResult getAlloManageQuery(GetRedundantByAttributeBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAlloManageQuery(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}

	public HttpResult getAllocationOrderTypes(BaseUpdateAndDeleteBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getAllocationOrderTypes(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addzhinengpeihuo() {
		//将请求参数转化为requestbody格式
//		String json = gson.toJson(param);
//		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),"1");
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addzhinengpeihuo(rb);
		return verifyHttpResult(http);
	}

	public HttpResult addDeliAllocOrder(List<WarehouseOrderDetailPO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addDeliAllocOrder(rb);
		return verifyHttpResult(http);
	}
	
	public OtherHttpResult getOrderByAllocationId(GetIntelligentAllocaBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.getOrderByAllocationId(rb);
		OtherHttpResult result = null;
		//校验请求是否成功
		if(http.isSuccessful()){
			result = gson.fromJson(http.getBodyString(), OtherHttpResult.class);
		}
		if(result == null){
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
	public HttpResult batchAddOrderRedundant(AddOrderRedundantBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.batchAddOrderRedundant(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult batchUpdateOrderRedun(List<UpdateOrderRedundantBO> param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.batchUpdateOrderRedun(rb);
		return verifyHttpResult(http);
	}
	
	public HttpResult addExcellentAllocation(AddExcellentAllocationBO param) {
		//将请求参数转化为requestbody格式
		String json = gson.toJson(param);
		System.out.println("---http请求参数转化为json格式---:"+json);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = allocationHttpRequest.addExcellentAllocation(rb);
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
			System.err.println("Http请求出错,HttpResult结果为null");
			logger.error("Http请求出错,HttpResult结果为null");
		}
		return result;
	}
	
}