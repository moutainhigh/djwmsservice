package com.djcps.wms.stocktaking.constant;

/**
 * @title:盘点常量类
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/11
 **/
public class StocktakingTaskConstant {

    /**
     * 订单状态_待作业
     */
    public static final Integer ONT_WORK = 1;

    /**
     * 订单状态_作业中
     */
    public static final Integer WORKING = 2;

    /**
     * 订单状态_已完成
     */
    public static final Integer COMPLETE = 3;

    /**
     * 订单状态_已关闭
     */
    public static final Integer CLOSE = 4;

    /**
     * 作业状态未完成
     */
    public static final Integer Status_1 = 1;

    /**
     * 作业状态已完成
     */
    public static final Integer Status_3 = 3;

    /**
     * 仅保存
     */
    public static final Integer CONFIRM_ONLY = 1;

    /**
     * 保存并打印
     */
    public static final Integer CONFIRM_PRINT = 2;

    /**
     * 保存并推送
     */
    public static final Integer CONFIRM_PUSH = 3;

    /**
     * PDA作业状态,未下发
     */
    public static final Integer PDASTATUS_0 = 0;

    /**
     * PDA作业状态,已下发
     */
    public static final Integer PDASTATUS_1 = 1;
}
