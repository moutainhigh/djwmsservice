package com.djcps.wms.provider.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.base.BaseListPartnerIdBO;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderBO;

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
	Map<String, Object> add(AddProviderBO addBean);
	
	/**
	 * 修改供应商
	 * @param updateBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> modify(UpdateProviderBO updateBean);
	
	/**
	 * 删除供应商
	 * @param deleteBean
	 * @return 
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> delete(DeleteProviderBO deleteBean);
	
	/**
	 * 获取所有供应商
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getAllList(BaseListPartnerIdBO baseListParam);
	
	/**
	 * 根据供应商属性模糊查询
	 * @param selectVagueBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean);

}
