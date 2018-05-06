package com.djcps.wms.delivery.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.base.BaseListPO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.StringUtils;
import com.djcps.wms.delivery.constant.DeliveryConstant;
import com.djcps.wms.delivery.enums.DeliveryMsgEnum;
import com.djcps.wms.delivery.enums.DeliveryStatusEnum;
import com.djcps.wms.delivery.model.*;
import com.djcps.wms.delivery.server.DeliveryServer;
import com.djcps.wms.delivery.service.DeliveryService;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.order.model.ChildOrderBO;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.order.model.onlinepaperboard.BatchOrderDetailListPO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateSplitOrderBO;
import com.djcps.wms.order.model.onlinepaperboard.UpdateOrderBO;
import com.djcps.wms.order.server.OrderServer;
import com.mysql.fabric.xmlrpc.base.Array;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 提货实现类
 *
 * @author  Chengw
 * @since 2018/1/31 08:37.
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryServer deliveryServer;

    @Autowired
    private OrderServer orderServer;

    /**
     * 获取提货单列表
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/1/31  09:26
     */
    @Override
    public Map<String, Object> list(ListDeliveryBO param) {
        HttpResult result = deliveryServer.list(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 获取提货单订单列表
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/1/31  09:26
     */
    @Override
    public Map<String, Object> listOrder(ListDeliveryOrderBO param) {
        HttpResult result = deliveryServer.listOrder(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = JSONObject.toJSONString(result.getData());
                BaseListPO baseListPO = gson.fromJson(data, BaseListPO.class);
                List<DeliveryOrderPO> deliveryOrderList =
                        JSONArray.parseArray(JSONObject.toJSONString(baseListPO.getList()), DeliveryOrderPO.class);
                deliveryOrderList = getOrder(deliveryOrderList,param.getPartnerArea());
                baseListPO.setList(deliveryOrderList);
                return MsgTemplate.successMsg(baseListPO);
            } else {
                return MsgTemplate.customMsg(result);
            }

        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 增加打印次数
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/1/31  09:28
     */
    @Override
    public Map<String, Object> print(PrintDeliveryBO param) {
        HttpResult result = deliveryServer.addPrintCount(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 完成单条提货订单的提货
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/1/31  09:29
     */
    @Override
    public Map<String, Object> completeOrder(SaveDeliveryBO param) {
        HttpResult result = deliveryServer.completeOrder(param);
        if (result.isSuccess()) {
        	List<String> order = new ArrayList<>();
        	order.add(param.getOrderId());
        	
        	List<OrderIdBO> orderIdBOList = new ArrayList<>();
        	OrderIdBO orderIdBO = new OrderIdBO();
        	orderIdBO.setOrderId(param.getOrderId());
        	orderIdBO.setStatus(LoadingTaskConstant.REDUNDANTSTATUS_24);
        	orderIdBOList.add(orderIdBO);
        	
            result = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
            if(!result.isSuccess()){
            	LOGGER.error("完成单条提货订单的提货,修改订单状态失败!!!");
            	return MsgTemplate.customMsg(result);
            }
            List<String> orderId = new ArrayList<>();
			orderId.add(param.getOrderId());
			Boolean compareOrderStatus = orderServer.compareOrderStatus(orderId,  param.getPartnerArea());
			if(compareOrderStatus==false){
				return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
			}
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 附加订单详细信息
     *
     * @param orderList
     * @return
     */
    private List<DeliveryOrderPO> getOrder(List<DeliveryOrderPO> orderList,String partnerArea) {
        List<String> orderIdList = new ArrayList<>();
        orderList.stream().collect(
                Collectors.groupingBy(DeliveryOrderPO::getOrderId, Collectors.toList())
        ).forEach((name, groupList) -> {
            orderIdList.add(name);
        });
        OrderIdsBO orderIds = new OrderIdsBO();
        orderIds.setChildOrderIds(orderIdList);
        orderIds.setPartnerArea(partnerArea);
        List<ChildOrderBO> childOrderList = new ArrayList<>();
        BatchOrderDetailListPO batchOrderDetailListPO = orderServer.getOrderOrSplitOrder(orderIds);
        List<WarehouseOrderDetailPO> sonOrderList = batchOrderDetailListPO.getOrderList();
        List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailListPO.getSplitOrderList();
        if(!ObjectUtils.isEmpty(sonOrderList)){
        	sonOrderList = orderServer.joinOrderParamInfo(sonOrderList);
        	for (WarehouseOrderDetailPO warehouseOrderDetailPO : sonOrderList) {
        		ChildOrderBO child = new ChildOrderBO();
        		BeanUtils.copyProperties(warehouseOrderDetailPO, child);
        		childOrderList.add(child);
			}
        }
        if(!ObjectUtils.isEmpty(splitOrderList)){
        	splitOrderList = orderServer.joinOrderParamInfo(splitOrderList);
        	for (WarehouseOrderDetailPO warehouseOrderDetailPO : splitOrderList) {
        		ChildOrderBO child = new ChildOrderBO();
        		BeanUtils.copyProperties(warehouseOrderDetailPO, child);
        		childOrderList.add(child);
			}
        }
        if (!childOrderList.isEmpty()) {
            orderList.stream().forEach(order -> {
                ChildOrderBO childOrderBO =  childOrderList.stream().filter(
                        b -> b.getOrderId().equals(order.getOrderId())).findFirst().orElse(null);
                if(!ObjectUtils.isEmpty(childOrderBO)) {
                    //设置账户名称  母账户名称或者子账户名称
                    BeanUtils.copyProperties(childOrderBO, order);
                }
            });
        }

        return orderList;
    }

    /**
     * 获取提货订单列表 - PDA
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  13:18
     */
    @Override
    public Map<String, Object> listOrderForPDA(DeliveryOrderBO param) {
        HttpResult result = deliveryServer.listOrderForPDA(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                List<DeliveryOrderPO> orderList = listOrder(param,param.getPartnerArea());
                if (orderList.isEmpty()) {
                    MsgTemplate.failureMsg(DeliveryMsgEnum.ORDER_NOT_EXIT);
                }
                orderList = setDeliveryStatus(orderList);
                return MsgTemplate.successMsg(orderList);
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 获取提货任务信息 - PDA
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  13:10
     */
    @Override
    public Map<String, Object> getDeliveryForPDA(DeliveryOrderBO param) {
        HttpResult result = deliveryServer.getDeliveryForPDA(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                DeliveryPO deliveryPO = gson.fromJson(data, DeliveryPO.class);
                List<DeliveryOrderPO> orderList = listOrder(param,param.getPartnerArea());
                if (orderList.isEmpty()) {
                    MsgTemplate.failureMsg(DeliveryMsgEnum.ORDER_NOT_EXIT);
                }
                orderList = setDeliveryStatus(orderList);
                deliveryPO.setOrderList(orderList);
                return MsgTemplate.successMsg(deliveryPO);
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 获取提货订单信息
     *
     * @param param
     * @return
     */
    private List<DeliveryOrderPO> listOrder(DeliveryOrderBO param,String partnerArea) {
        HttpResult result = deliveryServer.listOrderForPDA(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<DeliveryOrderPO> orderList = JSONArray.parseArray(data, DeliveryOrderPO.class);
                return getOrder(orderList,partnerArea);
            }
        }
        return null;
    }

    /**
     * 获取提货订单详细信息 - PDA
     * 订单详细信息、库存信息
     *
     * @param param
     * @return
     * @autuor Chengw
     * @since 2018/2/1  13:09
     */
    @Override
    public Map<String, Object> getOrderDetail(DeliveryOrderDetailBO param) {
        HttpResult result = deliveryServer.getOrderDetail(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                DeliveryOrderDetailPO deliveryOrderDetailPO = gson.fromJson(data, DeliveryOrderDetailPO.class);
                return MsgTemplate.successMsg(getOrderDetail(deliveryOrderDetailPO,param.getPartnerArea()));
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 获取订单详细信息
     *
     * @param deliveryOrderDetailPO
     * @return
     */
    private DeliveryOrderDetailPO getOrderDetail(DeliveryOrderDetailPO deliveryOrderDetailPO,String partnerArea) {
        OrderIdsBO orderIds = new OrderIdsBO();
        orderIds.setChildOrderIds(Arrays.asList(deliveryOrderDetailPO.getOrderId()));
        orderIds.setPartnerArea(partnerArea);
        BatchOrderDetailListPO batchOrderDetailList = orderServer.getOrderOrSplitOrder(orderIds);
        List<WarehouseOrderDetailPO> orderList = batchOrderDetailList.getOrderList();
        if(!ObjectUtils.isEmpty(orderList)){
        	List<WarehouseOrderDetailPO> joinOrderParamInfo =orderServer.joinOrderParamInfo(orderList);
        	List<ChildOrderBO> childOrderBOList = new ArrayList<>();
        	ChildOrderBO child = new ChildOrderBO();
			BeanUtils.copyProperties(joinOrderParamInfo.get(0), child);
			childOrderBOList.add(child);
        	ChildOrderBO childOrderBO = childOrderBOList.stream()
                    .filter(b -> b.getOrderId().equals(deliveryOrderDetailPO.getOrderId()))
                    .findFirst().orElse(null);
            if(!ObjectUtils.isEmpty(childOrderBO)) {
                BeanUtils.copyProperties(childOrderBO, deliveryOrderDetailPO);
            }
        }else{
        	List<WarehouseOrderDetailPO> splitOrderList = batchOrderDetailList.getSplitOrderList();
        	List<WarehouseOrderDetailPO> joinOrderParamInfo =orderServer.joinOrderParamInfo(splitOrderList);
        	List<ChildOrderBO> childOrderBOList = new ArrayList<>();
        	ChildOrderBO child = new ChildOrderBO();
        	BeanUtils.copyProperties(joinOrderParamInfo.get(0), child);
        	childOrderBOList.add(child);
        	ChildOrderBO childOrderBO = childOrderBOList.stream()
        			.filter(b -> b.getOrderId().equals(deliveryOrderDetailPO.getOrderId()))
        			.findFirst().orElse(null);
        	if(!ObjectUtils.isEmpty(childOrderBO)) {
            	BeanUtils.copyProperties(childOrderBO, deliveryOrderDetailPO);
        	}
        }
        return deliveryOrderDetailPO;
    }

    /**
     * 设置订单提货状态
     * 库位提货状态为 1已提货 0为未提货
     * @param orderList
     * @return
     */
    private List<DeliveryOrderPO> setDeliveryStatus(List<DeliveryOrderPO> orderList) {
        orderList.stream().forEach(order -> {
            List<OrderDeliveryPO> warehouseLocs = order.getWarehouseLocs();
            Long deliveryedCount = warehouseLocs.stream().filter(a -> a.getStatus().equals(1)).count();
            Long deliveryCount = warehouseLocs.stream().filter(a -> a.getStatus().equals(0)).count();
            if(!deliveryCount.equals(0L)&& !deliveryedCount.equals(0L)){
                order.setDeliveryStatus(DeliveryStatusEnum.UNDONE_PART.getValue());
            }else if(deliveryCount.equals(0L)&& !deliveryedCount.equals(0L)){
                order.setDeliveryStatus(DeliveryStatusEnum.ACCOMPLISHED.getValue());
            }else{
                order.setDeliveryStatus(DeliveryStatusEnum.UNDONE.getValue());
            }
        });
        return orderList;
    }
    /**
     * 删除提货订单信息
     * @autuor wyb
     * @since 2018/3/13
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> updateDeliveryEffect(UpdateDeliveryEffectBO param) {
        HttpResult result = deliveryServer.updateDeliveryEffect(param);
        //更新订单状态为已入库
        if(!ObjectUtils.isEmpty(param.getOrderIds())){
        	List<OrderIdBO> orderIdBOList = new ArrayList<>();
        	List<String> orderIds = param.getOrderIds();
        	for (String string : orderIds) {
        		OrderIdBO orderIdBO = new OrderIdBO();
            	orderIdBO.setOrderId(string);
            	orderIdBO.setStatus(DeliveryConstant.REDUNDANTSTATUS_22);
            	orderIdBOList.add(orderIdBO);
			}
        	
            result = orderServer.updateOrderOrSplitOrder(param.getPartnerArea(),orderIdBOList);
            if(!result.isSuccess()){
            	LOGGER.error("完成单条提货订单的提货,修改订单状态失败!!!");
            	return MsgTemplate.customMsg(result);
            }
			Boolean compareOrderStatus = orderServer.compareOrderStatus(param.getOrderIds(),param.getPartnerArea());
			if(compareOrderStatus==false){
				return MsgTemplate.failureMsg("------拆单状态比子单状态小,需要修改子单状态,但是修改子订单状态失败!!!------");
			}
        }
        return MsgTemplate.customMsg(result);
    }
}
