package com.djcps.wms.provider.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.ProvinceCityAreaCodeBo;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;

/**
 * 供应商业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface ProviderService {
	/**
	 * 新增供应商
	 * @param addBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> add(AddProviderBO addBean) throws Exception;
	
	/**
	 * 修改供应商
	 * @param updateBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> modify(UpdateProviderVO updateBean) throws Exception;
	
	/**
	 * 删除供应商
	 * @param deleteBean
	 * @return 
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> delete(DeleteProviderBO deleteBean) throws Exception;
	
	/**
	 * 获取所有供应商
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception;
	
	/**
	 * 根据供应商属性模糊查询
	 * @param selectVagueBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean) throws Exception;

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
}
