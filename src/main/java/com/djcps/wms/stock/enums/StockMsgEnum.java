package com.djcps.wms.stock.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * @title:系统参数枚举
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月28日
 */
public enum StockMsgEnum implements MsgInterface {
    /**
     * 错误代码枚举
     */
	
	REDUNDANT_FAIL(1, "冗余表订单插入失败,请联系管理员"),
	
	/**
	 * 入库数量有误请重新核对
	 */
	SAVE_AMOUNT_ERROE(2, "入库数量有误请重新核对"),
	
	SPLIT_ORDER_NOT_STOC(3, "已拆分的子单不允许进行入库操作"),
	
	ADD_STOCK_WAREHOUSE_ERROR(4,"请选择和订单类型相同的仓库");

    private int code;
    
    private String msg;

   StockMsgEnum(Integer code, String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_ENTRY_ENUM_PREFIX+code;
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
