package com.djcps.wms.abnormal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.wms.abnormal.constant.AbnormalConstant;
import com.djcps.wms.abnormal.enums.AbnormalMsgEnum;
import com.djcps.wms.abnormal.model.AbnormalOrderPO;
import com.djcps.wms.abnormal.model.AddAbnormal;
import com.djcps.wms.abnormal.model.GetOrderByAttributeBO;
import com.djcps.wms.abnormal.model.OrderIdBO;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.model.UpdateAbnormalBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.abnormal.service.AbnormalService;
import com.djcps.wms.allocation.model.AddAllocationOrderBO;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 异常订单业务层
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2018年3月7日
 */
@Service
public class AbnormalServiceImpl implements AbnormalService {
	
	@Autowired
	private AbnormalServer abnormalServer;
	@Autowired
	private OrderServer orderServer;
	
	private Gson gsonNotNull = GsonUtils.gsonNotNull;
	
	@Override
	public Map<String, Object> getOrderByAttributeBO(GetOrderByAttributeBO param) {
		HttpResult result = abnormalServer.getOrderByAttributeBO(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getSplitOrderByOrderId(OrderIdBO param) {
		HttpResult result = abnormalServer.getSplitOrderByOrderId(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> updateAbnormal(UpdateAbnormalBO param) {
		OrderIdListBO orderIdListBO = new OrderIdListBO();
		orderIdListBO.setList(Arrays.asList(param.getOrderId()));
		orderIdListBO.setPartnerId(param.getPartnerId());
		HttpResult abnorResult = abnormalServer.getOrderByOrderIdList(orderIdListBO);
		List<AbnormalOrderPO> abnormalOrderPOList = gsonNotNull.fromJson(gsonNotNull.toJson(abnorResult.getData()), new TypeToken<ArrayList<AbnormalOrderPO>>(){}.getType());
		//作废的处理方式只能针对盘点异常订单!!! 
		if(abnormalOrderPOList.get(0).getLink().equals(AbnormalConstant.ABNORMAL_LINK_2) && 
				String.valueOf(param.getResult())!=AbnormalConstant.ABNORMAL_UPDATE_4){
			return MsgTemplate.failureMsg(AbnormalMsgEnum.STOCK_TAKING_RESULT_EROOR);
		}
		
		HttpResult result = abnormalServer.updateAbnormal(param);
		//OMS处理订单提醒状态
		if(result.isSuccess()){
			String orderId = param.getOrderId();
			Integer updateResult = param.getResult();
			List<UpdateSplitOrderBO> list = new ArrayList<>();
			UpdateSplitOrderBO update = new UpdateSplitOrderBO();
			update.setSubOrderId(orderId);
			update.setKeyArea(param.getPartnerArea());
			if(updateResult.equals(Integer.valueOf(AbnormalConstant.ABNORMAL_UPDATE_1))){
				update.setIsProduce(1);
				update.setIsException(0);
			}else if(updateResult.equals(Integer.valueOf(AbnormalConstant.ABNORMAL_UPDATE_2))){
				update.setIsStored(1);
				update.setIsException(0);
			}else if(updateResult.equals(Integer.valueOf(AbnormalConstant.ABNORMAL_UPDATE_3))){
				update.setIsException(0);
			}else if(updateResult.equals(Integer.valueOf(AbnormalConstant.ABNORMAL_UPDATE_4))){
				update.setIsException(0);
			}
			list.add(update);
			result = orderServer.updateSplitOrderInfo(list);
		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> addAbnormal(AddAbnormal param) {
		Boolean chooseAddAbnormal = abnormalServer.isChooseAddAbnormal(param);
		if(chooseAddAbnormal){
			//新增异常订单逻辑,新增时,需要修改OMS处订单的异常状态
			HttpResult result = abnormalServer.addAbnormal(param);
			if(result.isSuccess()){
				List<String> strOrderIds = Arrays.asList(param.getOrderId());
				result = abnormalServer.updateExecptionFlag(1,strOrderIds,param.getPartnerArea());
				if(!result.isSuccess()){
					return MsgTemplate.failureMsg(AbnormalMsgEnum.STOCK_UPDATE_SPLIT_ORDER_STATUS_ERROR);
				}
			}
			return MsgTemplate.customMsg(result);
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> getOrderByOrderIdList(OrderIdListBO param) {
		HttpResult result = abnormalServer.getOrderByOrderIdList(param);
		return MsgTemplate.customMsg(result);
	}

}
