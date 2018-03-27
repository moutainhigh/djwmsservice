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
    BC("BC瓦"),
    BE("BE瓦"),
    C("单C瓦"),
    B("单B瓦"),
    E("单E瓦"),
    EBC("EBC瓦"),
    EE("EE瓦");
	
    private String value;

    FluteTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
