package com.djcps.wms.loadingtable.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;


public interface LoadingTableService {
	Map<String, Object> add(AddLoadingTableBO loadingTable) throws Exception;
	
	Map<String, Object> modify(UpdateLoadingTableBO loadingTable) throws Exception;
	
	Map<String, Object> delete(DeleteLoadingTableBO loadingTable) throws Exception;
	
	Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception;
	
	Map<String, Object> getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable) throws Exception;
	
	Map<String, Object> getLoadingTableById(SelectLoadingTableByIdBO loadingTable) throws Exception;
	
	Map<String, Object> enable(IsUseLoadingTableBO loadingTable) throws Exception;
	
	Map<String, Object> disable(IsUseLoadingTableBO loadingTable) throws Exception;
	
}
