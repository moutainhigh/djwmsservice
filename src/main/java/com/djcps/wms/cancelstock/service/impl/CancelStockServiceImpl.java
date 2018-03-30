package com.djcps.wms.cancelstock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.spi.Bean;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.cancelstock.enums.CancelStockEnum;
import com.djcps.wms.cancelstock.model.CancalOrderAttributePO;
import com.djcps.wms.cancelstock.model.CancelStockPO;
import com.djcps.wms.cancelstock.model.param.AddCancelStockBO;
import com.djcps.wms.cancelstock.model.param.AddStockBO;
import com.djcps.wms.cancelstock.model.param.CancelOrderIdBO;
import com.djcps.wms.cancelstock.model.param.PickerIdBO;
import com.djcps.wms.cancelstock.server.CancelStockServer;
import com.djcps.wms.cancelstock.service.CancelStockService;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.FluteTypeEnum;
import com.djcps.wms.commons.enums.OrderStatusTypeEnum;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.server.OrderServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 地址业务层实现类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
@Service
public class CancelStockServiceImpl implements CancelStockService {

	Gson gson = new Gson();
	
	@Autowired
	private CancelStockServer cancelStockServer;

	@Autowired
	private OrderServer orderServer;
	
	@Override
	public Map<String, Object> getOrderByOrderId(CancelOrderIdBO param) {
		HttpResult result = cancelStockServer.getOrderByOrderId(param);
		if(!result.isSuccess()){
			return MsgTemplate.failureMsg(SysMsgEnum.ORDER_IS_NULL);
		}
		CancalOrderAttributePO orderAttribute = gson.fromJson(gson.toJson(result.getData()), CancalOrderAttributePO.class);
		OrderIdBO orderIdBO = new OrderIdBO();
		orderIdBO.setChildOrderId(param.getOrderId());
		HttpResult orderResult = orderServer.getOrderByOrderId(orderIdBO);
		if(ObjectUtils.isEmpty(orderResult.getData())){
			return MsgTemplate.failureMsg(SysMsgEnum.ORDER_IS_NULL);
		}
		if(!ObjectUtils.isEmpty(orderResult)){
			WarehouseOrderDetailPO orderDetailPO = gson.fromJson(gson.toJson(orderResult.getData()), WarehouseOrderDetailPO.class);
			String fflutetype = orderDetailPO.getFflutetype();
        	switch(Integer.valueOf(fflutetype)){
	        case 1:
	        	orderAttribute.setFflutetype(FluteTypeEnum.BC.getValue());break;
	        case 2:
	        	orderAttribute.setFflutetype(FluteTypeEnum.BE.getValue());break;
	        case 3:
	        	orderAttribute.setFflutetype(FluteTypeEnum.C.getValue());break;
	        case 4:
	        	orderAttribute.setFflutetype(FluteTypeEnum.B.getValue());break;
	        case 5:
	        	orderAttribute.setFflutetype(FluteTypeEnum.E.getValue());break;
	        case 6:
	        	orderAttribute.setFflutetype(FluteTypeEnum.EBC.getValue());break;
	        default:
	        	orderAttribute.setFflutetype(FluteTypeEnum.EE.getValue());break;
	        }
			orderAttribute.setFmaterialname(orderDetailPO.getFmaterialname());
			orderAttribute.setProductName(orderDetailPO.getFgroupgoodname());
			orderAttribute.setLocation(orderDetailPO.getFlnglat());
			//规格长宽高都不为null,才进行拼接
			if(!ObjectUtils.isEmpty(orderDetailPO.getFboxlength()) && !ObjectUtils.isEmpty(orderDetailPO.getFboxwidth()) &&
					!ObjectUtils.isEmpty(orderDetailPO.getFboxheight())){
				//拼接字符串,拼接成产品规格和下料规格
				orderAttribute.setFproductSize(new StringBuffer().append(orderDetailPO.getFboxlength()).append("*")
						.append(orderDetailPO.getFboxwidth()).append("*").append(orderDetailPO.getFboxheight()).toString());
			}
			if(!ObjectUtils.isEmpty(orderDetailPO.getFmateriallength()) && !ObjectUtils.isEmpty(orderDetailPO.getFmaterialwidth())){
				orderAttribute.setFmaterialSize(new StringBuffer().append(orderDetailPO.getFmateriallength()).append("*")
						.append(orderDetailPO.getFmaterialwidth()).toString());
			}
		}
		return MsgTemplate.successMsg(orderAttribute);
	}

	@Override
	public Map<String, Object> addStock(AddStockBO param) {
		CancelOrderIdBO cancelOrderIdBO = new CancelOrderIdBO();
		BeanUtils.copyProperties(param, cancelOrderIdBO);
		HttpResult cancelOrderResult = cancelStockServer.getOrderByOrderId(cancelOrderIdBO);
		CancalOrderAttributePO orderAttribute = gson.fromJson(gson.toJson(cancelOrderResult.getData()), CancalOrderAttributePO.class);
		if(!param.getWarehouseId().equals(orderAttribute.getWarehouseId())){
			return MsgTemplate.failureMsg(CancelStockEnum.WAREHOUSEID_ERROR.getValue());
		}
		HttpResult result = cancelStockServer.addStock(param);
		//修改订单服务拆分订单的订单状态
//		if(result.isSuccess()){
//			OrderIdBO orderIdBO = new OrderIdBO();
//			orderIdBO.setOrderId(param.getOrderId());
//			orderIdBO.setStatus(OrderStatusTypeEnum.ALL_ADD_STOCK.getValue());
//	        //通知订单服务修改,需要批量执行
//	        HttpResult updateResult = orderServer.updateOrderStatus(orderIdBO);
//		}
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getCancelStockByPickerId(PickerIdBO param) {
		HttpResult result = cancelStockServer.getCancelStockByPickerId(param);
		List<String> orderIdList = new ArrayList<>();
		Map<String,CancelStockPO> map = new HashMap<>(16);
		if(!ObjectUtils.isEmpty(result.getData())){
			List<CancelStockPO> cancelStockList = gson.fromJson(gson.toJson(result.getData()), new TypeToken<ArrayList<CancelStockPO>>(){}.getType());
			for (CancelStockPO cancelStockPO : cancelStockList) {
				map.put(cancelStockPO.getSonOrderId(), cancelStockPO);
				orderIdList.add(cancelStockPO.getSonOrderId());
			}
		}else{
			return MsgTemplate.successMsg();
		}
		OrderIdsBO orderIds = new OrderIdsBO();
		orderIds.setChildOrderIds(orderIdList);
		HttpResult orderResult = orderServer.getOrderByOrderIds(orderIds);
		List<CancalOrderAttributePO> orderAttributeList = new ArrayList<>();
		if(!ObjectUtils.isEmpty(orderResult)){
			List<WarehouseOrderDetailPO> orderDetailList = gson.fromJson(gson.toJson(orderResult.getData()), new TypeToken<ArrayList<WarehouseOrderDetailPO>>(){}.getType());
			for (WarehouseOrderDetailPO orderDetailPO : orderDetailList) {
				if(AppConstant.GROUP_ORDER_DOUBLE.equals(orderDetailPO.getFdblflag())){
					CancalOrderAttributePO orderAttribute = new CancalOrderAttributePO();
					CancelStockPO cancelStockPO = map.get(orderDetailPO.getFchildorderid());
					String fflutetype = orderDetailPO.getFflutetype();
		        	switch(Integer.valueOf(fflutetype)){
			        case 1:
			        	orderAttribute.setFflutetype(FluteTypeEnum.BC.getValue());break;
			        case 2:
			        	orderAttribute.setFflutetype(FluteTypeEnum.BE.getValue());break;
			        case 3:
			        	orderAttribute.setFflutetype(FluteTypeEnum.C.getValue());break;
			        case 4:
			        	orderAttribute.setFflutetype(FluteTypeEnum.B.getValue());break;
			        case 5:
			        	orderAttribute.setFflutetype(FluteTypeEnum.E.getValue());break;
			        case 6:
			        	orderAttribute.setFflutetype(FluteTypeEnum.EBC.getValue());break;
			        default:
			        	orderAttribute.setFflutetype(FluteTypeEnum.EE.getValue());break;
			        }
					orderAttribute.setFmaterialname(orderDetailPO.getFmaterialname());
					orderAttribute.setProductName(orderDetailPO.getFgroupgoodname());
					BeanUtils.copyProperties(cancelStockPO,orderAttribute);
					orderAttributeList.add(orderAttribute);
					//规格长宽高都不为null,才进行拼接
					if(!ObjectUtils.isEmpty(orderDetailPO.getFboxlength()) && !ObjectUtils.isEmpty(orderDetailPO.getFboxwidth()) &&
							!ObjectUtils.isEmpty(orderDetailPO.getFboxheight())){
						//拼接字符串,拼接成产品规格和下料规格
						orderAttribute.setFproductSize(new StringBuffer().append(orderDetailPO.getFboxlength()).append("*")
								.append(orderDetailPO.getFboxwidth()).append("*").append(orderDetailPO.getFboxheight()).toString());
					}
					if(!ObjectUtils.isEmpty(orderDetailPO.getFmateriallength()) && !ObjectUtils.isEmpty(orderDetailPO.getFmaterialwidth())){
						orderAttribute.setFmaterialSize(new StringBuffer().append(orderDetailPO.getFmateriallength()).append("*")
								.append(orderDetailPO.getFmaterialwidth()).toString());
					}
				}
			}
			
		}
		if(!ObjectUtils.isEmpty(orderAttributeList)){
			return MsgTemplate.successMsg(orderAttributeList);
		}else{
			return MsgTemplate.successMsg();
		}
	}

	@Override
	public Map<String, Object> addCancelStock(AddCancelStockBO param) {
		HttpResult result = cancelStockServer.addCancelStock(param);
		return MsgTemplate.customMsg(result);
	}

}
