package com.djcps.wms.record.model.param;

import java.io.Serializable;
import java.util.Arrays;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.record.model.TaskOperationRecordPO;

public class SelectTaskRecordBO extends BaseListBO implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8915612973199348741L;

    String batchBy;
    
    TaskOperationRecordPO[] record;

    public String getBatchBy() {
        return batchBy;
    }

    public void setBatchBy(String batchBy) {
        this.batchBy = batchBy;
    }

    public TaskOperationRecordPO[] getRecord() {
        return record;
    }

    public void setRecord(TaskOperationRecordPO[] record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "selectTaskRecordBO [batchBy=" + batchBy + ", record=" + Arrays.toString(record) + "]";
    }
    
}
