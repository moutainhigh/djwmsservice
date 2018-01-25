package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * 订单服务参数接收
 * @author:wzy
 * @company:djwms
 * @create:2018/1/23
 **/
public class ListFOrderInfoPO {
    private List<FOrderInfoBO> fOrderInfoBOList;

    public List<FOrderInfoBO> getfOrderInfoBOList() {
        return fOrderInfoBOList;
    }

    public void setfOrderInfoBOList(List<FOrderInfoBO> fOrderInfoBOList) {
        this.fOrderInfoBOList = fOrderInfoBOList;
    }

}
