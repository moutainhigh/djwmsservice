package com.djcps.wms.inneruser.model.userparam;

import java.util.List;

/**
 * 用户角色类型和所有角色类型返回类
 * @author:wzy
 * @date:2018/4/18
 **/
public class UserRoleListBO {
    /**
     * 当前用户角色类型
     */
   private List<GetRoleTypePO> personRoleList;

    /**
     * 所有角色类型
     */
   private List<GetRoleTypePO> allRoleList;

    public List<GetRoleTypePO> getPersonRoleList() {
        return personRoleList;
    }

    public void setPersonRoleList(List<GetRoleTypePO> personRoleList) {
        this.personRoleList = personRoleList;
    }

    public List<GetRoleTypePO> getAllRoleList() {
        return allRoleList;
    }

    public void setAllRoleList(List<GetRoleTypePO> allRoleList) {
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
