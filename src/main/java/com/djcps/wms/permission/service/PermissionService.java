package com.djcps.wms.permission.service;

import java.util.Map;

import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.permission.model.bo.BaseOrgBO;
import com.djcps.wms.permission.model.bo.DeletePermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionBO;
import com.djcps.wms.permission.model.bo.GetPermissionChooseBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.InsertOrUpdatePermissionBO;
import com.djcps.wms.permission.model.bo.PermissionBO;
import com.djcps.wms.permission.model.bo.PermissionChooseBO;
import com.djcps.wms.permission.model.bo.UpdatePermissionBO;



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
	Map<String, Object> getPermissionList(PermissionBO param);
	
	/**
	 * 获取WMS权限
	 * @return
	 */
	Map<String, Object> getWmsPermission(GetWmsPermissionBO param);
	
	/**
	 * 新增组合权限
	 * @return
	 */
	Map<String, Object> insertPermission(UpdatePermissionBO param, PartnerInfoBO partner);
	
	/**
	 * 删除组合权限
	 * @return
	 */
	Map<String, Object> deletePermission(DeletePermissionBO param);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @return
	 */
	Map<String, Object> getPerChoose(PermissionChooseBO param);
	
	/**
	 * 更新组合权限
	 * @return
	 */
	Map<String, Object> updatePermission(UpdatePermissionBO param, PartnerInfoBO partnerInfoBO);
}
