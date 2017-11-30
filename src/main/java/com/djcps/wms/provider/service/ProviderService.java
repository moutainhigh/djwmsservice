package com.djcps.wms.provider.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.provider.model.AddProviderBO;
import com.djcps.wms.provider.model.DeleteProviderBO;
import com.djcps.wms.provider.model.SelectProviderByAttributeBO;
import com.djcps.wms.provider.model.UpdateProviderVO;

public interface ProviderService {
	Map<String, Object> add(AddProviderBO addBean) throws Exception;
	
	Map<String, Object> modify(UpdateProviderVO updateBean) throws Exception;
	
	Map<String, Object> delete(DeleteProviderBO deleteBean) throws Exception;
	
	Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception;
	
	Map<String, Object> getProviderByAttribute(SelectProviderByAttributeBO selectVagueBean) throws Exception;
}
