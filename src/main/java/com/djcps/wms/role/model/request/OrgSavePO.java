package com.djcps.wms.role.model.request;

/**
 * 调用ORG保存时返回参数
 * 
 * @author WYB
 * @since 2018/4/12
 */
public class OrgSavePO {
    /**
     * 返回保存时的角色编号
     */
    private String insertID;

    public String getInsertID() {
        return insertID;
    }

    public void setInsertID(String insertID) {
        this.insertID = insertID;
    }

    @Override
    public String toString() {
        return "OrgSavePO [insertID=" + insertID + "]";
    }

}
