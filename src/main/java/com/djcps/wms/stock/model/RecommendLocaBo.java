package com.djcps.wms.stock.model;

import java.io.Serializable;
import java.util.List;

import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取库区推荐对象
 * @company:djwms
 * @author:zdx
 * @date:2017年12月20日
 */
public class RecommendLocaBo extends BaseBO implements Serializable{

	private static final long serialVersionUID = -7237160364347242371L;
	
	/**
	 * 经纬度
	 */
	@NotBlank
	private String location;
	
	/**
	 * 合作方id
	 */
	@NotBlank
	private String partnerId;
	
	private List<RecommendLocaParamBo> param;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public List<RecommendLocaParamBo> getParam() {
		return param;
	}

	public void setParam(List<RecommendLocaParamBo> param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "RecommendLocaBo [location=" + location + ", partnerId=" + partnerId + ", param=" + param + "]";
	}
	
}
