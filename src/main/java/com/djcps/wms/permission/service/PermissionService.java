package com.djcps.wms.permission.service;

import java.util.Map;

import com.djcps.wms.permission.model.bo.BaseOrgBO;
import com.djcps.wms.permission.model.bo.DeletePermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionChooseBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.InsertOrUpdatePermissionBO;



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
	Map<String, Object> getWmsPermission(GetWmsPermissionBO param);
	
	/**
	 * 新增组合权限
	 * @return
	 */
	Map<String, Object> insertPermission(InsertOrUpdatePermissionBO param);
	
	/**
	 * 删除组合权限
	 * @return
	 */
	Map<String, Object> deletePermission(DeletePermissionBO param);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @return
	 */
	Map<String, Object> getPerChoose(GetPermissionChooseBO param);
	
	/**
	 * 更新组合权限
	 * @return
	 */
	Map<String, Object> updatePermission(InsertOrUpdatePermissionBO param);
}
