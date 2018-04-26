package com.djcps.wms.workrecords.model;

/**
 * 业绩
 * @author Chengw
 * @create 2018/4/24 11:33.
 * @since 1.0.0
 */
public class WorkRecordsOrderBO{

    /**
     * 操作id
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WorkRecordsOrderBO{" +
                "id='" + id + '\'' +
                '}';
    }
}
