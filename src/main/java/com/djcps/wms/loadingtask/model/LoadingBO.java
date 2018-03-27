package com.djcps.wms.loadingtask.model;

import com.djcps.wms.commons.base.BaseAddBO;

/**
 * 装车信息参数
 * 
 * @author WYB
 * @since 2018/3/21
 */
public class LoadingBO extends BaseAddBO {

    /**
     * 
     */
    private static final long serialVersionUID = -6623274182421249708L;
    /**
     * 装车数量
     */
    private Integer loadingAmount;
    /**
     * 订单数量
     */
    private Integer orderAmount;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 运单编号
     */
    private String wayBillId;
    /**
     * 运单状态 1待提货,5部分提货,10提货完成,15部分装车,20装车完成
     */
    private Integer status;

    /**
     * 冗余表订单状态21,部分入库 ,22 ,已入库：23 ,已配货：24, 已提货：25, 已装车：26 ,已发车
     */
    /* private Integer redundantStatus; */

    /**
     * 退库数量
     */
    private Integer cancelStockAmount;

    /**
     * 退库类型1全部退库 2部分退库
     */
    private Integer cancelType;

    /**
     * 新订单id-1
     */
    private String onceOrderid;

    /**
     * 新订单id-2
     */
    private String twiceOrderid;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCancelStockAmount() {
        return cancelStockAmount;
    }

    public void setCancelStockAmount(Integer cancelStockAmount) {
        this.cancelStockAmount = cancelStockAmount;
    }

    public Integer getCancelType() {
        return cancelType;
    }

    public void setCancelType(Integer cancelType) {
        this.cancelType = cancelType;
    }

    public String getOnceOrderid() {
        return onceOrderid;
    }

    public void setOnceOrderid(String onceOrderid) {
        this.onceOrderid = onceOrderid;
    }

    public String getTwiceOrderid() {
        return twiceOrderid;
    }

    public void setTwiceOrderid(String twiceOrderid) {
        this.twiceOrderid = twiceOrderid;
    }

    public Integer getLoadingAmount() {
        return loadingAmount;
    }

    public void setLoadingAmount(Integer loadingAmount) {
        this.loadingAmount = loadingAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(String wayBillId) {
        this.wayBillId = wayBillId;
    }

    @Override
    public String toString() {
        return "LoadingBO [loadingAmount=" + loadingAmount + ", orderAmount=" + orderAmount + ", orderId=" + orderId
                + ", wayBillId=" + wayBillId + ", status=" + status + ", cancelStockAmount=" + cancelStockAmount
                + ", cancelType=" + cancelType + ", onceOrderid=" + onceOrderid + ", twiceOrderid=" + twiceOrderid
                + "]";
    }

}
