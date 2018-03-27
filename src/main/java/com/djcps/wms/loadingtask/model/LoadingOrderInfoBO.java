package com.djcps.wms.loadingtask.model;

public class LoadingOrderInfoBO {
    /**
     * 运单单号
     */
    private String wayBillId;
    /**
     * 合作方编号
     */
    private String partnerId;
    /**
     * 合作方姓名
     */
    private String partnerName;
    /**
     * 合作方区域
     */
    private Integer partnerArea;
    /**
     * 订单状态21,部分入库 ,22 ,已入库：23 ,已配货：24, 已提货：25, 已装车：26 ,已发车
     */
    private Integer status;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 材料名称
     */
    private String materialName;
    /**
     * 下料规格长
     */
    private String materialLength;
    /**
     * 下料规格宽
     */
    private String materialWidth;
    /**
     * 纸箱规格长
     */
    private String boxLength;
    /**
     * 纸箱规格宽
     */
    private String boxWidth;
    /**
     * 纸箱规格高
     */
    private String boxHeight;
    /**
     * 支付时间
     */
    private String paymentTime;
    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 退库订单号
     */
    private String canceleStockOrderId;
}
