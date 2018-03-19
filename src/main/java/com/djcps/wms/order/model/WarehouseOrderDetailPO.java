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
	private String unit;
	
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
	private Integer fstatus;
	
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
	 * 母账号名称
	 */
	private String fpusername;
	
	/**
	 * 子账号名称
	 */
	private String fcusername;
	
	private String fdblflag;
	
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
	private Integer amountSaved;
	
	/**
	 * 订单数量
	 */
	private Integer famount;
	
	/**
	 * 订单数量
	 */
	private Integer amount;
	
	/**
	 * 订单数量
	 */
	private Integer orderAmount;
	
	/**
	 * 备注
	 */
	private String remark;
	
	  /**
	   * 材料id
	   */
	private String materialId;
	
	  /**
	   * 库存数量
	   */
	private Integer  instockAmount;
	
	/**
	 * 库区list
	 */
	private List<WarehouseAreaBO> areaList;
	
	/**
	 * =====================================================================
	 */
	
	/**
	 * 运单号
	 */
	private String waybillId;
	/**
	 * 提货单号
	 */
	private String deliveryId;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 订单提醒
	 */
	private String remind;
	/**
	 * 提货单状态
	 */
	private String deliveryIdStatus;
	/**
	 * 装车顺序
	 */
	private Integer sequence;
	
	/**
	 * 提货员id
	 */
	private String pickerId;
	/**
	 * 提货员名称
	 */
	private String pickerName;
	/**
	 * 装车员id
	 */
	private String loadingPersonId;
	/**
	 * 装车员名称
	 */
	private String loadingPersonName;
	
	/**
	 * 提货数量
	 */
	private Integer deliveryAmount;
	
	/**
	 * ============================================================
	 */
	
	/**
	 * 仓库编码
	 */
	private String warehouseId;
	/**
	 * 仓库名称
	 */
	private String warehouseName;
	/**
	 * 库区编码
	 */
	private String warehouseAreaId;
	/**
	 * 库区名称
	 */
	private String warehouseAreaName;
	/**
	 * 库位编码
	 */
	private String warehouseLocId;
	/**
	 * 库位名称
	 */
	private String warehouseLocName;
	
	/**
	 * 智能配货id
	 */
	private String allocationId;
	
	
	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public Integer getInstockAmount() {
		return instockAmount;
	}

	public void setInstockAmount(Integer instockAmount) {
		this.instockAmount = instockAmount;
	}

	public Integer getFstatus() {
		return fstatus;
	}

	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}

	public String getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(String warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	public String getWarehouseAreaName() {
		return warehouseAreaName;
	}

	public void setWarehouseAreaName(String warehouseAreaName) {
		this.warehouseAreaName = warehouseAreaName;
	}

	public String getWarehouseLocId() {
		return warehouseLocId;
	}

	public void setWarehouseLocId(String warehouseLocId) {
		this.warehouseLocId = warehouseLocId;
	}

	public String getWarehouseLocName() {
		return warehouseLocName;
	}

	public void setWarehouseLocName(String warehouseLocName) {
		this.warehouseLocName = warehouseLocName;
	}

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public WarehouseOrderDetailPO() {
		super();
		this.unit = "片";
	}

	public Integer getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(Integer deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
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

	public Date getFordertime() {
		return fordertime;
	}

	public void setFordertime(Date fordertime) {
		this.fordertime = fordertime;
	}

	public Date getFpaymenttime() {
		return fpaymenttime;
	}

	public void setFpaymenttime(Date fpaymenttime) {
		this.fpaymenttime = fpaymenttime;
	}

	public Date getFdelivery() {
		return fdelivery;
	}

	public void setFdelivery(Date fdelivery) {
		this.fdelivery = fdelivery;
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


	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getFchildorderid() {
		return fchildorderid;
	}

	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFlnglat() {
		return flnglat;
	}

	public void setFlnglat(String flnglat) {
		this.flnglat = flnglat;
	}

	public Integer getAmountSaved() {
		return amountSaved;
	}

	public void setAmountSaved(Integer amountSaved) {
		this.amountSaved = amountSaved;
	}

	public Integer getFamount() {
		return famount;
	}

	public void setFamount(Integer famount) {
		this.famount = famount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
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

	public List<WarehouseAreaBO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<WarehouseAreaBO> areaList) {
		this.areaList = areaList;
	}

	public String getWaybillId() {
		return waybillId;
	}

	public void setWaybillId(String waybillId) {
		this.waybillId = waybillId;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public String getDeliveryIdStatus() {
		return deliveryIdStatus;
	}

	public void setDeliveryIdStatus(String deliveryIdStatus) {
		this.deliveryIdStatus = deliveryIdStatus;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPickerId() {
		return pickerId;
	}

	public void setPickerId(String pickerId) {
		this.pickerId = pickerId;
	}

	public String getPickerName() {
		return pickerName;
	}

	public void setPickerName(String pickerName) {
		this.pickerName = pickerName;
	}

	public String getLoadingPersonId() {
		return loadingPersonId;
	}

	public void setLoadingPersonId(String loadingPersonId) {
		this.loadingPersonId = loadingPersonId;
	}

	public String getLoadingPersonName() {
		return loadingPersonName;
	}

	public void setLoadingPersonName(String loadingPersonName) {
		this.loadingPersonName = loadingPersonName;
	}

	public String getFcusername() {
		return fcusername;
	}

	public void setFcusername(String fcusername) {
		this.fcusername = fcusername;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getFdblflag() {
		return fdblflag;
	}

	public void setFdblflag(String fdblflag) {
		this.fdblflag = fdblflag;
	}

	@Override
	public String toString() {
		return "WarehouseOrderDetailPO [fboxlength=" + fboxlength + ", fboxwidth=" + fboxwidth + ", fboxheight="
				+ fboxheight + ", fmateriallength=" + fmateriallength + ", fmaterialwidth=" + fmaterialwidth
				+ ", fordertime=" + fordertime + ", fpaymenttime=" + fpaymenttime + ", fdelivery=" + fdelivery
				+ ", fgroupgoodname=" + fgroupgoodname + ", fflutetype=" + fflutetype + ", fmaterialname="
				+ fmaterialname + ", fmaterialRule=" + fmaterialRule + ", fproductRule=" + fproductRule + ", unit="
				+ unit + ", fstatus=" + fstatus + ", fconsignee=" + fconsignee + ", fcontactway=" + fcontactway
				+ ", fcodeprovince=" + fcodeprovince + ", faddressdetail=" + faddressdetail + ", fpusername="
				+ fpusername + ", fcusername=" + fcusername + ", fdblflag=" + fdblflag + ", fchildorderid="
				+ fchildorderid + ", orderId=" + orderId + ", flnglat=" + flnglat + ", amountSaved=" + amountSaved
				+ ", famount=" + famount + ", amount=" + amount + ", orderAmount=" + orderAmount + ", remark=" + remark
				+ ", materialId=" + materialId + ", instockAmount=" + instockAmount + ", areaList=" + areaList
				+ ", waybillId=" + waybillId + ", deliveryId=" + deliveryId + ", plateNumber=" + plateNumber
				+ ", remind=" + remind + ", deliveryIdStatus=" + deliveryIdStatus + ", sequence=" + sequence
				+ ", pickerId=" + pickerId + ", pickerName=" + pickerName + ", loadingPersonId=" + loadingPersonId
				+ ", loadingPersonName=" + loadingPersonName + ", deliveryAmount=" + deliveryAmount + ", warehouseId="
				+ warehouseId + ", warehouseName=" + warehouseName + ", warehouseAreaId=" + warehouseAreaId
				+ ", warehouseAreaName=" + warehouseAreaName + ", warehouseLocId=" + warehouseLocId
				+ ", warehouseLocName=" + warehouseLocName + ", allocationId=" + allocationId + "]";
	}

}
