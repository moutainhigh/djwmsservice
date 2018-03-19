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
     * 待作业
     */
    public static final Integer ONT_WORK = 1;

    /**
     * 作业中
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
    public static final Integer STATUS_1 = 1;

    /**
     * 作业状态已完成
     */
    public static final Integer STATUS_3 = 3;

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

    /**
     * pda作业中
     */
    public static final Integer PDASTATUS_2 = 2;

    /**
     * pda已完成
     */
    public static final Integer PDASTATUS_3 = 3;

    /**
     * 有未完成盘点订单，无法完成盘点
     */
    public static final Integer NOTCOMPLETE= 1;

    /**
     * isadd是否正常新增 0正常，1新增，2盘盈
     */
    public static final Integer ISADD_NORMAL= 0;

    /**
     * 1新增
     */
    public static final Integer ISADD_NEW= 1;

    /**
     * 2盘盈
     */
    public static final Integer ISADD_SURPLUS= 2;

    /**
     * 是盘盈
     */
    public static final Integer INVENTORY_IS=1;

    /**
     * 正常订单非盘盈
     */
    public static final Integer INVENTORY_NORMAL=2;

}
