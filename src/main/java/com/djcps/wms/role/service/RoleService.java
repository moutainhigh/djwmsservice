package com.djcps.wms.role.service;

import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveRoleBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;

import java.util.Map;
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
     * @param updateRoleInfoBO
     * @return
     * @create  2018/4/12
     */
    Map<String,Object> update(UpdateRoleInfoBO updateRoleInfoBO);
    /**
     * 删除角色信息
     * @param deleteBO
     * @return
     */
    Map<String, Object> delete(DeleteBO deleteBO);
    /**
     * 新增角色信息
     * @param saveRoleBO
     * @return
     */
    Map<String, Object> save(SaveRoleBO saveRoleBO);
    /**
     * 获取角色类型信息列表
     * @param param
     * @return
     */
    Map<String, Object> getRoleType(BaseBO param);
}
