package com.djcps.wms.outorder.model.outorderresult;
/**
 * 返回页面的参数
 * @author xzzx
 *
 */
public class OrderDetailInfoBO {
	/**
	 * 订单编号
	 */
	private String fchildorderid;
	/**
	 * 产品名称
	 */
	private String fgroupgoodname;
	/**
	 * 下料规格
	 */
	private String fmaterial;
	/**
	 * 纸箱规格
	 */
	private String fbox;
	/**
	 * 数量
	 */
	private Integer famount;
	/**
	 * 单价
	 */
	private Double funitprice;
	/**
	 * 金额
	 */
	private Double famountprice; 
	/**
	 * 单位
	 */
	private String unit;
	
	public OrderDetailInfoBO(){
		this.unit = "片";
	}

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

	public String getFmaterial() {
		return fmaterial;
	}

	public void setFmaterial(String fmaterial) {
		this.fmaterial = fmaterial;
	}

	public String getFbox() {
		return fbox;
	}

	public void setFbox(String fbox) {
		this.fbox = fbox;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "OrderDetailInfoBO [fchildorderid=" + fchildorderid + ", fgroupgoodname=" + fgroupgoodname
				+ ", fmaterial=" + fmaterial + ", fbox=" + fbox + ", famount=" + famount + ", funitprice=" + funitprice
				+ ", famountprice=" + famountprice + ", unit=" + unit + "]";
	}
	
	
}
