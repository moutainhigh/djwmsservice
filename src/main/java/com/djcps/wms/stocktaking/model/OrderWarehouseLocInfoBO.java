package com.djcps.wms.stocktaking.model;
import com.djcps.wms.commons.constant.AppConstant;

import java.util.List;

/**
 * 检测是否需要盘盈参数类，已不用
 * @author  wzy
 * @param
 * @return
 * @create  2018/1/26 14:08
 **/
public class OrderWarehouseLocInfoBO {
    private String version;

    private List<OrderIdBO> orderIds;

    public String getVersion() {
        return version;
    }

    public void setVersion() {
        this.version = AppConstant.DEFAULT_VERSION;
    }


    public List<OrderIdBO> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<OrderIdBO> orderIds) {
        this.orderIds = orderIds;
    }

    @Override
    public String toString() {
        return "OrderWarehouseLocInfoBO{" +
                "version='" + version + '\'' +
                ", orderIds=" + orderIds +
                '}';
    }
}
