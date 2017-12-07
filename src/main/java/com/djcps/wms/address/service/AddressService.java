package com.djcps.wms.address.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;

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
	Map<String, Object> getProvinceAllList(ProvinceCityAreaCodeBo param);
	
	/**
	 * 根据省份获取所有的城市
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	Map<String, Object> getCityListByProvince(ProvinceCityAreaCodeBo param);
	
	
	/**
	 * 根据城市获取所有的区
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月4日
	 */
	Map<String, Object> getAreaListByCity(ProvinceCityAreaCodeBo param);

	/**
	 * 根据区获取所有的街道
	 * @description:
	 * @param param
	 * @return
	 * @author:zdx
	 * @date:2017年12月6日
	 */
	Map<String, Object> getStreeListByArea(ProvinceCityAreaCodeBo param);
}
