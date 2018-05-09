package com.djcps.wms.stocktaking.model;

import java.util.List;

import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;

/**
 * @title:列表保存盘点结果
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/16
 **/
public class SaveStocktakingOrderInfoList extends BaseAddBO {

    /**
     * 
     */
    private static final long serialVersionUID = -4708078292105312554L;

    private String jobId;

    private String warehouseId;

    /**
     * 盘点员名
     */
    private String inventoryClerk;

    /**
     * 盘点员id
     */
    private String inventoryClerkId;
    /**
     * 盘点订单信息集合
     */
    private List<SaveStocktakingOrderInfoBO> saveStocktaking;
    /**
     * 操作记录实体类
     */
    private TaskOperationRecordPO taskOperationRecordPO;
    /**
     * 盘点订单对应操作记录数据
     */
    private List<OrderOperationRecordPO> orderOperationInfo;

    public List<OrderOperationRecordPO> getOrderOperationInfo() {
        return orderOperationInfo;
    }

    public void setOrderOperationInfo(List<OrderOperationRecordPO> orderOperationInfo) {
        this.orderOperationInfo = orderOperationInfo;
    }

    public TaskOperationRecordPO getTaskOperationRecordPO() {
        return taskOperationRecordPO;
    }

    public void setTaskOperationRecordPO(TaskOperationRecordPO taskOperationRecordPO) {
        this.taskOperationRecordPO = taskOperationRecordPO;
    }

    public List<SaveStocktakingOrderInfoBO> getSaveStocktaking() {
        return saveStocktaking;
    }

    public void setSaveStocktaking(List<SaveStocktakingOrderInfoBO> saveStocktaking) {
        this.saveStocktaking = saveStocktaking;
    }

    public String getJobId() {
        return jobId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public String getInventoryClerk() {
        return inventoryClerk;
    }

    public String getInventoryClerkId() {
        return inventoryClerkId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setInventoryClerk(String inventoryClerk) {
        this.inventoryClerk = inventoryClerk;
    }

    public void setInventoryClerkId(String inventoryClerkId) {
        this.inventoryClerkId = inventoryClerkId;
    }

    @Override
    public String toString() {
        return "SaveStocktakingOrderInfoList [jobId=" + jobId + ", warehouseId=" + warehouseId + ", inventoryClerk="
                + inventoryClerk + ", inventoryClerkId=" + inventoryClerkId + ", saveStocktaking=" + saveStocktaking
                + ", taskOperationRecordPO=" + taskOperationRecordPO + ", orderOperationInfo=" + orderOperationInfo
                + "]";
    }

}
