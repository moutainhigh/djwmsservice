package com.djcps.wms.stock.enums;

/**
 * 入库类型
 * @author Chengw
 * @since 2018/1/26 12:17.
 */
public enum StockTypeEnum {
    /**
     * 部分入库
     */
    LESS_ADD_STOCK("21"),
    /**
     * 已入库
     */
    ALL_ADD_STOCK("22");

    private String value;

    StockTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "StockTypeEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
