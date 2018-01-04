package com.djcps.wms.commons.httpclient;

import java.io.Serializable;

/**
 * 订单服务获取订单例外result
 * @company:djwms
 * @author:zdx
 * @date:2018年1月3日
 */
public class OrderHttpResult implements Serializable {

	private static final long serialVersionUID = -5325765796819634146L;

	private Object data;
    
    private String msg;
    
    private int code;
    
    private boolean success;
    
    private Integer totalCount;
    
    public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
		return "OrderHttpResult [data=" + data + ", msg=" + msg + ", code=" + code + ", success=" + success
				+ ", totalCount=" + totalCount + "]";
	}
    
}
