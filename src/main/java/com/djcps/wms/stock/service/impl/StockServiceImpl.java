package com.djcps.wms.stock.service.impl;

import java.text.SimpleDateFormat;
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
import com.djcps.wms.abnormal.constant.AbnormalConstant;
import com.djcps.wms.abnormal.model.AbnormalOrderPO;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.allocation.constant.AllocationConstant;
import com.djcps.wms.allocation.model.UpdateOrderRedundantBO;
import com.djcps.wms.allocation.server.AllocationServer;
import com.djcps.wms.cancelstock.enums.CancelStockMsgEnum;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderIdListBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.stock.enums.StockMsgEnum;
import com.djcps.wms.stock.model.AddOrderRedundantBO;
import com.djcps.wms.stock.model.AddStockBO;
import com.djcps.wms.stock.model.BulitTypePO;
import com.djcps.wms.stock.model.MapLocationPO;
import com.djcps.wms.stock.model.MoveStockBO;
import com.djcps.wms.stock.model.RecommendLocaBO;
import com.djcps.wms.stock.model.RecommendLocaParamBO;
import com.djcps.wms.stock.model.SelectAreaByOrderIdBO;
import com.djcps.wms.stock.model.SelectSavedStockAmountBO;
import com.djcps.wms.stock.server.StockServer;
import com.djcps.wms.stock.service.StockService;
import com.djcps.wms.warehouse.server.WarehouseServer;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 入库移库业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
@Service
public class StockServiceImpl implements StockService{
	
	private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	private StockServer stockServer;
	
	@Autowired
	private OrderServer orderServer;
	
	@Autowired
	private AllocationServer allocationServer;
	
	@Autowired
	private StockService stockService;

	@Autowired
	private AbnormalServer abnormalServer;
	
	@Autowired
	private WarehouseServer warehouseServer;
	
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
		if(mapLocationPo!=null){
			RecommendLocaParamBO rl = new RecommendLocaParamBO();
			rl.setPartnerId(param.getPartnerId());
			rl.setStreetCode(mapLocationPo.getStreetCode());
			rl.setWarehouseId(param.getWarehouseId());
			addList.add(rl);
			param.setParam(addList);
			HttpResult result = stockServer.getRecommendLoca(param);
			if(!ObjectUtils.isEmpty(result.getData())){
				ArrayList data = (ArrayList) result.getData();
				result.setData(data.get(0));
			}
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> getOperationRecord(OrderIdBO fromJson) {
		HttpResult result = stockServer.getOperationRecord(fromJson);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addStock(AddStockBO param) {
		//先判断入库的仓库是否被启用,禁用直接驳回
		PartnerInfoBO partnerInfoBean = new PartnerInfoBO();
		BeanUtils.copyProperties(param, partnerInfoBean);
		HttpResult warehouseResult = warehouseServer.getAllWarehouseName(partnerInfoBean);
		if(ObjectUtils.isEmpty(warehouseResult.getData())){
			return MsgTemplate.failureMsg(SysMsgEnum.NO_HAVE_WAREHOUSE);
		}
		List<BulitTypePO> fromJsonDetailList = gson.fromJson(gson.toJson(warehouseResult.getData()), new TypeToken<ArrayList<BulitTypePO>>(){}.getType());
		Map<String,BulitTypePO> haveMap = new HashMap<>(16);
		for (BulitTypePO bulitTypePO : fromJsonDetailList) {
			haveMap.put(bulitTypePO.getWarehouseId(), bulitTypePO);
		}
		BulitTypePO bulitTypePO = haveMap.get(param.getWarehouseId());
		if(bulitTypePO==null){
			return MsgTemplate.failureMsg(SysMsgEnum.WAREHOUSE_ERROR);
		}
		//判断扫面或者网页端传来的订单号是否为拆分,是的话则需要进行查询另外订单
		if(param.getOrderId().indexOf("-")==-1){
			OrderIdBO newOrderIdBO = new OrderIdBO();
			BeanUtils.copyProperties(param, newOrderIdBO);
			HttpResult lessStockOrderResult = stockServer.getLessStockOrderId(newOrderIdBO);
			if(!ObjectUtils.isEmpty(lessStockOrderResult.getData())){
				param.setOrderId((String)lessStockOrderResult.getData());
			}
		}
		
		List<OrderIdBO> list = new ArrayList<OrderIdBO>();
		//订单号
		String orderId = param.getOrderId();
		//入库数量
		Integer saveAmount = param.getAmountSave();
		//订单数量
		Integer orderAmount = param.getAmount();
		SelectAreaByOrderIdBO selectAreaByOrderId = new SelectAreaByOrderIdBO();
		BeanUtils.copyProperties(param, selectAreaByOrderId);
		
		List<String> orderIdList = new ArrayList<>();
		orderIdList.add(orderId);
		
		OrderIdBO orderIdBO = new OrderIdBO();
		orderIdBO.setOrderId(orderId);
		list.add(orderIdBO);
		selectAreaByOrderId.setOrderIds(list);
		//解析在库信息
		List<WarehouseOrderDetailPO> orderDetail = orderServer.getOrderStockInfo(selectAreaByOrderId);
		//修改订单状态需要的参数
		if(!ObjectUtils.isEmpty(orderDetail)){
			Integer trueAmount = orderDetail.get(0).getAmountSaved();
			if(trueAmount+saveAmount>orderAmount){
				return MsgTemplate.failureMsg(StockMsgEnum.SAVE_AMOUNT_ERROE);
			}else if(trueAmount+saveAmount==orderAmount){
				//相等表示已入库修改订单状态
				orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
			}else{
				//小于表示部分入库
				orderIdBO.setStatus(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue());
			}
		}else{
			if(saveAmount > orderAmount){
				return MsgTemplate.failureMsg(StockMsgEnum.SAVE_AMOUNT_ERROE);
			}else if(saveAmount.equals(orderAmount)){
				//相等表示已入库修改订单状态
				orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
			}else{
				//小于表示部分入库
				orderIdBO.setStatus(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue());
			}
		}
		//入库
		HttpResult result = stockServer.addStock(param);
		if(result.isSuccess()){
			//OMS服务要求我们正常的订单也在他们的拆单表中生成假数据,并且修改订单状态
			List<UpdateOrderBO> updateOrderList = new ArrayList<>();
			UpdateOrderBO updateOrder = new UpdateOrderBO();
			updateOrder.setKeyArea(param.getPartnerArea());
			updateOrder.setOrderId(list.get(0).getOrderId());
			updateOrder.setOrderStatus(list.get(0).getStatus());
			
			List<UpdateSplitOrderBO> addSplitOrderList = new ArrayList<>();
			UpdateSplitOrderBO splitOrder = new UpdateSplitOrderBO();
			splitOrder.setOrderId(list.get(0).getOrderId());
			splitOrder.setSubOrderId(list.get(0).getOrderId());
			splitOrder.setKeyArea(param.getPartnerArea());
			splitOrder.setSubStatus(Integer.valueOf(list.get(0).getStatus()));
			addSplitOrderList.add(splitOrder);
			updateOrder.setSplitOrders(addSplitOrderList);
			updateOrderList.add(updateOrder);
			//OMS服务要求我们正常的订单也在他们的拆单表中生成假数据,并且修改订单状态
//			result = orderServer.splitOrder(updateOrderList);
			
			result  = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),list);
	        if(!result.isSuccess()){
	        	LOGGER.error("入库修改订单状态失败!!!");
	        	return MsgTemplate.failureMsg("入库修改订单状态失败!!!");
	        }
	        Boolean compareOrderStatus = orderServer.compareOrderStatus(orderIdList,  param.getPartnerArea());
	        if(compareOrderStatus==false){
	          return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
	        }
			
			
			
			
	        
	        
	        
	        
			if(result.isSuccess()){
				//插入冗余表数据
				BatchOrderIdListBO batchOrderIdListBO = new BatchOrderIdListBO();
				List<String> orderIdsList = new ArrayList<>();
				orderIdsList.add(param.getOrderId());
				batchOrderIdListBO.setOrderIds(orderIdsList);
				batchOrderIdListBO.setKeyArea(param.getPartnerArea());
				result = orderServer.getOrderDeatilByIdList(batchOrderIdListBO);
				if(!ObjectUtils.isEmpty(result.getData())){
					BatchOrderDetailListPO batchOrderDetailListPO = gson.fromJson(gson.toJson(result.getData()),BatchOrderDetailListPO.class);
					List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
					if(!ObjectUtils.isEmpty(orderList)){
						WarehouseOrderDetailPO onlinePaperboardPO = orderServer.joinOrderParamInfo(orderList).get(0);
						AddOrderRedundantBO orderRedundant = new AddOrderRedundantBO();
						BeanUtils.copyProperties(onlinePaperboardPO, orderRedundant);
						BeanUtils.copyProperties(param, orderRedundant);
						SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(!ObjectUtils.isEmpty(onlinePaperboardPO.getDeliveryTime())){
							orderRedundant.setDeliveryTime(sd.format(onlinePaperboardPO.getDeliveryTime()));
						}
						if(!ObjectUtils.isEmpty(onlinePaperboardPO.getOrderTime())){
							orderRedundant.setOrderTime(sd.format(onlinePaperboardPO.getOrderTime()));
						}
						if(!ObjectUtils.isEmpty(onlinePaperboardPO.getPayTime())){
							orderRedundant.setPaymentTime(sd.format(onlinePaperboardPO.getPayTime()));
						}
						orderRedundant.setStatus(onlinePaperboardPO.getOrderStatus());
						orderRedundant.setIsSplit(onlinePaperboardPO.getSplitOrder());
						//插入冗余数据订单数据
						result = allocationServer.batchAddOrderRedundant(orderRedundant);
						if(!result.isSuccess()){
							return MsgTemplate.failureMsg(StockMsgEnum.REDUNDANT_FAIL);
						}
					}
					//判断该订单是否是已入库状态,是的话修改冗余表修改为已入库
					if(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue().equals(orderIdBO.getStatus())){
						//修改冗余表订单状态为已入库
						List<UpdateOrderRedundantBO> updateList = new ArrayList<>();
						UpdateOrderRedundantBO update = new UpdateOrderRedundantBO();
						update.setStatus(Integer.valueOf(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue()));
						update.setOrderId(param.getOrderId());
						update.setPartnerId(param.getPartnerId());
						updateList.add(update);
						allocationServer.batchUpdateOrderRedun(updateList);
					}
					//异常订单逻辑
					OrderIdListBO orderIdListBO = new OrderIdListBO();
					BeanUtils.copyProperties(param, orderIdListBO);
					orderIdListBO.setList(new ArrayList<>());
					orderIdListBO.getList().add(param.getOrderId());
					HttpResult orderResult= abnormalServer.getOrderByOrderIdList(orderIdListBO);
					if(ObjectUtils.isEmpty(orderResult.getData())){
						if(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(orderIdBO.getStatus())){
							//剩余的异常订单数量
							Integer surplusOrderAmount= orderAmount-saveAmount;
							//直接插入异常订单数据
							List<WarehouseOrderDetailPO> sonOrderList = batchOrderDetailListPO.getOrderList();
							List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
							List<WarehouseOrderDetailPO> detail = !ObjectUtils.isEmpty(sonOrderList)?sonOrderList:splitOrderList;
							WarehouseOrderDetailPO orderDeatil = orderServer.joinOrderParamInfo(detail).get(0);
							AddAbnormal addAbnormal = new AddAbnormal();
							BeanUtils.copyProperties(param, addAbnormal);
							addAbnormal.setLink(AbnormalConstant.ABNORMAL_LINK_ADD_STOCK);
							addAbnormal.setReason(new StringBuffer().append(AbnormalConstant.ABNORMAL_ERROR_REASON).append(surplusOrderAmount).toString());
							addAbnormal.setAbnomalAmount(surplusOrderAmount);
							addAbnormal.setCustomerName(orderDeatil.getCustomerName());
							addAbnormal.setProductName(orderDeatil.getProductName());
							addAbnormal.setIsSplit(orderDeatil.getSplitOrder());
							HttpResult addResult = abnormalServer.addAbnormal(addAbnormal);
							return  MsgTemplate.customMsg(addResult);
						}
					}else{
						UpdateAbnormalBO updateOrderBO = new UpdateAbnormalBO();
						BeanUtils.copyProperties(param, updateOrderBO);
						HttpResult abnormalResult =null;
						if(OrderStatusTypeEnum.LESS_ADD_STOCK.getValue().equals(orderIdBO.getStatus())){
							AbnormalOrderPO abnormalFromJson = gson.fromJson(gson.toJson(jsonParser.parse(gson.toJson(orderResult.getData())).getAsJsonArray().get(0)),
									AbnormalOrderPO.class);
							//异常数量
							Integer abnomalAmount = abnormalFromJson.getAbnomalAmount();
							//剩余的异常订单数量
							Integer surplusOrderAmount= abnomalAmount-saveAmount;
							//仍然为异常订单,修改异常数量即可
							updateOrderBO.setAbnomalAmount(String.valueOf(surplusOrderAmount));
							updateOrderBO.setReason(new StringBuffer().append(AbnormalConstant.ABNORMAL_ERROR_REASON).append(surplusOrderAmount).toString());
							updateOrderBO.setSubmiter(param.getOperator());
							abnormalResult = abnormalServer.updateAbnormal(updateOrderBO);
						}else{
							updateOrderBO.setStatus(AbnormalConstant.ABNORMAL_STATUS);
							updateOrderBO.setAbnomalAmount("0");
							updateOrderBO.setReason(AbnormalConstant.ABNORMAL_REASON_NULL);
							updateOrderBO.setSubmiter(param.getOperator());
							abnormalResult = abnormalServer.updateAbnormal(updateOrderBO);
						}
						return  MsgTemplate.customMsg(abnormalResult);
					}
				}
			}
		}
		return  MsgTemplate.customMsg(result);
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
