package com.djcps.wms.workrecords.service.impl;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.workrecords.model.WorkRecordsBO;
import com.djcps.wms.workrecords.model.WorkRecordsDetailBO;
import com.djcps.wms.workrecords.server.WorkRecordServer;
import com.djcps.wms.workrecords.service.WorkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @author panyang
 * @version 创建时间：2018年4月17日 下午3:49:26 工作记录实现类
 */
@Service
public class WorkRecordServiceImpl implements WorkRecordService {

    @Autowired
    private WorkRecordServer workRecordServer;

    /**
     * 根据操作类类型获取相关工作记录信息
     *
     * @author py
     * @create :2018/4/18
     */
    @Override
    public Map<String, Object> getAllRecordListByOperationType(WorkRecordsBO param) {
        HttpResult result = workRecordServer.getAllRecordListByOperationType(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 根据id,楞型获取相关工作记录详情
     *
     * @author py
     * @create :2018/4/18
     */
    @Override
    public Map<String, Object> getWorkRecordsDetail(WorkRecordsDetailBO param) {
        HttpResult result = workRecordServer.getWorkRecordsDetail(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }


    /**
     * 获取提货的工作记录相关信息
     *
     * @param param
     * @author py
     * @create :2018/4/18
     * @autuor Chengw
     * @update 2018/4/23  13:09
     */

    @Override
    public Map<String, Object> listDeliveryRecord(WorkRecordsBO param) {
        HttpResult result = workRecordServer.listDeliveryRecord(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    /**
     * 工作记录详情
     *
     * @param param
     * @author py
     * @create :2018/4/18
     * @autuor Chengw
     * @update 2018/4/23  13:09
     */

    @Override
    public Map<String, Object> getDeliveryWorkRecordsDetail(WorkRecordsDetailBO param) {
        HttpResult result = workRecordServer.getDeliveryWorkRecordsDetail(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }
}
