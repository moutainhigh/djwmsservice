package com.djcps.wms.loadingtask.model.result;

import java.util.List;

/**
 * 完成装车
 * 
 * @author:wyb
 * @date:2018/3/22
 **/
public class FinishLoadingPO {
    /**
     * 装车任务信息
     */
    private LoadingTaskPO loadingTaskPO;
    /**
     * 冗余表订单信息
     */
    private List<OrderRedundantPO> orderPOList;

    public LoadingTaskPO getLoadingTaskPO() {
        return loadingTaskPO;
    }

    public void setLoadingTaskPO(LoadingTaskPO loadingTaskPO) {
        this.loadingTaskPO = loadingTaskPO;
    }

    public List<OrderRedundantPO> getOrderPOList() {
        return orderPOList;
    }

    public void setOrderPOList(List<OrderRedundantPO> orderPOList) {
        this.orderPOList = orderPOList;
    }

    @Override
    public String toString() {
        return "FinishLoadingPO [loadingTaskPO=" + loadingTaskPO + ", orderPOList=" + orderPOList + "]";
    }

}
