package com.djcps.wms.commons.httpclient;

import java.io.Serializable;

/**
 * @author
 */
public class HttpResult implements Serializable {

    private static final long serialVersionUID = -6964977326736882682L;
    
    private Object data;
    
    private String msg;
    
    private int code;
    
    private boolean success;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
        return "HttpResult [data=" + data + ", msg=" + msg + ", code=" + code + ", success=" + success + "]";
    }
    
    
}
