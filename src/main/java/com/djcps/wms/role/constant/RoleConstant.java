package com.djcps.wms.role.constant;
/**
 * 角色参数
 * 
 * @author wyb
 * @since 2018/44/16
 */
public class RoleConstant {
  //=====================用户工作状态类型====================
    /**
     *工作状态 空闲
     */
    public static final Integer FREE = 1;

    /**
     *工作状态 忙碌
     */
    public static final Integer BUSY = 2;

    /**
     *工作状态 休息中
     */
    public static final Integer TAKE_REST = 3;
  //=====================ORG角色类型类型====================
    /**
     * 常规
     */
    public static final String CONVENTIONAL = "0";
    /**
     * 系统
     */
    public static final String SYSTEM = "1";
    /**
     * 价值观审核人
     */
    public static final String VALUER = "2";
    /**
     * 张力通知人
     */
    public static final String TENSION_NOTIFIER = "3";
    
}
