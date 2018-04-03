package com.djcps.wms.commons.enums;

import com.djcps.wms.commons.constant.AppConstant;
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
	OPS_SUCCESS(1, "操作成功"),

	/**
	 * 操作失败,基本也不用
	 */
	OPS_FAILURE(2, "操作失败"),

	/**
	 * 系统异常，服务器报错
	 */
	SYS_EXCEPTION(3, "系统异常,请联系管理员"),

	/**
	 * token过期或未登入
	 */
	NOT_LOGIN(4, "抱歉,您还未登录,或token已过期,请登录后再试"),

	/**
	 * 路径失效
	 */
	URL_EXPIRE(5, "请求路径已失效,请联系管理员"),

	/**
	 * 请求参数错误
	 */
	PARAM_ERROR(6, "请求参数有误"),

	/**
	 * redis中取不到用户URL,其实没有记录ip地址
	 */
	SYSURL_NULL(7, "非法操作,已记录了你的ip"),

	LENGTH_BEYOND(8, "字符长度不符合标准"),

	/**
	 * 订单已存在
	 */
	ORDER_EXIST(9, "该订单已在任务中"),

	/**
	 * 订单号错误
	 */
	ORDER_WRONG(10, "订单号错误"),

	NOT_DEAL(11, "还有任务未处理无法完成装车"),

	NOT_TASK(12, "当前没有任务"),

	NO_HAVE_WAREHOUSE(13, "当前无可用仓库"),

	WAREHOUSE_ERROR(14, "该仓库有误请重新核实"),

	OUTORDER_FAIL(15, "生成出库单失败");

	private int code;

	private String msg;

	SysMsgEnum(int code, String msg) {
		this.code = AppConstant.WMS_MODULE_SYS_ENUM_PREFIX + AppConstant.WMS_MODULE_SYS_ENUM_PREFIX + code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
