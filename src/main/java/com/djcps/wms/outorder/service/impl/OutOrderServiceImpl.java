package com.djcps.wms.outorder.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.outorder.model.OrderBO;
import com.djcps.wms.outorder.model.OrderDetailBO;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.model.outorderresult.OrderDetailInfoBO;
import com.djcps.wms.outorder.server.OutOrderServer;
import com.djcps.wms.outorder.service.OutOrderService;
import com.google.gson.Gson;
/**
 * 
 * @author ldh
 *
 */
@Service("outOrderService")
public class OutOrderServiceImpl implements OutOrderService{
	@Autowired
	private OutOrderServer outOrderServer;
	
	private Gson gson = new Gson();
	@Override
	public Map<String, Object> getOrderDetail(OutOrderBO param) {
		//先根据出库单号获取订单编号
		HttpResult result = outOrderServer.getOrderIdsByOutOrderId(param);
		//获取订单编号数组
		String orderStr = (String)result.getData();
		String[] orderIds = orderStr.split(","); 
		HttpResult res = null;
		List<OrderDetailInfoBO> list = new ArrayList<OrderDetailInfoBO>();
		for(String orderId:orderIds){
			OrderBO orderBO = new OrderBO();
			orderBO.setChildOrderId(orderId);
			res = outOrderServer.getOrderDetailByOrderId(orderBO);
			if(!ObjectUtils.isEmpty(res.getData())){
				OrderDetailBO orderDetail = gson.fromJson(gson.toJson(res.getData()), OrderDetailBO.class);
				OrderDetailInfoBO detailInfo = new OrderDetailInfoBO();
				BeanUtils.copyProperties(orderDetail, detailInfo);
				//拼接参数,设置下料规格和产品规格
				if(!ObjectUtils.isEmpty(orderDetail.getFboxlength()) && !ObjectUtils.isEmpty(orderDetail) && !ObjectUtils.isEmpty(orderDetail)){
					detailInfo.setFbox(new StringBuffer().append(orderDetail.getFboxlength()).append("*").append(orderDetail.getFboxwidth()).append("*").append(orderDetail.getFboxheight()).toString());
				}
				if(!ObjectUtils.isEmpty(orderDetail.getFmateriallength()) && !ObjectUtils.isEmpty(orderDetail.getFmaterialwidth())){
					detailInfo.setFmaterial(new StringBuffer().append(orderDetail.getFmateriallength()).append("*").append(orderDetail.getFmaterialwidth()).toString());
				}
				list.add(detailInfo);
			}
		}
		
		return MsgTemplate.successMsg(list);
	}
	
	@Override
	public Map<String, Object> getAllOutOrder(SelectOutOrderBO param) {
		HttpResult result = outOrderServer.getAllOutOrder(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> getOutOrderByOutOrderId(OutOrderBO param) {
		HttpResult result = outOrderServer.getOutOrderByOutOrderId(param);
		return MsgTemplate.customMsg(result);
	}

	@Override
	public Map<String, Object> updateOutOrderByOutOrderId(OutOrderBO param) {
		HttpResult result = outOrderServer.updateOutOrderByOutOrderId(param);
		return MsgTemplate.customMsg(result);
	}

	
	
}
