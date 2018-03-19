package com.djcps.wms.stocktaking.model;

import java.util.Date;

/**
 * 不带f的订单详细参数
 * @author  wzy
 * @param
 * @return
 * @date  2018/2/2 14:05
 **/
public class OrderInfoBO {
    private String orderId;

    private String childOrderid;

    /**
     * 联系方式
     */
    private String contactWay;
    /**
     * 联系人
     */
    private String consignee;

    /**
     * 客户名称
     */
    private String pusername;

    private String dblFlag;

    /**
     * 材料id
     */
    private String materialId;

    /**
     * 楞型
     */
    private String fluteType;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 提货单号
     */
    private String deliveryorder;

    private String boxModel;

    private Double boxWidth;

    private Double boxLength;

    private Double boxHeight;

    private Double materialLength;

    private Double materialWidth;

    /**
     * 材料名
     */
    private String materialName;

    /**
     * 产品名
     */
    private String groupgoodName;

    /**
     * 下料规格
     */
    private String materialRule;

    /**
     * 产品规格
     */
    private String productRule;

    /**
     * 订单数量
     */
    private Integer amount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 订单状态(
     *	已付款:2
     *	已分发:3
     * 	部分入库：21
     已入库：22
     已配货：23
     已提货：24
     已装车：25
     已发车：26
     )
     */
    private Integer status;

    /**
     * 经纬度
     */
    private String lnglat;

    /**
     * 支付时间
     */
    private Date paymenttime;

    /**
     * 地址街道
     */
    private String addressdetail;

    /**
     * 省市区
     */
    private String codeprovince;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public OrderInfoBO(){
        super();
        this.unit = "片";
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChildOrderid() {
        return childOrderid;
    }

    public void setChildOrderid(String childOrderid) {
        this.childOrderid = childOrderid;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPusername() {
        return pusername;
    }

    public void setPusername(String pusername) {
        this.pusername = pusername;
    }

    public String getDblFlag() {
        return dblFlag;
    }

    public void setDblFlag(String dblFlag) {
        this.dblFlag = dblFlag;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getFluteType() {
        return fluteType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryorder() {
        return deliveryorder;
    }

    public void setDeliveryorder(String deliveryorder) {
        this.deliveryorder = deliveryorder;
    }

    public String getBoxModel() {
        return boxModel;
    }

    public void setBoxModel(String boxModel) {
        this.boxModel = boxModel;
    }

    public Double getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(Double boxWidth) {
        this.boxWidth = boxWidth;
    }

    public Double getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(Double boxLength) {
        this.boxLength = boxLength;
    }

    public Double getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(Double boxHeight) {
        this.boxHeight = boxHeight;
    }

    public Double getMaterialLength() {
        return materialLength;
    }

    public void setMaterialLength(Double materialLength) {
        this.materialLength = materialLength;
    }

    public Double getMaterialWidth() {
        return materialWidth;
    }

    public void setMaterialWidth(Double materialWidth) {
        this.materialWidth = materialWidth;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getGroupgoodName() {
        return groupgoodName;
    }

    public void setGroupgoodName(String groupgoodName) {
        this.groupgoodName = groupgoodName;
    }

    public String getMaterialRule() {
        return materialRule;
    }

    public void setMaterialRule(String materialRule) {
        this.materialRule = materialRule;
    }

    public String getProductRule() {
        return productRule;
    }

    public void setProductRule(String productRule) {
        this.productRule = productRule;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getCodeprovince() {
        return codeprovince;
    }

    public void setCodeprovince(String codeprovince) {
        this.codeprovince = codeprovince;
    }

    @Override
    public String toString() {
        return "OrderInfoBO{" +
                "orderId='" + orderId + '\'' +
                ", childOrderid='" + childOrderid + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", consignee='" + consignee + '\'' +
                ", pusername='" + pusername + '\'' +
                ", dblFlag='" + dblFlag + '\'' +
                ", materiafId='" + materialId + '\'' +
                ", fluteType='" + fluteType + '\'' +
                ", orderTime=" + orderTime +
                ", deliveryorder='" + deliveryorder + '\'' +
                ", boxModel='" + boxModel + '\'' +
                ", boxWidth=" + boxWidth +
                ", boxLength=" + boxLength +
                ", boxHeight=" + boxHeight +
                ", materialLength=" + materialLength +
                ", materiaWidth=" + materialWidth +
                ", materialName='" + materialName + '\'' +
                ", groupgoodName='" + groupgoodName + '\'' +
                ", materialRule='" + materialRule + '\'' +
                ", productRule='" + productRule + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", status=" + status +
                ", lnglat='" + lnglat + '\'' +
                ", paymenttime=" + paymenttime +
                ", addressdetail='" + addressdetail + '\'' +
                ", codeprovince='" + codeprovince + '\'' +
                '}';
    }
}
