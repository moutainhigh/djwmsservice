package com.djcps.wms.loadingtask.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.abnormal.model.OrderIdListBO;
import com.djcps.wms.abnormal.server.AbnormalServer;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.constant.LoadingTaskConstant;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.LoadingPersonIdBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.model.result.AbnormalOrderPO;
import com.djcps.wms.loadingtask.model.result.ConfirmPO;
import com.djcps.wms.loadingtask.model.result.FinishLoadingPO;
import com.djcps.wms.loadingtask.model.result.OrderIdAndLoadingAmountPO;
import com.djcps.wms.loadingtask.model.result.OrderInfoPO;
import com.djcps.wms.loadingtask.model.result.OrderRedundantPO;
import com.djcps.wms.loadingtask.server.LoadingTaskOrderServer;
import com.djcps.wms.loadingtask.server.LoadingTaskServer;
import com.djcps.wms.loadingtask.service.LoadingTaskService;
import com.djcps.wms.order.model.OrderIdBO;
import com.djcps.wms.order.model.OrderIdsBO;
import com.djcps.wms.push.mq.producer.AppProducer;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 装车实现类
 *
 * @author wyb
 * @since 2018/3/19
 */
@Service
public class LoadingTaskServiceImpl implements LoadingTaskService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskService.class);

    @Autowired
    private LoadingTaskServer loadingTaskServer;

    @Autowired
    private LoadingTaskOrderServer loadingTaskOrderServer;
    @Autowired
    private AbnormalServer abnormalServer;
    @Resource
    private AppProducer appProducer;

    /**
     * 获取装车员列表
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> loadingPersonList(LoadingPersonBO param) {
        HttpResult result = loadingTaskServer.loadingPersonList(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 移除装车员
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> removeLoadingPerson(RemoveLoadingPersonBO param) {
        param.setStatus(LoadingTaskConstant.LOADINGPERSON_TYPE_0);
        HttpResult result = loadingTaskServer.removeLoadingPerson(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 装车台界面确认
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> confirm(ConfirmBO param) {
        List<LoadingPersonIdBO> list = param.getList();
        if (!ObjectUtils.isEmpty(list)) {
            for (LoadingPersonIdBO info : list) {
                if (!ObjectUtils.isEmpty(param.getLoadingTableId())) {
                    info.setLoadingTableId(param.getLoadingTableId());
                }
            }
        }

        HttpResult result = loadingTaskServer.getOrderList(param);
        if (ObjectUtils.isEmpty(result.getData())) {
            return MsgTemplate.failureMsg(SysMsgEnum.NOT_TASK);
        }
        if (result.isSuccess()) {
            loadingTaskServer.updateLoadPersonStatus(param);
            String data = JSONObject.toJSONString(result.getData());
            ConfirmPO confirmPO = gson.fromJson(data, ConfirmPO.class);

            List<OrderIdAndLoadingAmountPO> orderPOList = confirmPO.getOrderPOList();
            List<String> childOrderIds = new ArrayList<String>();
            OrderIdsBO orderIdsBO = new OrderIdsBO();
            Map<String, String> map = new HashMap<>(16);
            if (!ObjectUtils.isEmpty(orderPOList)) {
                String orderId = null;
                for (OrderIdAndLoadingAmountPO orderInfo : orderPOList) {

                    if (!ObjectUtils.isEmpty(orderInfo.getOrderId())) {
                        if (orderInfo.getOrderId().indexOf(LoadingTaskConstant.SUBSTRING_ORDER) > 0) {

                            orderId = orderInfo.getOrderId().substring(0,
                                    orderInfo.getOrderId().indexOf(LoadingTaskConstant.SUBSTRING_ORDER));

                            map.put(orderInfo.getOrderId(), orderId);
                            childOrderIds.add(orderId);
                        } else {
                            map.put(orderInfo.getOrderId(), orderInfo.getOrderId());
                            childOrderIds.add(orderInfo.getOrderId());
                        }
                    }

                    orderIdsBO.setChildOrderIds(childOrderIds);
                }

            }
            List<AbnormalOrderPO> abnormalInfo = abnormalOrderInfo(childOrderIds, param);

            List<OrderInfoPO> orderInfo = loadingTaskOrderServer.getChildOrderList(orderIdsBO);

            if (!ObjectUtils.isEmpty(orderInfo)) {

                List<OrderInfoPO> orderlist = new ArrayList<>();
                for (OrderInfoPO orderInfoPO : orderInfo) {
                    orderInfoPO.setOrderStatus(orderInfoPO.getFstatus());
                    if (AppConstant.GROUP_ORDER_DOUBLE.equals(orderInfoPO.getFdblflag())) {
                        orderlist.add(orderInfoPO);
                    }
                }
                for (OrderInfoPO orderInformation : orderlist) {

                    for (AbnormalOrderPO abnormalOrderPO : abnormalInfo) {
                        if (orderInformation.getFchildorderid().equals(abnormalOrderPO.getOrderId())) {
                            if (!abnormalOrderPO.getAbnomalAmount().equals(0)) {
                                orderInformation.setAbnomalAmount(abnormalOrderPO.getAbnomalAmount());
                            }
                        }
                    }
                }
                orderlist.stream().forEach(info -> {
                    List<OrderIdAndLoadingAmountPO> loadingAmount = orderPOList.stream()
                            .filter(amount -> info.getFchildorderid().equals(map.get(amount.getOrderId())))
                            .collect(Collectors.toList());
                    info.setLoadingAmount(loadingAmount.get(0).getLoadingAmount());
                    info.setFchildorderid(loadingAmount.get(0).getOrderId());
                });

                confirmPO.setOrderInfo(orderlist);
            }
            return MsgTemplate.successMsg(confirmPO);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);

    }

    /**
     * 获取异常订单信息
     * 
     * @param childOrderIds
     * @param param
     * @return
     */
    public List<AbnormalOrderPO> abnormalOrderInfo(List<String> childOrderIds, ConfirmBO param) {
        OrderIdListBO orderIdListBO = new OrderIdListBO();
        orderIdListBO.setList(childOrderIds);
        orderIdListBO.setPartnerId(param.getPartnerId());
        HttpResult abnormalInfo = abnormalServer.getOrderByOrderIdList(orderIdListBO);
        List<AbnormalOrderPO> abnormalOrderInfoList = new ArrayList<>();
        if (abnormalInfo.isSuccess()) {
            String abnormalData = gson.toJson(abnormalInfo.getData());
            abnormalOrderInfoList = JSONArray.parseArray(abnormalData, AbnormalOrderPO.class);
        }
        return abnormalOrderInfoList;
    }

    /**
     * 装车
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> loading(LoadingBO param) {
        OrderIdBO orderIdBO = new OrderIdBO();
        if (!ObjectUtils.isEmpty(param)) {
            Integer loadingAmount = param.getLoadingAmount();
            Integer orderAmount = param.getOrderAmount();
            if (!orderAmount.equals(loadingAmount)) {
                param.setOnceOrderid(new StringBuffer(param.getOrderId()).append("-1").toString());
                param.setTwiceOrderid(new StringBuffer(param.getOrderId()).append("-2").toString());
                if (!ObjectUtils.isEmpty(loadingAmount)) {
                    // 全部退库
                    if (loadingAmount == 0) {
                        param.setCancelStockAmount(orderAmount);
                        param.setCancelType(LoadingTaskConstant.CANCEL_TYPE_1);
                        //更新订单状态
                        if (updateOrderStatus(LoadingTaskConstant.REDUNDANTSTATUS_24, param.getOrderId(), param.getPartnerId(), param.getVersion())) {
                            
                            HttpResult result = loadingTaskServer.loading(param);
                            return MsgTemplate.customMsg(result);
                        } else {
                            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
                        }
                        

                    } else {
                        // 部分退库
                        param.setCancelStockAmount(orderAmount - loadingAmount);
                        param.setCancelType(LoadingTaskConstant.CANCEL_TYPE_2);
                        HttpResult result = loadingTaskServer.loading(param);
                        return MsgTemplate.customMsg(result);
                        // 更新-1订订单状态
                        /*Boolean updateOnce = updateOrderStatus(LoadingTaskConstant.REDUNDANTSTATUS_25, param.getOnceOrderid(), param.getPartnerId(),
                                param.getVersion());
                        Boolean updateTwice = false;
                        if (updateOnce) {
                            // 更新-2订单状态
                            updateTwice = updateOrderStatus(LoadingTaskConstant.REDUNDANTSTATUS_24, param.getTwiceOrderid(), param.getPartnerId(),
                                    param.getVersion());
                        } else {
                            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
                        }*/
                        /*if (updateTwice) {
                            HttpResult result = loadingTaskServer.loading(param);
                            return MsgTemplate.customMsg(result);
                        }*/
                    }
                }
            }
        }

        // 正常装车
        /*
         * orderIdBO.setOrderId(param.getOrderId()); orderIdBO.setStatus("25");
         * orderIdBO.setPartnerId(param.getPartnerId());
         * orderIdBO.setVersion(param.getVersion()); HttpResult updateResult =
         * loadingTaskOrderServer.updateOrderStatus(orderIdBO);
         */
        if (updateOrderStatus(LoadingTaskConstant.REDUNDANTSTATUS_25, param.getOrderId(), param.getPartnerId(), param.getVersion())) {
            HttpResult result = loadingTaskServer.loading(param);
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    public boolean updateOrderStatus(String status, String orderId, String partnerId, String version) {
        OrderIdBO orderIdBO = new OrderIdBO();
        orderIdBO.setOrderId(orderId);
        orderIdBO.setStatus(status);
        orderIdBO.setPartnerId(partnerId);
        orderIdBO.setVersion(version);
        HttpResult updateResult = loadingTaskOrderServer.updateOrderStatus(orderIdBO);
        if (updateResult.isSuccess()) {
            return true;
        }
        return false;
    }

    /**
     * 追加订单
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> additionalOrder(AdditionalOrderBO param) {
        param.setApplicationTime("NOW()");
        param.setProposer(param.getOperator());
        param.setOperatorId(param.getOperatorId());
        HttpResult result = loadingTaskServer.additionalOrder(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 驳回申请
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> rejectRequest(RejectRequestBO param) {
        // 推送驳回消息
        pushMsg(param);
        param.setDisposeStatus(LoadingTaskConstant.DISPOSESTATUS_2);
        param.setHandler(param.getOperator());
        param.setHandlerId(param.getOperatorId());
        HttpResult result = loadingTaskServer.rejectRequest(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 推送驳回申请消息
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    public void pushMsg(RejectRequestBO param) {
        try {
            String json = gson.toJson(param);
            appProducer.sendPushMsg(json);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * 追加订单申请列表web
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> addOrderApplicationList(AddOrderApplicationListBO param) {
        HttpResult result = loadingTaskServer.addOrderApplicationList(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 完成装车
     *
     * @param param
     * @return
     * @author WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> finishLoading(FinishLoadingBO param) {
        FinishLoadingPO result = loadingTaskServer.finishLoading(param);
        System.out.println(result.getLoadingTaskPO());
        System.out.println(param);
        if (ObjectUtils.isEmpty(result.getLoadingTaskPO())) {
            return MsgTemplate.failureMsg(SysMsgEnum.NOT_DEAL);
        }
        // 该订单数据状态应到订单自取
        List<OrderRedundantPO> orderPOList = result.getOrderPOList();
        if (!ObjectUtils.isEmpty(orderPOList)) {
            for (OrderRedundantPO info : orderPOList) {
                if (!LoadingTaskConstant.REDUNDANTSTATUS_25.equals(info.getStatus())) {
                    return MsgTemplate.failureMsg(SysMsgEnum.NOT_DEAL);
                }
            }
        }
        param.setStatus(LoadingTaskConstant.WAYBILLID_STATUS_20);
        HttpResult updateResult = loadingTaskServer.updateWayBill(param);
        return MsgTemplate.customMsg(updateResult);
    }

}
