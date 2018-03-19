package com.djcps.wms.loadingtable.model;

import java.io.Serializable;

/**
 * @title    获取随机编号
 * @author  wzy
 * @create  2017/12/21 10:48
 **/
public class GetNumberBO implements Serializable {

    private static final long serialVersionUID = 1716108404288752798L;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GetNumberBO{" +
                "count=" + count +
                '}';
    }
}
