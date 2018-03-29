package com.djcps.wms.loadingtask.constant;

/**
 * 运单订单各常量类
 * @author:wzy
 * @date:2018/3/23
 **/
public class LoadingTaskConstant {

    /**
     * 运单状态 1待提货
     */
    public static final Integer WAYBILLID_STATUS_1=1;

    /**
     * 运单状态 5部分提货
     */
    public static final Integer WAYBILLID_STATUS_5=5;

    /**
     * 运单状态 10提货完成
     */
    public static final Integer WAYBILLID_STATUS_10=10;

    /**
     * 运单状态 15部分装车
     */
    public static final Integer WAYBILLID_STATUS_15=15;

    /**
     * 运单状态 20装车完成
     */
    public static final Integer WAYBILLID_STATUS_20=20;

    /**
     * 处理状态(0待处理)
     */
    public static final Integer DISPOSESTATUS_0=0;

    /**
     * 处理状态(1已通过)
     */
    public static final Integer DISPOSESTATUS_1=1;

    /**
     * 处理状态(2已驳回)
     */
    public static final Integer DISPOSESTATUS_2=2;

    /**
     * 冗余表订单状态 21,部分入库
     */
    public static final Integer REDUNDANTSTATUS_21=21;

    /**
     * 冗余表订单状态 22 ,已入库
     */
    public static final Integer REDUNDANTSTATUS_22=22;

    /**
     * 冗余表订单状态 23 ,已配货
     */
    public static final Integer REDUNDANTSTATUS_23=23;

    /**
     * 冗余表订单状态 24, 已提货
     */
    public static final Integer REDUNDANTSTATUS_24=24;

    /**
     * 冗余表订单状态 25, 已装车
     */
    public static final Integer REDUNDANTSTATUS_25=25;

    /**
     * 冗余表订单状态 26 ,已发车
     */
    public static final Integer REDUNDANTSTATUS_26=26;
    /**
     * 全部退库1
     */
    public static final Integer CANCEL_TYPE_1=1;
    /**
     * 部分退库2
     */
    public static final Integer CANCEL_TYPE_2=2;
    /**
     * 装车员空闲状态
     */
    public static final Integer LOADINGPERSON_TYPE_0=0;
    /**
     * 装车员繁忙状态
     */
    public static final Integer LOADINGPERSON_TYPE_1=1;
}
