package com.djcps.wms.record.enums;

/**
 * 操作类型 枚举
 * @author Chengw
 * @create 2018/4/23 10:13.
 * @since 1.0.0
 */
public enum OperationTypeEnum {
    /**
     * 操作类型
     * 1 入库
     * 2 推荐库区
     * 3 移库
     * 4 配货
     * 5 取消配货
     * 6 提货
     * 7 退库
     * 8 装车
     * 9 盘点
     * 10 发起异常
     * 11 异常处理
     * 12 拆单处理
     */
    ENTRY(1),
    DELIVERY(6),
    CANCEL_STOCK(9)
    ;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    OperationTypeEnum(Integer code) {
        this.code = code;
    }

}
