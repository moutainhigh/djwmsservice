package com.djcps.wms.loadingtask.model;
/**
 * 从订单服务获取的客户信息
 * 
 * @author ldh
 *
 */
public class OrderInfoBO {
	/**
     * 子订单号
     */
    private String fchildorderid;
	/**
	 * 母账号名称
	 */
	private String fpusername;
	/**
	 * 子账号名称
	 */
	private String fcusername;
	/**
	 * 收件人
	 */
	private String fconsignee;
	/**
	 * 联系方式
	 */
	private String fcontactway;
	/**
	 * 省市区镇
	 */
	private String fcodeprovince;
	/**
	 * 详细地址
	 */
	private String faddressdetail;
	
	public String getFpusername() {
		return fpusername;
	}
	public void setFpusername(String fpusername) {
		this.fpusername = fpusername;
	}
	public String getFcusername() {
		return fcusername;
	}
	public void setFcusername(String fcusername) {
		this.fcusername = fcusername;
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
	public String getFchildorderid() {
		return fchildorderid;
	}
	public void setFchildorderid(String fchildorderid) {
		this.fchildorderid = fchildorderid;
	}
	@Override
	public String toString() {
		return "OrderInfoBO [fchildorderid=" + fchildorderid + ", fpusername=" + fpusername + ", fcusername="
				+ fcusername + ", fconsignee=" + fconsignee + ", fcontactway=" + fcontactway + ", fcodeprovince="
				+ fcodeprovince + ", faddressdetail=" + faddressdetail + "]";
	}
	
}
