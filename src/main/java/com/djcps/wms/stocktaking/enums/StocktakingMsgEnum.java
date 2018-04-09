package com.djcps.wms.stocktaking.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;

/**
 * 盘点枚举类
 * @author:wzy
 * @date:2018/4/9
 **/
public enum StocktakingMsgEnum implements MsgInterface{

    /**
     * 订单状态修改失败
     */
    STOCKTAKING_ORDER_WRONG(1, "订单号错误"),

    /**
     * 该订单已在任务中
     */
    STOCKTAKING_ORDER_EXIST(2,"该订单已在任务中"),

    /**
     * 获取库位关联订单信息失败
     */
    LOCORDER_FAIL(3,"获取库位关联订单信息失败")
    ;

    private int code;

    private String msg;

    StocktakingMsgEnum(Integer code,String msg) {
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX+AppConstant.WMS_MODULE_STOCKTAKING_ENUM_PREFIX+code;
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

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
