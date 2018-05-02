package com.djcps.wms.outorder.service.impl;

import java.math.BigDecimal;
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
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.server.OrderServer;
import com.djcps.wms.outorder.enums.OutOrderEnums;
import com.djcps.wms.outorder.model.OrderDetailBO;
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
		OrderIdsBO orderIdsBO = null;
		List<OrderDetailBO> childOrderList = new ArrayList<>();
		List<OrderDetailBO> splitChildOrderList = new ArrayList<>();
		if(!ObjectUtils.isEmpty(result.getData())){
			String childOrderIdStr = result.getData().toString();
			String[] childOrderIds = childOrderIdStr.split(",");
			List<String> list = Arrays.asList(childOrderIds);
			orderIdsBO = new OrderIdsBO();
			orderIdsBO.setChildOrderIds(list);
			orderIdsBO.setPartnerArea(param.getPartnerArea());
			//获取订单明细详情list
			BatchOrderDetailListPO batchOrderDetailListPO = orderServer.getOrderOrSplitOrder(orderIdsBO);
			List<WarehouseOrderDetailPO> orderList = batchOrderDetailListPO.getOrderList();
			if(!ObjectUtils.isEmpty(orderList)){
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(orderList);
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
					OrderDetailBO child = new OrderDetailBO();
					BeanUtils.copyProperties(warehouseOrderDetailPO, child);
					childOrderList.add(child);
				}
			}
			
			List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
			if(!ObjectUtils.isEmpty(splitOrderList)){
				List<WarehouseOrderDetailPO> joinOrderParamInfo = orderServer.joinOrderParamInfo(splitOrderList);
				for (WarehouseOrderDetailPO warehouseOrderDetailPO : joinOrderParamInfo) {
					OrderDetailBO child = new OrderDetailBO();
					BeanUtils.copyProperties(warehouseOrderDetailPO, child);
					splitChildOrderList.add(child);
				}
			}
		}else{
			return MsgTemplate.failureMsg(OutOrderEnums.GET_ORDER_ID_FAIL);
		}
		
		List<OrderDetailBO> orderDetailList = new ArrayList<OrderDetailBO>();
		
		if(!ObjectUtils.isEmpty(childOrderList)){
			orderDetailList.addAll(childOrderList);
		}
		
		if(!ObjectUtils.isEmpty(splitChildOrderList)){
			orderDetailList.addAll(splitChildOrderList);
		}
		
		BigDecimal totalPrice = new BigDecimal(0.00);
		Integer totalAmount = 0;
		if(!ObjectUtils.isEmpty(orderDetailList)){
			for(OrderDetailBO orderDetail:orderDetailList){
				totalPrice = totalPrice.add(orderDetail.getAmountPrice());
				totalAmount += orderDetail.getAmountPiece();
			}
			OrderDetailInfoVO orderDetailVO = new OrderDetailInfoVO();
			orderDetailVO.setOrderDetails(orderDetailList);
			orderDetailVO.setTotalAmount(totalAmount);
			orderDetailVO.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			return MsgTemplate.successMsg(orderDetailVO);
		}else{
			return MsgTemplate.failureMsg(OutOrderEnums.GET_ORDERDETAIL_FAIL);
		}
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
