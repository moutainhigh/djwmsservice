package com.djcps.wms.push.service.impl;

import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.push.model.PushAppBO;
import com.djcps.wms.push.model.PushMsgBO;
import com.djcps.wms.push.mq.producer.AppProducer;
import com.djcps.wms.push.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 消息推送
 * @author Chengw
 * @since 2018/1/23 16:27.
 */
@Service
public class PushServiceImpl implements PushService{

    private Logger logger = LoggerFactory.getLogger(PushService.class);

    @Resource
    private AppProducer appProducer;


    /**
     * 推送消息
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> sendAppMsg(PushMsgBO param) {
        try {
            String json = gson.toJson(param);
            appProducer.sendPushMsg(json);
            return MsgTemplate.successMsg();
        }catch (InterruptedException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 设备注册
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> registerMsg(PushAppBO param) {
        try {
            String json = gson.toJson(param);
            appProducer.sendRegisterMsg(json);
            return MsgTemplate.successMsg();
        }catch (InterruptedException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 设备注销
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> logoutMsg(PushAppBO param) {
        try {
            String json = gson.toJson(param);
            appProducer.sendLogoutMsg(json);
            return MsgTemplate.successMsg();
        }catch (InterruptedException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }
}
