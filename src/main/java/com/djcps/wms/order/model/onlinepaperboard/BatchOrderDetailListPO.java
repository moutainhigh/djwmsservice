package com.djcps.wms.order.model.onlinepaperboard;

import java.util.List;

import com.djcps.wms.order.model.WarehouseOrderDetailPO;

/**
 * 批量混合查询,订单详情映射对象
 * @company:djwms
 * @author:zdx
 * @date:2018年4月13日
 */
public class BatchOrderDetailListPO {
	
	/**
	 * 子单的list集合
	 */
	private List<WarehouseOrderDetailPO> orderList;
	
	/**
	 * 拆单的list集合
	 */
	private List<WarehouseOrderDetailPO> splitOrderList;

	public List<WarehouseOrderDetailPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<WarehouseOrderDetailPO> orderList) {
		this.orderList = orderList;
	}

	public List<WarehouseOrderDetailPO> getSplitOrderList() {
		return splitOrderList;
	}

	public void setSplitOrderList(List<WarehouseOrderDetailPO> splitOrderList) {
		this.splitOrderList = splitOrderList;
	}

	@Override
	public String toString() {
		return "BatchOrderDetailListPO [orderList=" + orderList + ", splitOrderList=" + splitOrderList + "]";
	}
	
}
