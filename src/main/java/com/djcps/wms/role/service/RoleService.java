package com.djcps.wms.role.service;

import java.util.Map;

import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
/**
 * @title:业务层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/12
 **/
public interface RoleService {
    /**
     * 获取角色列表
     * @author  wyb
     * @param roleListBO
     * @return
     * @create  2018/4/12
     **/
    Map<String,Object> roleList(RoleListBO roleListBO);
    /**
     * 更新角色信息
     * @param UpdateRoleInfoBO
     * @return
     * @create  2018/4/12
     */
    Map<String,Object> update(UpdateRoleInfoBO UpdateRoleInfoBO);
    /**
     * 获取角色信息
     * @param roleListBO
     * @return
     */
    Map<String, Object> roleInfo(RoleListBO roleListBO);
    /**
     * 删除角色信息
     * @param deleteBO
     * @return
     */
    Map<String, Object> delete(DeleteBO deleteBO);
    /**
     * 新增角色信息
     * @param saveBO
     * @return
     */
    Map<String, Object> save(SaveBO saveBO);
}
