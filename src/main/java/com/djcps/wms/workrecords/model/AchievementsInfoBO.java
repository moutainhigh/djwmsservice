package com.djcps.wms.workrecords.model;

import com.djcps.wms.commons.base.BaseListBO;

import javax.validation.constraints.NotNull;

/**
 * @author Chengw
 * @create 2018/4/24 17:19.
 * @since 1.0.0
 */
public class AchievementsInfoBO extends BaseListBO {

    /**
     * 操作类型
     */
    private Integer operationType;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 楞型
     */
    private Integer fluteType;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 年份
     */
    @NotNull
    private Integer year;

    /**
     * 月份
     */
    @NotNull
    private Integer month;

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

    public Integer getFluteType() {
        return fluteType;
    }

    public void setFluteType(Integer fluteType) {
        this.fluteType = fluteType;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "AchievementsInfoBO{" +
                "operationType=" + operationType +
                ", operatorId='" + operatorId + '\'' +
                ", fluteType=" + fluteType +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
