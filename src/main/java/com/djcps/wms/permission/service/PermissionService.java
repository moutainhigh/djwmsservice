package com.djcps.wms.permission.service;

import java.util.Map;

import com.djcps.wms.permission.model.BaseOrgBO;
import com.djcps.wms.permission.model.DeletePermissionBO;
import com.djcps.wms.permission.model.GetPermissionBO;
import com.djcps.wms.permission.model.GetPermissionChooseBO;
import com.djcps.wms.permission.model.InsertOrUpdatePermissionBO;



/**
 * @author zhq
 *
 * 2018年4月12日
 */
public interface PermissionService {
	
	/**
	 * 获取组合权限列表
	 * @return
	 */
	Map<String, Object> getPermissionList(GetPermissionBO param);
	
	/**
	 * 获取WMS权限
	 * @return
	 */
	Map<String, Object> getWmsPermission(BaseOrgBO param);
	
	/**
	 * 新增/修改组合权限
	 * @return
	 */
	Map<String, Object> insertOrUpdatePermission(InsertOrUpdatePermissionBO param);
	
	/**
	 * 删除组合权限
	 * @return
	 */
	Map<String, Object> deletePermission(DeletePermissionBO param,String partnerId);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @return
	 */
	Map<String, Object> getPerChoose(GetPermissionChooseBO param);
}
