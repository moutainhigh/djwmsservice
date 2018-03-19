package com.djcps.wms.order.model;

import java.io.Serializable;

/**
 * 订单参数子订单参数
 * @company:djwms
 * @author:zdx
 * @date:2017年12月29日
 */
public class ChildOrderParamBO implements Serializable{
	
	private static final long serialVersionUID = 6305424558458967857L;

	/**
	 * 子订单号
	 */
	private String fchildorderid;
	
	/**
	 * 交期开始时间
	 */
	private String deliveryStartTime;
	
	/**
	 * 交期结束时间
	 */
	private String deliveryEndTime;
	
	/**
	 * 材料名称
	 */
	private String fmaterialname;
	
	/**
	 * 产品规格,高
	 */
	private String fboxheight;
	
	/**
	 * 产品规格,长
	 */
	private String fboxlength;
	
	/**
	 * 产品规格,宽
	 */
	private String fboxwidth;
	
	/**
	 * 订单异常状态
	 */
	private String fexcpstatus;
	
	/**
	 * 产品名称
	 */
	private String fgroupgoodname;
	
	/**
	 * 订单状态
	 */
	private String fstatus;
	/**
	 * 下单开始时间
	 */
	private String orderEndTime;
	/**
	 * 下单结束时间
	 */
	private String orderStartTime;
	
	/**
	 * 支付开始时间
	 */
	private String payStartTime;
	
	/**
	 * 支付结束时间
	 */
	private String payEndTime;
	
	/**
	 * 合作方id(就是OA的id)
	 */
	private String fmanufacturer;
	
	/**
	 * 合作方拆分键
	 */
	private String fkeyarea;
	
	/**
	 * 下料规格长
	 */
	private String fmateriallength;
	
	/**
	 * 下料规格宽
	 */
	private String fmaterialwidth;
	
	/**
	 * 合作方名称
	 */
	private String manufacturerName;
	
	/**
	 * 合作方省份
	 */
	private String fprovince;
	
	/**
	 * 合作方城市
	 */
	private String fcity;
	
	public ChildOrderParamBO() {
		this.fexcpstatus = "1";
		this.fmanufacturer = "400";
		this.fkeyarea = "3303";
		this.manufacturerName = "温州东诚包装有限公司";
		this.fprovince = "浙江省";
		this.fcity = "温州市";
		this.fboxheight="-1";
		this.fboxlength="-1";
		this.fboxwidth="-1";
	}
	
	public String getPayStartTime() {
		return payStartTime;
	}

	public void setPayStartTime(String payStartTime) {
		this.payStartTime = payStartTime;
	}

	public String getPayEndTime() {
		return payEndTime;
	}

	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}

	public String getFchildorderid() {
		return fchildorderid;
	}

	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public String getFmaterialname() {
		return fmaterialname;
	}

	public void setFmaterialname(String fmaterialname) {
		this.fmaterialname = fmaterialname;
	}

	public String getFboxheight() {
		return fboxheight;
	}

	public void setFboxheight(String fboxheight) {
		this.fboxheight = fboxheight;
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

	public String getFexcpstatus() {
		return fexcpstatus;
	}

	public void setFexcpstatus(String fexcpstatus) {
		this.fexcpstatus = fexcpstatus;
	}

	public String getFgroupgoodname() {
		return fgroupgoodname;
	}

	public void setFgroupgoodname(String fgroupgoodname) {
		this.fgroupgoodname = fgroupgoodname;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String fstatus) {
		if(fstatus==null){
			this.fstatus="2";
		}else{
			this.fstatus=fstatus;
		}
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getFmanufacturer() {
		return fmanufacturer;
	}

	public void setFmanufacturer(String fmanufacturer) {
		this.fmanufacturer = fmanufacturer;
	}

	public String getFkeyarea() {
		return fkeyarea;
	}

	public void setFkeyarea(String fkeyarea) {
		this.fkeyarea = fkeyarea;
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

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getFprovince() {
		return fprovince;
	}

	public void setFprovince(String fprovince) {
		this.fprovince = fprovince;
	}

	public String getFcity() {
		return fcity;
	}

	public void setFcity(String fcity) {
		this.fcity = fcity;
	}

	@Override
	public String toString() {
		return "ChildOrderParamBO [fchildorderid=" + fchildorderid + ", deliveryStartTime=" + deliveryStartTime
				+ ", deliveryEndTime=" + deliveryEndTime + ", fmaterialname=" + fmaterialname + ", fboxheight="
				+ fboxheight + ", fboxlength=" + fboxlength + ", fboxwidth=" + fboxwidth + ", fexcpstatus="
				+ fexcpstatus + ", fgroupgoodname=" + fgroupgoodname + ", fstatus=" + fstatus + ", orderEndTime="
				+ orderEndTime + ", orderStartTime=" + orderStartTime + ", payStartTime=" + payStartTime
				+ ", payEndTime=" + payEndTime + ", fmanufacturer=" + fmanufacturer + ", fkeyarea=" + fkeyarea
				+ ", fmateriallength=" + fmateriallength + ", fmaterialwidth=" + fmaterialwidth + ", manufacturerName="
				+ manufacturerName + ", fprovince=" + fprovince + ", fcity=" + fcity + "]";
	}
	
}
