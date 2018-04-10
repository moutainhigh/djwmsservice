package com.djcps.wms.loadingtask.enums;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.msg.MsgInterface;
/**
 * 
 * @author ldh
 *
 */
public enum LoadingTaskEnum implements MsgInterface {

    NOT_TASK(1, "当前没有任务"),
    
    NOTLOADING(2,"还未提货无法装车"),
    
    NOT_DEAL(3, "还有任务未处理无法完成装车"),
    
	/**
	 * 运单号错误
	 */
	WAYBILLID_ERROR(4,"运单号错误,获取不到信息"),
	/**
	 * 
	 */
	GET_ORDERDETAIL_FAIL(5,"获取订单详情失败"),
	/**
	 * 生成出库单失败
	 */
	OUTORDER_FAIL(6,"生成出库单失败")
	;
	
	 private int code;

    private String msg;

    LoadingTaskEnum(int code,String msg){
        this.code = AppConstant.WMS_MSG_ENUM_PREFIX + AppConstant.WMS_LOADINGTASK_ENUM_PREFIX + code;
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
