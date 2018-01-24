package com.djcps.wms.commons.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum SysMsgEnum implements MsgInterface {
    
    /**
     * 操作成功,基本不用
     */
    OPS_SUCCESS(880001, "操作成功"),
    
    /**
     * 操作失败,基本也不用
     */
    OPS_FAILURE(880002, "操作失败"),
    
    /**
     * 系统异常，服务器报错
     */
    SYS_EXCEPTION(880003, "系统异常,请稍后再试"),
    
    /**
     * token过期或未登入
     */
    NOT_LOGIN(880004, "抱歉,您还未登录,或token已过期,请登录后再试"),
    
    /**
     * 路径失效
     */
    URL_EXPIRE(880005, "请求路径已失效,请联系管理员"),
	
	/**
	 * 请求参数错误
	 */
	PARAM_ERROR(880006, "请求参数有误"),
	
	/**
	 * redis中取不到用户URL,其实没有记录ip地址
	 */
	SYSURL_NULL(880007, "非法操作,已记录了你的ip"),
    
	/**
	 * 编码错误,请重新获取
	 */
	CODE_ERROE(880009, "编码失效,请重刷新页面"),
	
	/**
	 * 编码新增或删除有误,请联系管理员!!!
	 */
	DELETE_CODE_ERROE(880010, "编码新增或删除有误,请联系管理员!!!"),
	
	/**
	 * 入库数量有误请重新核对
	 */
	SAVE_AMOUNT_ERROE(880011, "入库数量有误请重新核对"),
	
	/**
	 * 订单状态修改失败
	 */
	ORDER_UPDATE_ERROR(880012, "订单状态修改失败,且入库失败,请联系管理员"),
	
	/**
	 * 订单有误请重新配货
	 */
	ORDER_ERROR_ALREADY_ALLOCATION(880013, "订单有误请重新配货");
	
    private int code;
    
    private String msg;

   SysMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public int getCode() {
        return code;
    }
    @Override
    public String getMsg() {
        return msg;
    }

}
