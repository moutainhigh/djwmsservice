package com.djcps.wms.warehouse.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.warehouse.model.AddWarehouseBO;
import com.djcps.wms.warehouse.model.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.UpdateWarehouseBO;
import com.djcps.wms.warehouse.model.IsUseWarehouseBO;


public interface WarehouseService {
	Map<String, Object> add(AddWarehouseBO addBean) throws Exception;
	
	Map<String, Object> modify(UpdateWarehouseBO updateBean) throws Exception;
	
	Map<String, Object> delete(DeleteWarehouseBO deleteBean) throws Exception;
	
	Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception;
	
	Map<String, Object> getWarehouseById(SelectWarehouseByIdBO selectByIdBean) throws Exception;
	
	Map<String, Object> getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean) throws Exception;
	
	Map<String, Object> enable(IsUseWarehouseBO isUseBean) throws Exception;
	
	Map<String, Object> disable(IsUseWarehouseBO isUseBean) throws Exception;
}
