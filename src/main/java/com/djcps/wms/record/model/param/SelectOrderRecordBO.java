package com.djcps.wms.record.model.param;

import java.io.Serializable;
import java.util.Arrays;

import com.djcps.wms.commons.base.BaseListBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;

public class SelectOrderRecordBO extends BaseListBO implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 8915612973199348741L;

    String batchBy;
    
    OrderOperationRecordPO[] record;

    public String getBatchBy() {
        return batchBy;
    }

    public void setBatchBy(String batchBy) {
        this.batchBy = batchBy;
    }

    public OrderOperationRecordPO[] getRecord() {
        return record;
    }

    public void setRecord(OrderOperationRecordPO[] record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "selectOrderRecordBO [batchBy=" + batchBy + ", record=" + Arrays.toString(record) + "]";
    }
    
}
