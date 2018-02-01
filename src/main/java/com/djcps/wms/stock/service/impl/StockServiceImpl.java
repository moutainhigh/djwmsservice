package com.djcps.wms.stock.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.request.UpdateOrderHttpRequest;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.order.service.OrderService;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.MapLocationPO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.RecommendLocaParamBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.server.StockServer;
import com.djcps.wms.stock.service.StockService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 入库移库业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@Service
public class StockServiceImpl implements StockService{

	@Autowired
	private StockServer stockServer;
	
	@Autowired
	private OrderServer orderServer;
	
	@Autowired
	private AllocationServer allocationServer;
	
	@Autowired
	private StockService stockService;
	
	private JsonParser jsonParser = new JsonParser();
	
	private Gson gson = new Gson();
	
	@Override
	public Map<String, Object> getRecommendLoca(RecommendLocaBO param) {
		StringBuilder bulider1 = new StringBuilder();
		List<RecommendLocaParamBO> addList = new ArrayList<RecommendLocaParamBO>();
		String location = param.getLocation();
		//location要求小数点显示后六位
		String newLocation = "";
		String[] split = location.split(",");
		for(int i =0;i<=split.length-1;i++){
			String str = split[i];
			int indexOf = str.indexOf(".");
			String substring = str.substring(indexOf+1);
			if(substring.length()>6){
				String str1 = str.substring(0,indexOf);
				String str2 = str.substring(indexOf+1,indexOf+7);
				String str3  = new StringBuilder().append(str1).append(".").append(str2).toString();
				if(i == 0){
					bulider1.append(str3);
				}else{
					bulider1.append(",").append(str3);
				}
			}else{
				if(i == 0){
					bulider1.append(split[i]);
				}else{
					bulider1.append(",").append(split[i]);
				}
			}
		}
		newLocation = bulider1.toString();
		//key表示高德地图api的需要的key,location表示经纬度,output输出格式
		String key=AppConstant.MAP_API_KEY;
		String output="JSON";
		MapLocationPO mapLocationPo = stockServer.getStreetCode(key,newLocation,output);
		RecommendLocaParamBO rl = new RecommendLocaParamBO();
		rl.setPartnerId(param.getPartnerId());
		rl.setStreetCode(mapLocationPo.getStreetCode());
		addList.add(rl);
		param.setParam(addList);
		HttpResult result = stockServer.getRecommendLoca(param);
		if(!ObjectUtils.isEmpty(result.getData())){
			ArrayList data = (ArrayList) result.getData();
			result.setData(data.get(0));
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getOperationRecord(OrderIdBO fromJson) {
		HttpResult result = stockServer.getOperationRecord(fromJson);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addStock(AddStockBO param) {
		ArrayList<OrderIdBO> list = new ArrayList<OrderIdBO>();
		//订单号
		String orderId = param.getOrderId();
		//入库数量
		Integer saveAmount = param.getAmountSave();
		//订单数量
		Integer orderAmount = param.getAmount();
		SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		BeanUtils.copyProperties(param, selectAreaByOrderId);
		OrderIdBO orderIdBO = new OrderIdBO();
		orderIdBO.setOrderId(orderId);
		list.add(orderIdBO);
		selectAreaByOrderId.setOrderIds(list);
		//解析在库信息
		Map<String, Object> areaByOrderIdMap = stockService.getAreaByOrderId(selectAreaByOrderId);
		Object object = areaByOrderIdMap.get("data");
		if(!ObjectUtils.isEmpty(object)){
			JsonArray asJsonArray = jsonParser.parse(gson.toJson(object)).getAsJsonArray();
			Integer trueAmount = asJsonArray.get(0).getAsJsonObject().get("amountSaved").getAsInt();
			if(trueAmount+saveAmount>orderAmount){
				return MsgTemplate.failureMsg(SysMsgEnum.SAVE_AMOUNT_ERROE);
			}else if(trueAmount+saveAmount==orderAmount){
				//相等表示已入库修改订单状态
				orderIdBO.setStatus(AppConstant.ALL_ADD_STOCK);
			}else{
				//小于表示部分入库
				orderIdBO.setStatus(AppConstant.LESS_ADD_STOCK);
			}
		}else{
			if(saveAmount > orderAmount){
				return MsgTemplate.failureMsg(SysMsgEnum.SAVE_AMOUNT_ERROE);
			}else if(saveAmount.equals(orderAmount)){
				//相等表示已入库修改订单状态
				orderIdBO.setStatus(AppConstant.ALL_ADD_STOCK);
			}else{
				//小于表示部分入库
				orderIdBO.setStatus(AppConstant.LESS_ADD_STOCK);
			}
		}
		HttpResult updateOrderResult = orderServer.updateOrderStatus(orderIdBO);
		//插入冗余表数据
		String order = param.getOrderId();
		OrderIdBO ord = new OrderIdBO();
		ord.setChildOrderId(order);
		HttpResult result = orderServer.getOrderByOrderId(ord);
		if(!ObjectUtils.isEmpty(result)){
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//组织参数
			WarehouseOrderDetailPO fromJson = gson.fromJson(gson.toJson(result.getData()), WarehouseOrderDetailPO.class);
			AddOrderRedundantBO orderRedundant = new AddOrderRedundantBO();
			BeanUtils.copyProperties(param, orderRedundant);
			orderRedundant.setBoxHeight(fromJson.getFboxheight());
			orderRedundant.setBoxLength(fromJson.getFboxlength());
			orderRedundant.setBoxWidth(fromJson.getFboxwidth());
			orderRedundant.setCustomerName(fromJson.getFcusername()==null?fromJson.getFpusername():fromJson.getFcusername());
			orderRedundant.setDeliveryTime(sd.format(fromJson.getFdelivery()));
			orderRedundant.setMaterialLength(fromJson.getFmateriallength());
			orderRedundant.setMaterialWidth(fromJson.getFmaterialwidth());
			orderRedundant.setMaterialName(fromJson.getFmaterialname());
			orderRedundant.setOrderId(fromJson.getFchildorderid());
			orderRedundant.setOrderTime(sd.format(fromJson.getFordertime()));
			orderRedundant.setProductName(fromJson.getFgroupgoodname());
			orderRedundant.setPaymentTime(sd.format(fromJson.getFpaymenttime()));
			orderRedundant.setStatus(fromJson.getFstatus());
			HttpResult orderRedundantResult = allocationServer.batchAddOrderRedundant(orderRedundant);
			if(!orderRedundantResult.isSuccess()){
				return MsgTemplate.failureMsg(SysMsgEnum.REDUNDANT_FAIL);
			}
		}
		//订单状态修改失败
		if(!updateOrderResult.isSuccess()){
			return MsgTemplate.failureMsg(SysMsgEnum.ORDER_UPDATE_ERROR);
		}
		//入库
		HttpResult addStockresult = stockServer.addStock(param);
		return MsgTemplate.customMsg(addStockresult);
	}

	@Override
	public Map<String, Object> moveStock(MoveStockBO param) {
		HttpResult result = stockServer.moveStock(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getSavedStockAmount(SelectSavedStockAmountBO param) {
		HttpResult result = stockServer.getSavedStockAmount(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getAreaByOrderId(SelectAreaByOrderIdBO param) {
		HttpResult result = stockServer.getAreaByOrderId(param);
		return MsgTemplate.customMsg(result);
	}

}
