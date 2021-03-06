package com.djcps.wms.role.model.request;

import java.util.List;

/**
 * 角色信息
 * 
 * @author wyb
 *
 */
public class RoleInfoResultVO {
    /**
     * 信息统计数目
     */
    private Integer total;
    /**
     * 返回结果集
     */
    private List<WmsRoleInfoPO> result;

    public Integer getTotal() {
        return total;
    }

    public List<WmsRoleInfoPO> getResult() {
        return result;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setResult(List<WmsRoleInfoPO> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RoleInfoResultVO [total=" + total + ", result=" + result + "]";
    }

}
