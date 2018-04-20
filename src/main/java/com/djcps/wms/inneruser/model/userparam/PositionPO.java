package com.djcps.wms.inneruser.model.userparam;

public class PositionPO {

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
        return "PositionPO{" +
                "positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
