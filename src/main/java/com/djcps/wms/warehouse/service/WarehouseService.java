package com.djcps.wms.warehouse.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.warehouse.model.AddWarehouseBO;
import com.djcps.wms.warehouse.model.DeleteWarehouseBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByIdBO;
import com.djcps.wms.warehouse.model.SelectWarehouseByAttributeBO;
import com.djcps.wms.warehouse.model.UpdateWarehouseBO;
import com.djcps.wms.warehouse.model.IsUseWarehouseBO;


/**
 * 仓库业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface WarehouseService {
	/**
	 * 新增仓库
	 * @param addBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> add(AddWarehouseBO addBean) throws Exception;
	
	/**
	 * 修改仓库
	 * @param updateBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> modify(UpdateWarehouseBO updateBean) throws Exception;
	
	/**
	 * 删除仓库
	 * @param deleteBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> delete(DeleteWarehouseBO deleteBean) throws Exception;
	
	/**
	 * 获取所有仓库信息
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getAllList(BaseListParam baseListParam) throws Exception;
	
	/**
	 * 根据仓库id查询获取仓库
	 * @param selectByIdBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getWarehouseById(SelectWarehouseByIdBO selectByIdBean) throws Exception;
	
	/**
	 * 根据仓库属性模糊查询获取仓库
	 * @param selectVagueBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean) throws Exception;
	
	/**
	 * 启用仓库
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> enable(IsUseWarehouseBO isUseBean) throws Exception;
	
	/**
	 * 禁用仓库
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> disable(IsUseWarehouseBO isUseBean) throws Exception;
}
