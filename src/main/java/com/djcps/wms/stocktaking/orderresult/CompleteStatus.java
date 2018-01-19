package com.djcps.wms.stocktaking.orderresult;

public class CompleteStatus {
    private Integer Status;

    private Object result;

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
