package com.djcps.wms.allocation.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum AllocationMsgEnum implements MsgInterface {
	
	/**
	 * 订单状态修改失败
	 */
	ORDER_UPDATE_ERROR(1, "订单状态修改失败,且入库失败,请联系管理员"),

    /**
     * 订单已存在
     */
    ORDER_EXIST(2, "该订单已在任务中"),

    /**
     * 订单号错误
     */
    ORDER_WRONG(3, "订单号错误"),
    
    /**
	 * 订单有误请重新配货
	 */
	ORDER_ERROR_ALREADY_ALLOCATION(4, "订单有误请重新配货"),
	
	ORDER_IS_NULL(5, "查无此订单"),
	
	REDUNDANT_FAIL(6, "冗余订单插入失败"),
	
	ORDER_STATUS_ERROR(7,"订单状态有误"),
	
	AGAIN_CHOOSE_ORDER(8,"该订单有误,请选择已配货订单"),
	
	VERIFY_ALLOCATION_ERROR(9,"请勿同时进行确认配货"),
	
	AGAIN_VERIFY_ALLOCATION_ERROR(10,"请勿同时进行确认优化"),
	
	ALREADY_INTELLIGENT_ALLOCATION(11,"此次配货已被确认,请重新获取"),
	
	SPLIT_ORDER_ERROR(12,"拆分订单有误,子单号查询不到订单详情!!!"),
	
    NOT_ALLOW_SPLIT_ORDER(13,"只有已配货和部分配货的订单才能进行拆分!!!"),
    
    LESS_STOK_FIRST_ORDER_AMOUNT_ERROR(14,"部分入库拆后订单,已入库的拆单数量有误!!!"),
    
    LESS_STOK_SECOND_ORDER_AMOUNT_ERROR(15,"部分入库拆后订单,待入库的拆单数量有误!!!"),
    
    ALL_STOCK_ORDER_SUBNUMBER_ERROR(16,"已入库订单,拆分订单的总拆分数量有误!!!"),
    
    NO_HAVING_ORDER_ALLOCATION(17,"当前无订单参与配货!!!"),
    
    SPLIT_ORDER_PRODUCE_STORED_ERROR(18,"已参与生产补单的订单和分线执行的订单无法拆分!!!");

    private int code;
    
    private String msg;

   AllocationMsgEnum(Integer code, String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_MODULE_ALLOCATION_ENUM_PREFIX+code;
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
