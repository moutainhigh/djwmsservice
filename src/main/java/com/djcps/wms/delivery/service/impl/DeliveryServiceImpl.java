package com.djcps.wms.delivery.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.wms.commons.base.BaseListPO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.StringUtils;
import com.djcps.wms.delivery.enums.DeliveryMsgEnum;
import com.djcps.wms.delivery.model.*;
import com.djcps.wms.delivery.server.DeliveryServer;
import com.djcps.wms.delivery.service.DeliveryService;
import com.djcps.wms.order.model.ChildOrderBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.order.server.OrderServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 提货实现类
 *
 * @author Chengw
 * @since 2018/1/31 08:37.
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

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
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
                deliveryOrderList = getOrder(deliveryOrderList);
                baseListPO.setList(deliveryOrderList);
                return MsgTemplate.successMsg(baseListPO);
            } else {
                return MsgTemplate.customMsg(result);
            }

        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 附加订单详细信息
     *
     * @param orderList
     * @return
     */
    private List<DeliveryOrderPO> getOrder(List<DeliveryOrderPO> orderList) {
        List<String> orderIdList = new ArrayList<>();
        orderList.stream().collect(
                Collectors.groupingBy(DeliveryOrderPO::getOrderId, Collectors.toList())
        ).forEach((name, groupList) -> {
            orderIdList.add(name);
        });
        OrderIdsBO orderIds = new OrderIdsBO();
        orderIds.setChildOrderIds(orderIdList);
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIds);
        if (!childOrderList.isEmpty()) {
            orderList.stream().forEach(order -> {
                ChildOrderBO childOrderBO =  childOrderList.stream().filter(
                        b -> b.getFchildorderid().equals(order.getOrderId())).findFirst().orElse(null);
                if(!ObjectUtils.isEmpty(childOrderBO)) {
                    order.setBoxHeight(StringUtils.toString(childOrderBO.getFboxheight()));
                    order.setBoxWidth(StringUtils.toString(childOrderBO.getFboxwidth()));
                    order.setBoxLength(StringUtils.toString(childOrderBO.getFboxlength()));
                    order.setFluteType(childOrderBO.getFflutetype());
                    order.setMaterialName(childOrderBO.getFmaterialname());
                    order.setMaterialLength(StringUtils.toString(childOrderBO.getFmateriallength()));
                    order.setMaterialWidth(StringUtils.toString(childOrderBO.getFmaterialwidth()));
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
                List<DeliveryOrderPO> orderList = listOrder(param);
                if (orderList.isEmpty()) {
                    MsgTemplate.failureMsg(DeliveryMsgEnum.ORDER_NOT_EXIT);
                }
                return MsgTemplate.successMsg(orderList);
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
                List<DeliveryOrderPO> orderList = listOrder(param);
                if (orderList.isEmpty()) {
                    MsgTemplate.failureMsg(DeliveryMsgEnum.ORDER_NOT_EXIT);
                }
                deliveryPO.setOrderList(orderList);
                return MsgTemplate.successMsg(deliveryPO);
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 获取提货订单信息
     *
     * @param param
     * @return
     */
    private List<DeliveryOrderPO> listOrder(DeliveryOrderBO param) {
        HttpResult result = deliveryServer.listOrderForPDA(param);
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                List<DeliveryOrderPO> orderList = JSONArray.parseArray(data, DeliveryOrderPO.class);
                return getOrder(orderList);
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
                return MsgTemplate.successMsg(getOrderDetail(deliveryOrderDetailPO));
            }
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 获取订单详细信息
     *
     * @param deliveryOrderDetailPO
     * @return
     */
    private DeliveryOrderDetailPO getOrderDetail(DeliveryOrderDetailPO deliveryOrderDetailPO) {
        OrderIdsBO orderIds = new OrderIdsBO();
        orderIds.setChildOrderIds(Arrays.asList(deliveryOrderDetailPO.getOrderId()));
        List<ChildOrderBO> childOrderList = orderServer.getChildOrderList(orderIds);
        if (!childOrderList.isEmpty()) {
            ChildOrderBO childOrderBO = childOrderList.stream()
                    .filter(b -> b.getFchildorderid().equals(deliveryOrderDetailPO.getOrderId()))
                    .findFirst().orElse(null);
            if(!ObjectUtils.isEmpty(childOrderBO)) {
                deliveryOrderDetailPO.setMaterialName(childOrderBO.getFmaterialname());
                deliveryOrderDetailPO.setMaterialLength(StringUtils.toString(childOrderBO.getFmateriallength()));
                deliveryOrderDetailPO.setMaterialWidth(StringUtils.toString(childOrderBO.getFmaterialwidth()));
                deliveryOrderDetailPO.setBoxHeight(StringUtils.toString(childOrderBO.getFboxheight()));
                deliveryOrderDetailPO.setBoxWidth(StringUtils.toString(childOrderBO.getFboxwidth()));
                deliveryOrderDetailPO.setBoxLength(StringUtils.toString(childOrderBO.getFboxlength()));
                deliveryOrderDetailPO.setFluteType(childOrderBO.getFflutetype());
            }
        }
        return deliveryOrderDetailPO;
    }
}
