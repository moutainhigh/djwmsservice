package com.djcps.wms.delivery.enums;

/**
 * 提货状态 枚举
 * @author Chengw
 * @since 2018/2/3 11:06.
 */
public enum DeliveryStatusEnum{
    /**
     * 未提货
     */
    UNDONE(0),
    /**
     * 部分未提货
     */
    UNDONE_PART(5),
    /**
     * 已提货
     */
    ACCOMPLISHED(10);
    private Integer value;

    DeliveryStatusEnum(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
