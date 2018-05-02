package com.djcps.wms.commons.base;

import java.io.Serializable;
import java.util.List;
/**
 * 列表返回集
 * @author admin
 *
 */
public class BaseVO implements Serializable {
	
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
		return "BaseVo{" +
				"total=" + total +
				", result=" + result +
				'}';
	}
}
