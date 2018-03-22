package com.djcps.wms.loadingTask.service.impl;

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
import com.djcps.wms.loadingTask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingTask.model.AdditionalOrderBO;
import com.djcps.wms.loadingTask.model.ConfirmBO;
import com.djcps.wms.loadingTask.model.LoadingBO;
import com.djcps.wms.loadingTask.model.LoadingPersonBO;
import com.djcps.wms.loadingTask.model.OrderInfoPO;
import com.djcps.wms.loadingTask.model.RejectRequestBO;
import com.djcps.wms.loadingTask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingTask.model.result.ConfirmPO;
import com.djcps.wms.loadingTask.model.result.OrderIdAndLoadingAmountPO;
import com.djcps.wms.loadingTask.model.result.addOrderApplicationResult;
import com.djcps.wms.loadingTask.server.LoadingTaskOrderServer;
import com.djcps.wms.loadingTask.server.LoadingTaskServer;
import com.djcps.wms.loadingTask.service.LoadingTaskService;
import com.djcps.wms.stocktaking.model.orderresult.InnerDate;

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
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        HttpResult result = loadingTaskServer.removeLoadingPerson(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        if (!ObjectUtils.isEmpty(result)) {
            if (result.isSuccess()) {
                String data = JSONObject.toJSONString(result.getData());
                ConfirmPO confirmPO = gson.fromJson(data, ConfirmPO.class);
                List<OrderIdAndLoadingAmountPO> orderPOList = confirmPO.getOrderPOList();
                List<OrderInfoPO> orderInfo = loadingTaskOrderServer.getChildOrderList(orderPOList);
                orderInfo.stream().forEach(info -> {
                    List<OrderIdAndLoadingAmountPO> loadingAmount = orderPOList.stream()
                            .filter(amount -> info.getFchildorderid().equals(amount.getOrderId()))
                            .collect(Collectors.toList());
                    info.setLoadingAmount(loadingAmount.get(0).getLoadingAmount());
                });
                return MsgTemplate.successMsg(orderInfo);
            }
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
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        if(!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        addOrderApplicationResult result = loadingTaskServer.addOrderApplicationList(param);
        if(ObjectUtils.isEmpty(result.getData())) {
            return MsgTemplate.successMsg();
        }
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(result.getData().getTotal());
        innerDate.setResult(result.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

}
