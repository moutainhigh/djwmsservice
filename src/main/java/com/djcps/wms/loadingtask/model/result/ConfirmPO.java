package com.djcps.wms.loadingtask.model.result;

import java.util.List;

/**
 * 装车员分类信息集合
 * 
 * @author:wyb
 * @date:2018/3/19
 **/
public class ConfirmPO {

    /**
     * 
     */
    private static final long serialVersionUID = 6869759586390861819L;
    /**
     * 运单编号
     */
    private String waybillId;
    /**
     * 装车台名称
     */
    private String name;
    /**
     * 车牌号
     */
    private String plateNumber;
    /**
     * 订单编号
     */
    private List<OrderIdAndLoadingAmountPO> orderPOList;
    /**
     * 订单信息
     */
    private List<OrderInfoPO> orderInfo;

    public List<OrderInfoPO> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderInfoPO> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public List<OrderIdAndLoadingAmountPO> getOrderPOList() {
        return orderPOList;
    }

    public void setOrderPOList(List<OrderIdAndLoadingAmountPO> orderPOList) {
        this.orderPOList = orderPOList;
    }

    @Override
    public String toString() {
        return "ConfirmPO [waybillId=" + waybillId + ", name=" + name + ", plateNumber=" + plateNumber
                + ", orderPOList=" + orderPOList + ", orderInfo=" + orderInfo + "]";
    }

}
