package com.djcps.wms.commons.base;
/**
 * 生成操作记录所需订单相关字段
 * @company:djwms
 * @author:wyb
 * @date:2018年5月3日
 */
public class BaseOperationRecordBO extends BaseAddBO {
    /**
     * 
     */
    private static final long serialVersionUID = -1169479734585149898L;
    /**
     * 业务关联名称
     */
    private String relativeName;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 塄型
     */
    private String fluteType;
    /**
     * 操作面积
     */
    private String area;

    public String getRelativeName() {
        return relativeName;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getFluteType() {
        return fluteType;
    }

    public String getArea() {
        return area;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setFluteType(String fluteType) {
        this.fluteType = fluteType;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "BaseOperationRecordBO [relativeName=" + relativeName + ", orderType=" + orderType + ", fluteType="
                + fluteType + ", area=" + area + "]";
    }

}
