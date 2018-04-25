package com.djcps.wms.permission.service;

import java.util.List;
import java.util.Map;

import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.permission.model.bo.DeletePerParamBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.PermissionBO;
import com.djcps.wms.permission.model.bo.PermissionChooseBO;
import com.djcps.wms.permission.model.bo.UpdatePermissionBO;
import com.djcps.wms.permission.model.bo.UserPermissionBO;
import com.djcps.wms.permission.model.vo.UserPermissionVO;



/**
 * @author zhq
 *
 * 2018年4月12日
 */
public interface PermissionService {
	
	/**
	 * 获取组合权限列表
	 * @param param
	 * @return Map
	 */
	Map<String, Object> getPermissionList(PermissionBO param);
	
	/**
	 * 获取WMS权限
	 * @param param
	 * @return Map
	 */
	Map<String, Object> getWmsPermission(GetWmsPermissionBO param);
	
	/**
	 * 新增组合权限
	 * @param param,@param partner
	 * @return Map
	 */
	Map<String, Object> insertPermission(UpdatePermissionBO param);
	
	/**
	 * 删除组合权限
	 * @param param
	 * @return Map
	 */
	Map<String, Object> deletePermission(DeletePerParamBO param);
	
	/**
	 * 根据组合权限id和公司id，获取获取组合权限集合
	 * @param param
	 * @return Map
	 */
	Map<String, Object> getPerChoose(PermissionChooseBO param);
	
	/**
	 * 更新组合权限
	 * @param param,@param partnerInfoBO
	 * @return Map
	 */
	Map<String, Object> updatePermission(UpdatePermissionBO param);
	
	/**
	 * 获取用户所有权限 
	 * @autuor Chengw
	 * @since 2018/4/23  20:33
	 * @param param
	 * @return
	 */
	Map<String, Object> getUserPermission(UserPermissionBO param);

	/**
	 * 获取用户所有权限 list 
	 * @autuor Chengw
	 * @since 2018/4/23  20:34
	 * @param param
	 * @return
	 */
	List<UserPermissionVO> listUserPermission(UserPermissionBO param);

	/**
	 * 校验是否不存在系统权限项
	 * @param userId
	 * @param url
	 * @return
	 */
	Boolean notExistSystemPermission(String userId,String url);

	/**
	 * 删除redis 权限缓存
	 * @param userId
	 */
	void delUserRedisPermission(String userId);
}
