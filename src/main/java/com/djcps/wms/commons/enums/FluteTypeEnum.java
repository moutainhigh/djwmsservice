package com.djcps.wms.commons.enums;

/**
 * 楞型枚举
 * @company:djwms
 * @author:zdx
 * @date:2018年3月15日
 */
public enum FluteTypeEnum {
	
	 /**
     * 楞型
    
	"BC瓦"= 1
    "BE瓦"= 2
    "单C瓦"= 3
    "单B瓦"= 4
    "单E瓦"= 5
    "EBC瓦"= 6
    "EE瓦"= 7
     */
    BC_NUMBER("1"),
    BE_NUMBER("2"),
    C_NUMBER("3"),
    B_NUMBER("4"),
    E_NUMBER("5"),
    EBC_NUMBER("6"),
    EE_NUMBER("7"),
    
    BC_STRING("BC瓦"),
    BE_STRING("BE瓦"),
    C_STRING("单C瓦"),
    B_STRING("单C瓦"),
    E_STRING("单E瓦"),
    EBC_STRING("EBC瓦"),
    EE_STRING("EE瓦"),
    ;
	
    private String value;

    FluteTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
