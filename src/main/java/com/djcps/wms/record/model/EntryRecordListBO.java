package com.djcps.wms.record.model;

import com.djcps.wms.commons.base.BaseListBO;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取入库移库操作记录列表参数接受类
 * 
 * @author wyb
 * @version 1.0
 * @since 2018/3/6
 *
 */
public class EntryRecordListBO extends BaseListBO {
    /**
     * 
     */
    private static final long serialVersionUID = 1581505530307356271L;
    /**
     * 订单编号
     */
    @NotBlank
    private String orderId;
    /**
     * 操作类型集合
     */
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "EntryRecordListBO [orderId=" + orderId + ", list=" + list + "]";
    }

}