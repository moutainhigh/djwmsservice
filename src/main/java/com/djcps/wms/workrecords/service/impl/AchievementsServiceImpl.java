package com.djcps.wms.workrecords.service.impl;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.DateUtils;
import com.djcps.wms.workrecords.model.AchievementsBO;
import com.djcps.wms.workrecords.model.AchievementsInfoBO;
import com.djcps.wms.workrecords.server.AchievementsServer;
import com.djcps.wms.workrecords.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 绩效
 * @author Chengw
 * @create 2018/4/24 18:52.
 * @since 1.0.0
 */
@Service
public class AchievementsServiceImpl implements AchievementsService {

    @Autowired
    private AchievementsServer achievementsServer;

    @Override
    public Map<String, Object> statistics(AchievementsBO param) {
        if(!ObjectUtils.isEmpty(param.getYear()) && !ObjectUtils.isEmpty(param.getYear())){
            param.setStartTime(DateUtils.getStartMonth(param.getYear(),param.getMonth()));
            param.setEndTime(DateUtils.getEndMonth(param.getYear(),param.getMonth()));
        }
        HttpResult result = achievementsServer.listAllAchievements(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    @Override
    public Map<String, Object> deliveryInfo(AchievementsInfoBO param) {
        if(!ObjectUtils.isEmpty(param.getYear()) && !ObjectUtils.isEmpty(param.getYear())){
            param.setStartTime(DateUtils.getStartMonth(param.getYear(),param.getMonth()));
            param.setEndTime(DateUtils.getEndMonth(param.getYear(),param.getMonth()));
        }
        HttpResult result = achievementsServer.listPdaDeliveryAchievementsInfo(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    @Override
    public Map<String, Object> entryInfo(AchievementsInfoBO param) {
        if(!ObjectUtils.isEmpty(param.getYear()) && !ObjectUtils.isEmpty(param.getYear())){
            param.setStartTime(DateUtils.getStartMonth(param.getYear(),param.getMonth()));
            param.setEndTime(DateUtils.getEndMonth(param.getYear(),param.getMonth()));
        }
        HttpResult result = achievementsServer.listPdaAchievementsInfo(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

}
