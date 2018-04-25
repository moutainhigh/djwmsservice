package com.djcps.wms.workrecords.model;

/**
 * 业绩
 * @author Chengw
 * @create 2018/4/24 11:33.
 * @since 1.0.0
 */
public class AchievementsBO {

    /**
     * 操作类型
     */
    private Integer operationType;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "AchievementsBO{" +
                "operationType='" + operationType + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
