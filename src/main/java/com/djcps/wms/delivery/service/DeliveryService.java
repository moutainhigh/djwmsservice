package com.djcps.wms.delivery.service;

import com.djcps.wms.delivery.model.*;

import java.util.Map;

/**
 * 提货 service
 * @author Chengw
 * @since 2018/1/31 08:26.
 */
public interface DeliveryService {


    /**
     * 获取提货单列表
     * @author Chengw
     * @since 2018/1/31  09:26
     * @param param
     * @return
     */
    Map<String,Object> list(ListDeliveryBO param);

    /**
     * 获取提货单订单列表
     * @author Chengw
     * @since 2018/1/31  09:26
     * @param param
     * @return
     */
    Map<String,Object> listOrder(ListDeliveryOrderBO param);

    /**
     * 增加打印次数
     * @author Chengw
     * @since 2018/1/31  09:28
     * @param param
     * @return
     */
    Map<String,Object> print(PrintDeliveryBO param);

    /**
     * 完成单条提货订单的提货
     * @author Chengw
     * @since 2018/1/31  09:29
     * @param param
     * @return
     */
    Map<String,Object> completeOrder(SaveDeliveryBO param);

    /**
     * 获取提货订单列表 - PDA 
     * @author Chengw
     * @since 2018/2/1  13:22
     * @param param
     * @return
     */
    Map<String,Object> listOrderForPDA(DeliveryOrderBO param);

    /**
     * 获取提货任务信息 - PDA
     * @author Chengw
     * @since 2018/2/1  13:10
     * @param param
     * @return
     */
    Map<String,Object> getDeliveryForPDA(DeliveryOrderBO param);

    /**
     * 获取提货订单详细信息 - PDA
     * 订单详细信息、库存信息
     * @author Chengw
     * @since 2018/2/1  13:09
     * @param param
     * @return
     */
    Map<String,Object> getOrderDetail(DeliveryOrderDetailBO param);
    /**
     * 设置提货单的确认状态为未确认 
     * @author wyb
     * @since 2018/3/13
     * @param param
     * @return
     */
    Map<String,Object> updateDeliveryEffect(UpdateDeliveryEffectBO param);

}
