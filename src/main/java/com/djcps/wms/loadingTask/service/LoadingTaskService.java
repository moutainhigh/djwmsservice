package com.djcps.wms.loadingTask.service;

import java.util.Map;

import com.djcps.wms.loadingTask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingTask.model.AdditionalOrderBO;
import com.djcps.wms.loadingTask.model.ConfirmBO;
import com.djcps.wms.loadingTask.model.LoadingBO;
import com.djcps.wms.loadingTask.model.LoadingPersonBO;
import com.djcps.wms.loadingTask.model.RejectRequestBO;
import com.djcps.wms.loadingTask.model.RemoveLoadingPersonBO;

/**
 * 装车 service
 * 
 * @author wyb
 * @since 2018/3/19
 */
public interface LoadingTaskService {

    /**
     * 获取装车员列表
     * 
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> loadingPersonList(LoadingPersonBO param);

    /**
     * 移除装车员
     * 
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> removeLoadingPerson(RemoveLoadingPersonBO param);

    /**
     * 装车员界面确认
     * 
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> confirm(ConfirmBO param);

    /**
     * 装车
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> loading(LoadingBO param);

    /**
     * 追加订单
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> additionalOrder(AdditionalOrderBO param);
    /**
     * 驳回申请
     * 
     * @autuor wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String,Object> rejectRequest(RejectRequestBO param);
    /**
     * 追加订单申请列表web
     * @autuor wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String,Object> addOrderApplicationList(AddOrderApplicationListBO param);
}
