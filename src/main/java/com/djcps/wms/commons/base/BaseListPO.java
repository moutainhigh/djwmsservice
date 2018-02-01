package com.djcps.wms.commons.base;

import java.util.List;

/**
 * @author Chengw
 * @since 2018/1/31 16:53.
 */
public class BaseListPO {

    private List list;

    private Integer total;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BaseListPO{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
