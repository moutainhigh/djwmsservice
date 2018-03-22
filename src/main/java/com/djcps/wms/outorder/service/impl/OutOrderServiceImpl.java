package com.djcps.wms.outorder.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.ChildOrderBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.outorder.model.OutOrderBO;
import com.djcps.wms.outorder.model.SelectOutOrderBO;
import com.djcps.wms.outorder.model.outorderresult.OrderDetailInfoVO;
import com.djcps.wms.outorder.server.OutOrderServer;
import com.djcps.wms.outorder.service.OutOrderService;
import com.google.gson.Gson;
/**
 * 出库单模块业务层
 * @author ldh
 *
 */
@Service("outOrderService")
public class OutOrderServiceImpl implements OutOrderService{
	@Autowired
	private OutOrderServer outOrderServer;
	
	@Autowired
	private OrderServer orderServer;
	
	private Gson gson = new Gson();
	
	@Override
	public Map<String, Object> getOrderDetail(OutOrderBO param) {
		//先根据出库单号获取订单编号
		HttpResult result = outOrderServer.getOrderIdsByOutOrderId(param);
		//获取出库单号字符串
		String childOrderIdStr = result.getData().toString();
		String[] childOrderIds = childOrderIdStr.split(",");
		List<String> list = Arrays.asList(childOrderIds);
		OrderIdsBO orderIdsBO = new OrderIdsBO();
		orderIdsBO.setChildOrderIds(list);
		//获取订单明细详情list
		List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIdsBO);
		List<OrderDetailInfoVO> orderDetailList = new ArrayList<OrderDetailInfoVO>();
		
		if(!childOrderList.isEmpty()){
			for(ChildOrderBO childOrderBO:childOrderList){
				OrderDetailInfoVO orderDetail = new OrderDetailInfoVO();
				orderDetail.setChildOrderId(childOrderBO.getFchildorderid());
				orderDetail.setGroupGoodName(childOrderBO.getFgroupgoodname());
				if(!ObjectUtils.isEmpty(childOrderBO.getFmateriallength())&&!ObjectUtils.isEmpty(childOrderBO.getFmaterialwidth())){
					StringBuffer buff = new StringBuffer().append(childOrderBO.getFmateriallength()).append("*").append(childOrderBO.getFmaterialwidth());
					orderDetail.setMaterial(buff.toString());
				}
				if(!ObjectUtils.isEmpty(childOrderBO.getFboxlength())&&!ObjectUtils.isEmpty(childOrderBO.getFboxwidth())
						&&!ObjectUtils.isEmpty(childOrderBO.getFboxheight())){
					StringBuffer stringBuff = new StringBuffer().append(childOrderBO.getFboxlength()).append("*").
							append(childOrderBO.getFboxwidth()).append("*").append(childOrderBO.getFboxheight());
					orderDetail.setBox(stringBuff.toString());
				}
				orderDetail.setAmount(childOrderBO.getFamountpiece());
				orderDetail.setUnitPrice(childOrderBO.getFunitprice());
				orderDetail.setAmountPrice(childOrderBO.getFamountprice());
				orderDetailList.add(orderDetail);
				
			}
		}
		
		return MsgTemplate.successMsg(orderDetailList);
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
