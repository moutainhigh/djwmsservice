package com.djcps.wms.workrecords.service.impl;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.record.enums.OperationTypeEnum;
import com.djcps.wms.workrecords.model.AchievementsBO;
import com.djcps.wms.workrecords.model.AchievementsInfoBO;
import com.djcps.wms.workrecords.model.AchievementsPO;
import com.djcps.wms.workrecords.server.AchievementsServer;
import com.djcps.wms.workrecords.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

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
        HttpResult result = achievementsServer.listAllAchievements(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    @Override
    public Map<String, Object> deliveryInfo(AchievementsInfoBO param) {
        HttpResult result = achievementsServer.listPdaDeliveryAchievementsInfo(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }

    @Override
    public Map<String, Object> entryInfo(AchievementsInfoBO param) {
        HttpResult result = achievementsServer.listPdaAchievementsInfo(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
    }
}
