package com.djcps.wms.warehouse.enums;

/**
 *
 * @author Chengw
 * @since 2018/1/26 10:27.
 */
public enum WareHouseTypeEnum {

    /**
     * 仓库编码类型
     */
    WAREHOUSE_CODE("11"),
    /**
     * 库区编码类型
     */
    WAREHOUSE_AREA_CODE("2"),
    /**
     * 库位编码类型
     */
    WAREHOUSE_AREA_LOCA_CODE("3")
    ;

    private String value;

    WareHouseTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
