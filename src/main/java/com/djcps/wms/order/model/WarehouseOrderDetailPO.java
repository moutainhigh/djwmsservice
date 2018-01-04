package com.djcps.wms.order.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 纸板映射对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月21日
 */
public class WarehouseOrderDetailPO implements Serializable{

	private static final long serialVersionUID = -4276569892759744580L;
	
	/**
	 * 产品规格,长
	 */
	private String fboxlength;
	
	/**
	 * 产品规格,宽
	 */
	private String fboxwidth;
	
	/**
	 * 产品规格,高
	 */
	private String fboxheight;
	
	/**
	 * 下料规格长
	 */
	private String fmateriallength;
	
	/**
	 * 下料规格宽
	 */
	private String fmaterialwidth;
	
	/**
	 * 下单时间
	 */
	private Date fordertime;
	
	/**
	 * 支付时间
	 */
	private Date fpaymenttime;
	
	/**
	 * 交期时间
	 */
	private Date fdelivery;
	
	/**
	 * 产品名称
	 */
	private String fgroupgoodname;
	
	/**
	 * 楞型
	 */
	private String fflutetype;
	
	/**
	 * 材料名称
	 */
	private String fmaterialname;
	
	/**
	 * 下料规格
	 */
	private String fmaterialRule;
	
	/**
	 * 产品规格
	 */
	private String fproductRule;
	
	/**
	 * 单位
	 */
	private String units;
	
	/**
	 * 订单状态(
	 *	已付款:2
	 *	已分发:3
	 * 	部分入库：21  
		已入库：22 
		已配货：23  
		已提货：24 
		已装车：25  
		已发车：26
		)
	 */
	private String fstatus;
	
	/**
	 * 联系人
	 */
	private String fconsignee;
	
	/**
	 * 联系方式
	 */
	private String fcontactway;
	
	/**
	 * 省市区
	 */
	private String fcodeprovince;
	
	/**
	 * 地址街道
	 */
	private String faddressdetail;
	
	/**
	 * 客户名称
	 */
	private String fpusername;
	
	/**
	 * =====================================================================
	 */
	
	/**
	 * 订单号
	 */
	private String fchildorderid;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 经纬度
	 */
	private String flnglat;
	
	/**
	 * 已入库数量
	 */
	private String amountSaved;
	
	/**
	 * 订单数量
	 */
	private String famount;
	
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
	 * 库区list
	 */
	private List<WarehouseAreaBO> areaList;
	
	public WarehouseOrderDetailPO() {
		super();
		this.units = "片";
	}
	
	public Date getFpaymenttime() {
		return fpaymenttime;
	}

	public void setFpaymenttime(Date fpaymenttime) {
		this.fpaymenttime = fpaymenttime;
	}

	public String getFchildorderid() {
		return fchildorderid;
	}

	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}

	public Date getFdelivery() {
		return fdelivery;
	}

	public void setFdelivery(Date fdelivery) {
		this.fdelivery = fdelivery;
	}

	public void setFordertime(Date fordertime) {
		this.fordertime = fordertime;
	}

	public String getFgroupgoodname() {
		return fgroupgoodname;
	}

	public void setFgroupgoodname(String fgroupgoodname) {
		this.fgroupgoodname = fgroupgoodname;
	}

	public String getFflutetype() {
		return fflutetype;
	}

	public void setFflutetype(String fflutetype) {
		this.fflutetype = fflutetype;
	}

	public String getFmaterialname() {
		return fmaterialname;
	}

	public void setFmaterialname(String fmaterialname) {
		this.fmaterialname = fmaterialname;
	}

	public String getFmaterialRule() {
		return fmaterialRule;
	}

	public void setFmaterialRule(String fmaterialRule) {
		this.fmaterialRule = fmaterialRule;
	}

	public String getFproductRule() {
		return fproductRule;
	}

	public void setFproductRule(String fproductRule) {
		this.fproductRule = fproductRule;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}

	public String getAmountSaved() {
		return amountSaved;
	}

	public void setAmountSaved(String amountSaved) {
		this.amountSaved = amountSaved;
	}

	public String getFamount() {
		return famount;
	}

	public void setFamount(String famount) {
		this.famount = famount;
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

	public List<WarehouseAreaBO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBO> areaList) {
		this.areaList = areaList;
	}

	public String getFboxlength() {
		return fboxlength;
	}

	public void setFboxlength(String fboxlength) {
		this.fboxlength = fboxlength;
	}

	public String getFboxwidth() {
		return fboxwidth;
	}

	public void setFboxwidth(String fboxwidth) {
		this.fboxwidth = fboxwidth;
	}

	public String getFboxheight() {
		return fboxheight;
	}

	public void setFboxheight(String fboxheight) {
		this.fboxheight = fboxheight;
	}

	public String getFmateriallength() {
		return fmateriallength;
	}

	public void setFmateriallength(String fmateriallength) {
		this.fmateriallength = fmateriallength;
	}

	public String getFmaterialwidth() {
		return fmaterialwidth;
	}

	public void setFmaterialwidth(String fmaterialwidth) {
		this.fmaterialwidth = fmaterialwidth;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFlnglat() {
		return flnglat;
	}

	public void setFlnglat(String flnglat) {
		this.flnglat = flnglat;
	}

	public Date getFordertime() {
		return fordertime;
	}

	public String getFconsignee() {
		return fconsignee;
	}

	public void setFconsignee(String fconsignee) {
		this.fconsignee = fconsignee;
	}

	public String getFcontactway() {
		return fcontactway;
	}

	public void setFcontactway(String fcontactway) {
		this.fcontactway = fcontactway;
	}

	public String getFcodeprovince() {
		return fcodeprovince;
	}

	public void setFcodeprovince(String fcodeprovince) {
		this.fcodeprovince = fcodeprovince;
	}

	public String getFaddressdetail() {
		return faddressdetail;
	}

	public void setFaddressdetail(String faddressdetail) {
		this.faddressdetail = faddressdetail;
	}

	public String getFpusername() {
		return fpusername;
	}

	public void setFpusername(String fpusername) {
		this.fpusername = fpusername;
	}

	@Override
	public String toString() {
		return "WarehouseOrderDetailPO [fboxlength=" + fboxlength + ", fboxwidth=" + fboxwidth + ", fboxheight="
				+ fboxheight + ", fmateriallength=" + fmateriallength + ", fmaterialwidth=" + fmaterialwidth
				+ ", fordertime=" + fordertime + ", fpaymenttime=" + fpaymenttime + ", fdelivery=" + fdelivery
				+ ", fgroupgoodname=" + fgroupgoodname + ", fflutetype=" + fflutetype + ", fmaterialname="
				+ fmaterialname + ", fmaterialRule=" + fmaterialRule + ", fproductRule=" + fproductRule + ", units="
				+ units + ", fstatus=" + fstatus + ", fconsignee=" + fconsignee + ", fcontactway=" + fcontactway
				+ ", fcodeprovince=" + fcodeprovince + ", faddressdetail=" + faddressdetail + ", fpusername="
				+ fpusername + ", fchildorderid=" + fchildorderid + ", orderId=" + orderId + ", flnglat=" + flnglat
				+ ", amountSaved=" + amountSaved + ", famount=" + famount + ", amount=" + amount + ", warehouseId="
				+ warehouseId + ", warehouseName=" + warehouseName + ", remark=" + remark + ", areaList=" + areaList
				+ "]";
	}
	
}
