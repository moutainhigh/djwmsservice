package com.djcps.wms.push.service;

import com.djcps.wms.push.model.PushAppBO;
import com.djcps.wms.push.model.PushMsgBO;

import java.util.Map;

/**
 * 消息推送
 * @author Chengw
 * @since 2018/1/23 16:04.
 */
public interface PushService {

    /**
     * 推送消息
     * @param param
     * @return
     */
    Map<String,Object> sendAppMsg(PushMsgBO param);

    /**
     * 设备注册
     * @param param
     * @return
     */
    Map<String,Object> registerMsg(PushAppBO param);

    /**
     * 设备注销
     * @param param
     * @return
     */
    Map<String,Object> logoutMsg(PushAppBO param);

}
