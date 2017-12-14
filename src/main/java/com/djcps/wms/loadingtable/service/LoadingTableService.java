package com.djcps.wms.loadingtable.service;

import java.util.Map;

import com.djcps.wms.commons.base.BaseListParam;
import com.djcps.wms.loadingtable.model.AddLoadingTableBO;
import com.djcps.wms.loadingtable.model.DeleteLoadingTableBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByIdBO;
import com.djcps.wms.loadingtable.model.SelectLoadingTableByAttributeBO;
import com.djcps.wms.loadingtable.model.UpdateLoadingTableBO;
import com.djcps.wms.loadingtable.model.IsUseLoadingTableBO;


/**
 * 装车台业务层
 * @company:djwms
 * @author:zdx
 * @date:2017年11月30日
 */
public interface LoadingTableService {
	/**
	 * 装车台新增
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> add(AddLoadingTableBO loadingTable);
	
	/**
	 * 装车台修改
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> modify(UpdateLoadingTableBO loadingTable);
	
	/**
	 * 装车台删除
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> delete(DeleteLoadingTableBO loadingTable);
	
	/**
	 * 获取所有装车台信息
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getAllList(BaseListParam baseListParam);
	
	/**
	 * 根据仓库属性模糊查询
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getLoadingTableByAttribute(SelectLoadingTableByAttributeBO loadingTable);
	
	/**
	 * 根据仓库id查询
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getLoadingTableById(SelectLoadingTableByIdBO loadingTable);
	
	/**
	 * 启用装车台
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> enable(IsUseLoadingTableBO loadingTable);
	
	/**
	 * 禁用装车台
	 * @param loadingTable
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> disable(IsUseLoadingTableBO loadingTable);
	
}
