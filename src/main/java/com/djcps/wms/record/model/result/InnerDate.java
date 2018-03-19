package com.djcps.wms.record.model.result;

/**
 * 特殊的http参数接收
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
public class InnerDate {

    private Integer total;

    private Object result;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "InnerDate{" +
                "total=" + total +
                ", result=" + result +
                '}';
    }
}
