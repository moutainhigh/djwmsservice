package com.djcps.wms.stocktaking.service.impl;

import com.djcps.wms.stocktaking.model.AddStocktakingBO;
import com.djcps.wms.stocktaking.server.StocktakingOrderServer;
import com.djcps.wms.stocktaking.service.StocktakingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 盘点订单实现类
 * @author  wzy
 * @param
 * @return
 * @create  2018/1/10 11:20
 **/
@Service
public class StocktakingOrderServiceimpl implements StocktakingOrderService{

    @Autowired
    private StocktakingOrderServer stocktakingOrderServer;

    @Override
    public Map<String, Object> getInfoByChildIds(AddStocktakingBO addStocktakingBO) {
        return null;
    }


}
