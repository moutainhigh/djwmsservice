package com.djcps.wms.loadingtask.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.loadingtask.model.result.OrderInventoryPO;
import com.djcps.wms.record.model.OrderOperationRecordPO;

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
    /**
     * 实际提货数量
     */
    private Integer realDeliveryAmount;
    /**
     * 在库数量
     */
    private Integer amountInStock;

    /**
     * 出库数量
     */
    private Integer amountOutStock;

    /**
     * 订单装车操作记录信息
     */
    private List<OrderOperationRecordPO> list;
    /**
     * 退库拆单操作记录信息
     */
    private List<OrderOperationRecordPO> splitOrder;
    /**
     * 部分退库时插入冗余表数据
     */
    private List<OrderRedundantBO> orderRedundantBOList;
    /**
     * 部分退库时存入入库表订单信息
     */
    private List<OrderInventoryPO> orderInventoryBOList;

    public List<OrderInventoryPO> getOrderInventoryBOList() {
        return orderInventoryBOList;
    }

    public void setOrderInventoryBOList(List<OrderInventoryPO> orderInventoryBOList) {
        this.orderInventoryBOList = orderInventoryBOList;
    }

    public List<OrderRedundantBO> getOrderRedundantBOList() {
        return orderRedundantBOList;
    }

    public void setOrderRedundantBOList(List<OrderRedundantBO> orderRedundantBOList) {
        this.orderRedundantBOList = orderRedundantBOList;
    }

    public Integer getAmountInStock() {
        return amountInStock;
    }

    public Integer getAmountOutStock() {
        return amountOutStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        this.amountInStock = amountInStock;
    }

    public void setAmountOutStock(Integer amountOutStock) {
        this.amountOutStock = amountOutStock;
    }

    public List<OrderOperationRecordPO> getSplitOrder() {
        return splitOrder;
    }

    public void setSplitOrder(List<OrderOperationRecordPO> splitOrder) {
        this.splitOrder = splitOrder;
    }

    public List<OrderOperationRecordPO> getList() {
        return list;
    }

    public void setList(List<OrderOperationRecordPO> list) {
        this.list = list;
    }

    public Integer getRealDeliveryAmount() {
        return realDeliveryAmount;
    }

    public void setRealDeliveryAmount(Integer realDeliveryAmount) {
        this.realDeliveryAmount = realDeliveryAmount;
    }

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
                + ", realDeliveryAmount=" + realDeliveryAmount + ", amountInStock=" + amountInStock
                + ", amountOutStock=" + amountOutStock + ", list=" + list + ", splitOrder=" + splitOrder
                + ", orderRedundantBOList=" + orderRedundantBOList + ", orderInventoryBOList=" + orderInventoryBOList
                + "]";
    }

}
