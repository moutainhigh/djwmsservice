package com.djcps.wms.record.model.OperationRecordResult;

import java.io.Serializable;

/**
 * 特殊的http参数接收类
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
public class OperationRecordResult implements Serializable {

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
