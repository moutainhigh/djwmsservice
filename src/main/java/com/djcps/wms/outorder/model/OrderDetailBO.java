package com.djcps.wms.outorder.model;
/**
 * 获取订单服务数据类
 * @author ldh
 *
 */
public class OrderDetailBO {
	/**
	 * 订单编号
	 */
	private String fchildorderid;
	/**
	 * 产品名称
	 */
	private String fgroupgoodname;
	/**
	 * 纸箱规格长
	 */
	private Double fboxlength;
	/**
	 * 纸箱规格宽
	 */
	private Double fboxwidth;
	/**
	 * 纸箱规格高
	 */
	private Double fboxheight;
	/**
	 * 下料规格长
	 */
	private Double fmateriallength;
	/**
	 * 下料规格宽
	 */
	private Double fmaterialwidth;
	/**
	 * 数量
	 */
	private Integer famount;
	/**
	 * 单价
	 */
	private Double funitprice;
	/**金额
	 * 
	 */
	private Double famountprice;
	public String getFchildorderid() {
		return fchildorderid;
	}
	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}
	public String getFgroupgoodname() {
		return fgroupgoodname;
	}
	public void setFgroupgoodname(String fgroupgoodname) {
		this.fgroupgoodname = fgroupgoodname;
	}
	public Double getFboxlength() {
		return fboxlength;
	}
	public void setFboxlength(Double fboxlength) {
		this.fboxlength = fboxlength;
	}
	public Double getFboxwidth() {
		return fboxwidth;
	}
	public void setFboxwidth(Double fboxwidth) {
		this.fboxwidth = fboxwidth;
	}
	public Double getFboxheight() {
		return fboxheight;
	}
	public void setFboxheight(Double fboxheight) {
		this.fboxheight = fboxheight;
	}
	public Double getFmateriallength() {
		return fmateriallength;
	}
	public void setFmateriallength(Double fmateriallength) {
		this.fmateriallength = fmateriallength;
	}
	public Double getFmaterialwidth() {
		return fmaterialwidth;
	}
	public void setFmaterialwidth(Double fmaterialwidth) {
		this.fmaterialwidth = fmaterialwidth;
	}
	public Integer getFamount() {
		return famount;
	}
	public void setFamount(Integer famount) {
		this.famount = famount;
	}
	public Double getFunitprice() {
		return funitprice;
	}
	public void setFunitprice(Double funitprice) {
		this.funitprice = funitprice;
	}
	public Double getFamountprice() {
		return famountprice;
	}
	public void setFamountprice(Double famountprice) {
		this.famountprice = famountprice;
	}
	@Override
	public String toString() {
		return "OrderDetailBO [fchildorderid=" + fchildorderid + ", fgroupgoodname=" + fgroupgoodname + ", fboxlength="
				+ fboxlength + ", fboxwidth=" + fboxwidth + ", fboxheight=" + fboxheight + ", fmateriallength="
				+ fmateriallength + ", fmaterialwidth=" + fmaterialwidth + ", famount=" + famount + ", funitprice="
				+ funitprice + ", famountprice=" + famountprice + "]";
	}
	
	
}
