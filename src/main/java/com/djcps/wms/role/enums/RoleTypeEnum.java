package com.djcps.wms.role.enums;

/**
 * 角色类型枚举
 * @company:djwms
 * @author:zdx
 * @date:2018年5月16日
 */
public enum RoleTypeEnum {
	
	
	/**
	 * 全部的订单状态
	 */
	ROLE_TYPE_1("01","入库员"),
	ROLE_TYPE_2("02","配货员"),
	ROLE_TYPE_3("03","提货员"),
	ROLE_TYPE_4("04","盘点员"),
	ROLE_TYPE_5("05","管理员"),
	ROLE_TYPE_6("06","异常处理员"),
	ROLE_TYPE_7("07","装车台");
	
    private String value;
    
    private String explain;

    RoleTypeEnum(String value,String explain){
        this.value = value;
        this.explain = explain;
    }

    public String getValue() {
        return value;
    }
    
    public String getExplain() {
        return explain;
    }
    
}
