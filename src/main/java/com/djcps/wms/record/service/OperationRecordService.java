package com.djcps.wms.record.service;

import java.util.Map;

import com.djcps.wms.record.model.OrderOperationRecordPO;
import com.djcps.wms.record.model.TaskOperationRecordPO;
import com.djcps.wms.record.model.param.DeleteOrderRecordBO;
import com.djcps.wms.record.model.param.DeleteTaskRecordBO;
import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.record.model.param.RelativeIdBO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
import com.djcps.wms.record.model.param.SelectOrderRecordBO;
import com.djcps.wms.record.model.param.SelectTaskRecordBO;
import com.djcps.wms.record.model.param.StocktakingRecordListBO;

/**
 * @title:操作记录业务层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
public interface OperationRecordService {
    /**
     * 获取盘点操作记录
     * @author  wyb
     * @param stocktakingRecordListBO
     * @return
     * @create  2018/3/6
     **/
    Map<String,Object> stocktakingRecordList(StocktakingRecordListBO stocktakingRecordListBO);
    /**
     * 获取操作记录
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/3/6
     */
    Map<String, Object> getEntryRecordList(EntryRecordListBO fromJson);
    /**
     * 保存操作记录
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/3/6
     */
    Map<String, Object> saveOperationRecord(SaveOperationRecordBO fromJson);
    /**
     * @title:根据关联id获取操作记录信息
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> getRecordByRrelativeId(RelativeIdBO fromJson);
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> selectTaskRecord(SelectTaskRecordBO fromJson);
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> saveTaskRecord(TaskOperationRecordPO fromJson);
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> deleteTaskRecord(DeleteTaskRecordBO fromJson);
    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> selectOrderRecord(SelectOrderRecordBO fromJson);
    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> saveOrderRecord(OrderOperationRecordPO fromJson);
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    Map<String, Object> deleteOrderRecord(DeleteOrderRecordBO fromJson);
    
}
