package com.djcps.wms.stocktaking.orderresult;

import java.io.Serializable;

public class CompleteStatusResult implements Serializable {

    private static final long serialVersionUID = -2892154908866597917L;

    private CompleteStatus date;

    private String msg;

    private int code;

    private boolean success;

    public CompleteStatus getDate() {
        return date;
    }

    public void setDate(CompleteStatus date) {
        this.date = date;
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
        return "CompleteStatusResult{" +
                "date=" + date +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", success=" + success +
                '}';
    }
}
