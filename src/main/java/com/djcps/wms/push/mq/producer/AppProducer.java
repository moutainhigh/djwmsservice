package com.djcps.wms.push.mq.producer;

import com.djcps.wms.push.constant.PushConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * PDA MQ操作类
 * @author Chengw
 * @since 2018/1/23 15:51.
 */
@Component
public class AppProducer{

    private static final Logger logger = LoggerFactory.getLogger(AppProducer.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * APP注册 MQ
     * @param message
     * @throws InterruptedException
     */
    @Transactional(value = "rabbitTxManage",rollbackFor = {InterruptedException.class})
    public void sendRegisterMsg(String message) throws InterruptedException{
        logger.debug("App Register MqProducer路由：{} ",PushConstant.EXCHANGE_WMS);
        logger.debug("App Register MqProducer路由：{} ",PushConstant.APP_REGISTER);
        logger.info("App Register MqProducer路由：{}",message);
        amqpTemplate.convertAndSend(PushConstant.EXCHANGE_WMS,
                PushConstant.APP_REGISTER, message);
    }

    /**
     * 消息推送 MQ
     * @param message
     * @throws InterruptedException
     */
    @Transactional(value = "rabbitTxManage",rollbackFor = {InterruptedException.class})
    public void sendPushMsg(String message) throws InterruptedException{
        logger.debug("App Push MqProducer路由：{}",PushConstant.EXCHANGE_WMS);
        logger.debug("App Push MqProducer路由：{}",PushConstant.APP_PUSH);
        logger.info("App Push MqProducer路由：{}",message);
        amqpTemplate.convertAndSend(PushConstant.EXCHANGE_WMS,
                PushConstant.APP_PUSH, message);
    }

    /**
     * APP注销 MQ
     * @param message
     * @throws InterruptedException
     */
    @Transactional(value = "rabbitTxManage",rollbackFor = {InterruptedException.class})
    public void sendLogoutMsg(String message) throws InterruptedException{
        logger.debug("App Logout MqProducer路由：{}",PushConstant.EXCHANGE_WMS);
        logger.debug("App Logout MqProducer路由：{}",PushConstant.APP_LOGOUT);
        logger.info("App Logout MqProducer路由：{}",message);
        amqpTemplate.convertAndSend(PushConstant.EXCHANGE_WMS,
                PushConstant.APP_LOGOUT, message);
    }
}
