package com.djcps.wms.loadingtask.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;

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
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> loadingPersonList(LoadingPersonBO param);

    /**
     * 移除装车员
     * 
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> removeLoadingPerson(RemoveLoadingPersonBO param);

    /**
     * 装车员界面确认
     * 
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> confirm(ConfirmBO param);

    /**
     * 装车
     * 
     * @author wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> loading(LoadingBO param);

    /**
     * 追加订单
     * 
     * @author wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> additionalOrder(AdditionalOrderBO param);

    /**
     * 驳回申请
     * 
     * @author wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> rejectRequest(RejectRequestBO param);

    /**
     * 追加订单申请列表web
     * 
     * @author wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> addOrderApplicationList(AddOrderApplicationListBO param);

    /**
     * 完成装车
     * 
     * @author wyb
     * @since 2018/3/21
     * @param param
     * @return
     */
    Map<String, Object> finishLoading(FinishLoadingBO param);

	/**
	 * 根据用户登录id获取装车台id
	 * @param partnerInfoBO
	 * @return
	 * @author:zdx
	 * @date:2018年4月4日
	 */
	Map<String, Object> getLoadingTableIdByUserId(PartnerInfoBO partnerInfoBO);
}
