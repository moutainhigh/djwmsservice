package com.djcps.wms.inneruser.model.param;

import com.djcps.wms.role.model.request.WmsRoleInfoPO;

import java.util.List;

/**
 * 用户角色类型和所有角色类型返回类
 * @author wzy
 * @date 2018/4/18
 **/
public class UserRoleListBO {
    /**
     * 当前用户角色类型
     */
   private List<WmsRoleInfoPO> personRoleList;

    /**
     * 所有角色类型
     */
   private List<WmsRoleInfoPO> allRoleList;

    public List<WmsRoleInfoPO> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<WmsRoleInfoPO> personRoleList) {
        this.personRoleList = personRoleList;
    }

    public List<WmsRoleInfoPO> getAllRoleList() {
        return allRoleList;
    }

    public void setAllRoleList(List<WmsRoleInfoPO> allRoleList) {
        this.allRoleList = allRoleList;
    }

    @Override
    public String toString() {
        return "UserRoleListBO{" +
                "personRoleList=" + personRoleList +
                ", allRoleList=" + allRoleList +
                '}';
    }
}
