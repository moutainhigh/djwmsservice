package com.djcps.wms.order.model.onlinepaperboard;

import java.util.List;

import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 * 线上纸板订单resut中的data映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月12日
 */
public class PaperboardResultDataPO {
	
	/**
	 * 映射对象list
	 */
	private List<WarehouseOrderDetailPO> content;
	
	/**
	 * 总条数
	 */
	private Integer totalSize;

	public List<WarehouseOrderDetailPO> getContent() {
		return content;
	}

	public void setContent(List<WarehouseOrderDetailPO> content) {
		this.content = content;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	@Override
	public String toString() {
		return "OnlinePaperboardResultDataPO [content=" + content + ", totalSize=" + totalSize + "]";
	}
	
}
