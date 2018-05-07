package com.djcps.wms.record.service;

import com.djcps.wms.record.model.param.EntryRecordListBO;
import com.djcps.wms.record.model.param.SaveOperationRecordBO;
import com.djcps.wms.record.model.param.StocktakingRecordListBO;

import java.util.Map;

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
}
