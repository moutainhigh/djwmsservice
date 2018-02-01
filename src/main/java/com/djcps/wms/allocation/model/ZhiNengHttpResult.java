package com.djcps.wms.allocation.model;

import java.io.Serializable;

public class ZhiNengHttpResult implements Serializable{

	private static final long serialVersionUID = 9084908797851017080L;
	
	    private Object data;
	    
	    private String msg;
	    
	    private int code;
	    
	    private boolean success;
	    private String uuid;


		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
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
			return "ZhiNengHttpResult [data=" + data + ", msg=" + msg + ", code=" + code + ", success=" + success
					+ ", uuid=" + uuid + "]";
		}
}
