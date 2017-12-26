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
     * 操作成功
     */
    OPS_SUCCESS(880001, "操作成功"),
    
    /**
     * 操作失败
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
	 * redis中取不到用户URL
	 */
	SYSURL_NULL(880007, "非法操作,已记录了你的ip"),
    
	/**
	 * 编码错误,请重新获取
	 */
	CODE_ERROE(880009, "编码失效,请重刷新页面");
	
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
