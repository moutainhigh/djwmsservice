package com.djcps.wms.loadingtask.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.OrderInfoPO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.model.result.ConfirmPO;
import com.djcps.wms.loadingtask.model.result.FinishLoadingPO;
import com.djcps.wms.loadingtask.model.result.OrderIdAndLoadingAmountPO;
import com.djcps.wms.loadingtask.model.result.OrderRedundantPO;
import com.djcps.wms.loadingtask.server.LoadingTaskOrderServer;
import com.djcps.wms.loadingtask.server.LoadingTaskServer;
import com.djcps.wms.loadingtask.service.LoadingTaskService;
import com.djcps.wms.order.model.OrderIdsBO;
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

    /**
     * 获取装车员列表
     *
     * @param param
     * @return
     * @autuor WYB
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
     * @autuor WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> removeLoadingPerson(RemoveLoadingPersonBO param) {
        param.setStatus(0);
        HttpResult result = loadingTaskServer.removeLoadingPerson(param);
            return MsgTemplate.customMsg(result);
    }

    /**
     * 装车台界面确认
     *
     * @param param
     * @return
     * @autuor WYB
     * @since 2018/3/20
     */
    @Override
    public Map<String, Object> confirm(ConfirmBO param) {
        
        HttpResult updateLoadPersonResult = loadingTaskServer.updateLoadPersonStatus(param);
        HttpResult result = loadingTaskServer.getOrderList(param);
            if (result.isSuccess()) {
                String data = JSONObject.toJSONString(result.getData());
                ConfirmPO confirmPO = gson.fromJson(data, ConfirmPO.class);
                List<OrderIdAndLoadingAmountPO> orderPOList = confirmPO.getOrderPOList();
                List<String> childOrderIds = new ArrayList<String>();
                OrderIdsBO orderIdsBO = new OrderIdsBO();
                for(OrderIdAndLoadingAmountPO orderInfo :orderPOList) {
                    childOrderIds.add(orderInfo.getOrderId());
                    orderIdsBO.setChildOrderIds(childOrderIds);
                }
                List<OrderInfoPO> orderInfo = loadingTaskOrderServer.getChildOrderList(orderIdsBO);
                orderInfo.stream().forEach(info -> {
                    List<OrderIdAndLoadingAmountPO> loadingAmount = orderPOList.stream()
                            .filter(amount -> info.getFchildorderid().equals(amount.getOrderId()))
                            .collect(Collectors.toList());
                    info.setLoadingAmount(loadingAmount.get(0).getLoadingAmount());
                });
                
                return MsgTemplate.successMsg(confirmPO);
            }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 装车
     *
     * @param param
     * @return
     * @autuor WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> loading(LoadingBO param) {
        HttpResult result = loadingTaskServer.loading(param);
            return MsgTemplate.customMsg(result);
    }

    /**
     * 追加订单
     *
     * @param param
     * @return
     * @autuor WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> additionalOrder(AdditionalOrderBO param) {
        HttpResult result = loadingTaskServer.additionalOrder(param);
            return MsgTemplate.customMsg(result);
    }
    /**
     * 驳回申请
     *
     * @param param
     * @return
     * @autuor WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> rejectRequest(RejectRequestBO param) {
        HttpResult result = loadingTaskServer.rejectRequest(param);
            return MsgTemplate.customMsg(result);
    }
    /**
     * 追加订单申请列表web
     *
     * @param param
     * @return
     * @autuor WYB
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
     * @autuor WYB
     * @since 2018/3/21
     */
    @Override
    public Map<String, Object> finishLoading(FinishLoadingBO param) {
        FinishLoadingPO result = loadingTaskServer.finishLoading(param);
        if(ObjectUtils.isEmpty(result.getLoadingTaskPO())) {
            return MsgTemplate.failureMsg(SysMsgEnum.NOT_DEAL);
        }
        List<OrderRedundantPO> orderPOList = result.getOrderPOList();
        if(!ObjectUtils.isEmpty(orderPOList)) {
            for(OrderRedundantPO info :orderPOList) {
                if(!"25".equals(info.getStatus())) {
                    return MsgTemplate.failureMsg(SysMsgEnum.NOT_DEAL);
                }
            }
        }
        param.setStatus(20);
        HttpResult updateResult = loadingTaskServer.updateWayBill(param);
        return MsgTemplate.customMsg(updateResult);
    }

}
