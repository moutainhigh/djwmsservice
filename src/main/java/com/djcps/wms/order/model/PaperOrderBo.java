package com.djcps.wms.order.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.djcps.wms.commons.base.BaseListParam;

/**
 * 纸板纸箱对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class PaperOrderBo implements Serializable{

	private static final long serialVersionUID = -6808929963013139398L;
	
	private String pageNo;
	private String pageSize;
	private String orderId;
	private String xiadanshijian;
	private String jiaoqianshijian;
	private String chanpinmingc;
	private String lengxing;
	private String cailiaomingcheng;
	private String xialiaoguige;
	private String chanpingguige;
	private String danwei;
	private String rukuzhuangtai;
	
	/**
	 * 经纬度
	 */
	private String location;
	/**
	 * 已入库数量
	 */
	private String amountSaved;
	/**
	 * 订单数量
	 */
	private String amount;
	/**
	 * 仓库编号
	 */
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 仓库类型,1,2,3,4,5(纸板，纸箱，积分商城仓库，物料仓库，退货仓库)
	 */
	@NotBlank
	private String warehouseType;
	
	/**
	 * 库区list
	 */
	private List<WarehouseAreaBo> areaList;
	
	public PaperOrderBo() {
		this.orderId = "1111";
		this.xiadanshijian = "2017-12-15 17:08:36";
		this.jiaoqianshijian = "2017-12-15 17:08:36";
		this.chanpinmingc = "郭全凯大便给你吃";
		this.lengxing = "楞型";
		this.cailiaomingcheng = "水泥";
		this.xialiaoguige = "下料规格";
		this.chanpingguige = "产品规格";
		this.danwei = "单位片";
		this.rukuzhuangtai = "入库状态";
		this.location = "120.779630,27.944950";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getXiadanshijian() {
		return xiadanshijian;
	}

	public void setXiadanshijian(String xiadanshijian) {
		this.xiadanshijian = xiadanshijian;
	}

	public String getJiaoqianshijian() {
		return jiaoqianshijian;
	}

	public void setJiaoqianshijian(String jiaoqianshijian) {
		this.jiaoqianshijian = jiaoqianshijian;
	}

	public String getChanpinmingc() {
		return chanpinmingc;
	}

	public void setChanpinmingc(String chanpinmingc) {
		this.chanpinmingc = chanpinmingc;
	}

	public String getLengxing() {
		return lengxing;
	}

	public void setLengxing(String lengxing) {
		this.lengxing = lengxing;
	}

	public String getCailiaomingcheng() {
		return cailiaomingcheng;
	}

	public void setCailiaomingcheng(String cailiaomingcheng) {
		this.cailiaomingcheng = cailiaomingcheng;
	}

	public String getXialiaoguige() {
		return xialiaoguige;
	}

	public void setXialiaoguige(String xialiaoguige) {
		this.xialiaoguige = xialiaoguige;
	}

	public String getChanpingguige() {
		return chanpingguige;
	}

	public void setChanpingguige(String chanpingguige) {
		this.chanpingguige = chanpingguige;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getRukuzhuangtai() {
		return rukuzhuangtai;
	}

	public void setRukuzhuangtai(String rukuzhuangtai) {
		this.rukuzhuangtai = rukuzhuangtai;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAmountSaved() {
		return amountSaved;
	}

	public void setAmountSaved(String amountSaved) {
		this.amountSaved = amountSaved;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}

	public List<WarehouseAreaBo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBo> areaList) {
		this.areaList = areaList;
	}

	@Override
	public String toString() {
		return "PaperOrderBo [pageNo=" + pageNo + ", pageSize=" + pageSize + ", orderId=" + orderId + ", xiadanshijian="
				+ xiadanshijian + ", jiaoqianshijian=" + jiaoqianshijian + ", chanpinmingc=" + chanpinmingc
				+ ", lengxing=" + lengxing + ", cailiaomingcheng=" + cailiaomingcheng + ", xialiaoguige=" + xialiaoguige
				+ ", chanpingguige=" + chanpingguige + ", danwei=" + danwei + ", rukuzhuangtai=" + rukuzhuangtai
				+ ", location=" + location + ", amountSaved=" + amountSaved + ", amount=" + amount + ", warehouseId="
				+ warehouseId + ", warehouseName=" + warehouseName + ", remark=" + remark + ", warehouseType="
				+ warehouseType + ", areaList=" + areaList + "]";
	}

}
