package com.djcps.wms.inneruser.model.result;

/**
 * org获取职位对应PO
 * @author wzy
 * @date 2018/4/20
 **/
public class PositionVO {

    /**
     * 职位id
     */
    private String positionId;

    /**
     * 职位名称
     */
    private String positionName;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "PositionVO{" +
                "positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
