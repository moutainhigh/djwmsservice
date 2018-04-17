package com.djcps.wms.role.model.request;

import java.util.List;

/**
 * 角色信息
 * 
 * @author wyb
 *
 */
public class RoleInfoResultPO {
    /**
     * 信息统计数目
     */
    private Integer total;
    /**
     * 返回结果集
     */
    private List<RoleListPO> result;
    
    public Integer getTotal() {
        return total;
    }

    public List<RoleListPO> getResult() {
        return result;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setResult(List<RoleListPO> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RoleInfoResultPO [total=" + total + ", result=" + result + "]";
    }

}
