package com.djcps.wms.record.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.wms.commons.enums.FluteTypeEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.ChildOrderBO;
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
import com.djcps.wms.record.model.result.OperationRecordResult;
import com.djcps.wms.record.server.OperationRecordServer;
import com.djcps.wms.record.service.OperationRecordService;
import com.djcps.wms.stocktaking.model.orderresult.InnerDate;

/**
 * 操作记录实现类
 * 
 * @title:
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/6
 **/
@Service
public class OperationRecordServiceImpl implements OperationRecordService {
    @Autowired
    private OperationRecordServer operationRecordServer;

    /**
     * 获取盘点操作记录
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/6
     **/

    @Override
    public Map<String, Object> stocktakingRecordList(StocktakingRecordListBO stocktakingRecordListBO) {
        OperationRecordResult operationRecordResult = operationRecordServer
                .stocktakingRecordList(stocktakingRecordListBO);
        if (ObjectUtils.isEmpty(operationRecordResult.getData())) {
            return MsgTemplate.successMsg();
        }
        // 因为这里返回的参数比较特殊所以需要重新自己组织对象,不调用方法
        InnerDate innerDate = new InnerDate();
        innerDate.setTotal(operationRecordResult.getData().getTotal());
        innerDate.setResult(operationRecordResult.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

    /**
     * 获取入库移库操作记录
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/6
     **/
    @Override
    public Map<String, Object> getEntryRecordList(EntryRecordListBO fromJson) {
        HttpResult result = operationRecordServer.getEntryRecordList(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 保存操作记录
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/6
     **/
    @Override
    public Map<String, Object> saveOperationRecord(SaveOperationRecordBO fromJson) {
        HttpResult result = operationRecordServer.saveOperationRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:根据关联id获取操作记录信息
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> getRecordByRrelativeId(RelativeIdBO fromJson) {
        HttpResult result = operationRecordServer.getRecordByRrelativeId(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> selectTaskRecord(SelectTaskRecordBO fromJson) {
        HttpResult result = operationRecordServer.selectTaskRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> saveTaskRecord(TaskOperationRecordPO fromJson) {
        HttpResult result = operationRecordServer.saveTaskRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> deleteTaskRecord(DeleteTaskRecordBO fromJson) {
        HttpResult result = operationRecordServer.deleteTaskRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:查询任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> selectOrderRecord(SelectOrderRecordBO fromJson) {
        HttpResult result = operationRecordServer.selectOrderRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

    /**
     * @title:保存任务 操作记录
     * @description:
     * @param fromJson
     * @return
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> saveOrderRecord(OrderOperationRecordPO fromJson) {
        HttpResult result = operationRecordServer.saveOrderRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }
   
    /**
     * @title:删除任务 操作记录
     * @description:
     * @param fromJson
     * @return 
     * @author:wyb
     * @date: 2018/5/3
     */
    @Override
    public Map<String, Object> deleteOrderRecord(DeleteOrderRecordBO fromJson) {
        HttpResult result = operationRecordServer.deleteOrderRecord(fromJson);
        return MsgTemplate.customMsg(result);
    }

}
