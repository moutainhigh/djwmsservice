package com.djcps.wms.stocktaking.orderresult;

import java.io.Serializable;

public class OrderResult implements Serializable {

    private static final long serialVersionUID = -2121676855794908248L;

    private InnerDate data;

    private String msg;

    private int code;

    private boolean success;

    public InnerDate getData() {
        return data;
    }

    public void setData(InnerDate data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "OrderResult [data=" + data + ", msg=" + msg + ", code=" + code + ", success=" + success
                + "]";
    }
}