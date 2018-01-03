package com.djcps.wms.warehouse.service;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.commons.model.GetCodeBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.warehouse.model.warehouse.*;

import java.util.Map;


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
	Map<String, Object> add(AddWarehouseBO addBean);
	
	/**
	 * 修改仓库
	 * @param updateBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> modify(UpdateWarehouseBO updateBean);
	
	/**
	 * 删除仓库
	 * @param deleteBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> delete(DeleteWarehouseBO deleteBean);
	
	/**
	 * 获取所有仓库信息
	 * @param baseListParam
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getAllList(BaseListBO baseListParam);
	
	/**
	 * 根据仓库id查询获取仓库
	 * @param selectByIdBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getWarehouseById(SelectWarehouseByIdBO selectByIdBean);
	
	/**
	 * 根据仓库属性模糊查询获取仓库
	 * @param selectVagueBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> getWarehouseByAttribute(SelectWarehouseByAttributeBO selectVagueBean);
	
	/**
	 * 启用仓库
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> enable(IsUseWarehouseBO isUseBean);
	
	/**
	 * 禁用仓库
	 * @param isUseBean
	 * @return
	 * @throws Exception
	 * @author:zdx
	 * @date:2017年11月30日
	 */
	Map<String, Object> disable(IsUseWarehouseBO isUseBean);

	
	/**
	 * 获取仓库类型
	 * @param partnerId
	 * @return
	 * @author:zdx
	 * @date:2017年12月8日
	 */
	Map<String, Object> getWarehouseType(String partnerId);

	/**
	 * 获取所有的仓库名称
	 * @param partnerInfoBean
	 * @return
	 * @author:zdx
	 * @date:2018年1月3日
	 */
	Map<String, Object> getAllWarehouseName(PartnerInfoBO partnerInfoBean);


	/**
	 * 获取仓库编码
	 * @author  wzy
	 * @param getCodeBO
	 * @return
	 * @create  2017/12/22 13:33
	 **/
	Map<String,Object> getWarehouseCode(GetCodeBO getCodeBO);

}
