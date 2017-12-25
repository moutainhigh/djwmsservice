package com.djcps.wms.address.service;

import java.util.Map;

import com.djcps.wms.address.model.ProvinceCityAreaCodeBO;

/**
 * 地址业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface AddressService {

	/**
	 * 获取所有的省
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	Map<String, Object> getProvinceAllList(ProvinceCityAreaCodeBO param);
	
	/**
	 * 根据省份获取所有的城市
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	Map<String, Object> getCityListByProvince(ProvinceCityAreaCodeBO param);
	
	
	/**
	 * 根据城市获取所有的区
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	Map<String, Object> getAreaListByCity(ProvinceCityAreaCodeBO param);

	/**
	 * 根据区获取所有的街道
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月6日
	 */
	Map<String, Object> getStreeListByArea(ProvinceCityAreaCodeBO param);
}
