package com.djcps.wms.inneruser.model.param;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 10:03.
 */
public class UserInfoBO implements Serializable{

    /**
     * 类型代码
     */
    private List<String> roleTypeCode;

    /**
     * 公司id
     */
    private String partnerId;

    /**
     * 工作状态
     */
    private Integer workStatus;

    public List<String> getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(List<String> roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public String toString() {
        return "UserInfoBO{" +
                "roleTypeCode=" + roleTypeCode +
                ", partnerId='" + partnerId + '\'' +
                ", workStatus=" + workStatus +
                '}';
    }
}
