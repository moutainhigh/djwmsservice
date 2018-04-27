package com.djcps.wms.workrecords.service;

import com.djcps.wms.workrecords.model.AchievementsBO;
import com.djcps.wms.workrecords.model.AchievementsInfoBO;

import java.util.Map;

/**
 * @author Chengw
 * @create 2018/4/24 11:24.
 * @since 1.0.0
 */
public interface AchievementsService {

    /**
     * 统计
     * @return
     */
    Map<String,Object> statistics(AchievementsBO param);

    /**
     * 提货信息
     * @return
     */
    Map<String,Object> deliveryInfo(AchievementsInfoBO param);

    /**
     * 信息
     * @return
     */
    Map<String,Object> entryInfo(AchievementsInfoBO param);

}
