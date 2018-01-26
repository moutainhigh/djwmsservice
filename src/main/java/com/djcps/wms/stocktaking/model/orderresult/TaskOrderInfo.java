package com.djcps.wms.stocktaking.model.orderresult;

import com.djcps.wms.stocktaking.model.SaveStocktakingOrderInfoBO;

import java.util.List;

/**
 * 特殊的http参数接收类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
public class TaskOrderInfo {
    private Integer total;

    List<SaveStocktakingOrderInfoBO>  result;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SaveStocktakingOrderInfoBO> getResult() {
        return result;
    }

    public void setResult(List<SaveStocktakingOrderInfoBO> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TaskOrderInfo{" +
                "total=" + total +
                ", result=" + result +
                '}';
    }
}
