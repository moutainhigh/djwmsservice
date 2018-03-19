package com.djcps.wms.commons.httpclient;

import java.io.Serializable;

/**
 * 带total的HttpResult的对象结果
 * @company:djwms
 * @author:zdx
 * @date:2018年2月4日
 */
public class OtherHttpResult implements Serializable{

	private static final long serialVersionUID = -5254870905221060001L;

	private Object data;
    
    private String msg;
    
    private int code;
    
    private boolean success;

    private Integer total;
    
    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

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
		return "OtherHttpResult [data=" + data + ", msg=" + msg + ", code=" + code + ", success=" + success + ", total="
				+ total + "]";
	}

}
