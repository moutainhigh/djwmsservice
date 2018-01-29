package com.djcps.wms.stocktaking.service;

import com.djcps.wms.stocktaking.model.AddStocktakingBO;

import java.util.Map;

/**
 * @title:盘点订单业务层
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/10
 **/
public interface StocktakingOrderService {

    /**
     * 批量获取订单详情
     * @author  wzy
     * @param addStocktakingBO
     * @return
     * @create  2018/1/11 13:09
     **/
    Map<String,Object> getInfoByChildIds(AddStocktakingBO addStocktakingBO);
}
