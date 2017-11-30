package com.djcps.wms.interceptor.enums;

import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:拦截器枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月13日
 */
public enum InterceptorEnums implements MsgInterface{
	
	OPS_SUCCESS(100000, "操作成功"),
    
    OPS_FAILURE(100001, "操作失败"),
    
    SYS_EXCEPTION(100002, "系统异常,已通知系统管理员，请重新登录后再试"),
    
    NOT_LOGIN(100003, "抱歉，您还未登录，或token已过期，请登录后再试"),
    
    OPS_ILLEGAL(100004, "非法操作，系统已记录您的ip，并已通知系统管理员"),
    
    NOT_AUTH(100005, "抱歉，您暂无权限访问，请联系相关人员"),
    
    UNKNOW(100006, "未知错误"),
    
    PARAMS_ERROR(-100000, "参数错误");
	
	private int code;
	
    private String msg;

	InterceptorEnums(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	InterceptorEnums(int code) {
		this.code = code;
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
