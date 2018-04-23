package com.djcps.wms.permission.model.bo;

/**
 * @author zhq
 * 权限包实体类
 * 2018年4月23日
 */
public class PermissionBO {
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 业务模块
	 */
	private String bussion;
	/**
	 * 公司id
	 */
	private String companyId;
	/**
	 * 当前页
	 */
	private String page;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 分页大小
	 */
	private String pageSize;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBussion() {
		return bussion;
	}
	public void setBussion(String bussion) {
		this.bussion = bussion;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "ChangePermissionBO [operator=" + operator + ", ip=" + ip + ", bussion=" + bussion + ", companyId="
				+ companyId + ", page=" + page + ", keyWord=" + keyWord + ", pageSize=" + pageSize + "]";
	}

}
