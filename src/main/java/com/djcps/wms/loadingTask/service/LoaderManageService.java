package com.djcps.wms.loadingTask.service;

import java.util.Map;

import com.djcps.wms.loadingTask.model.DelLoaderBO;
import com.djcps.wms.loadingTask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingTask.model.SaveLoaderBO;
import com.djcps.wms.loadingTask.model.UpdataLoaderBO;

/**
 * 装车员管理 service
 * 
 * @author wyb
 * @since 2018/3/19
 */
public interface LoaderManageService {
    /**
     * 修改装车员
     * 
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> updataLoader(UpdataLoaderBO param);
    /**
     * 删除装车员
     *
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> delLoader(DelLoaderBO param);
    /**
     * 新增装车员
     *
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> saveLoader(SaveLoaderBO param);
    /**
     * 获取装车员列表
     * 
     * @autuor wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> loadingPersonList(GetLoadingPersonInfoBO param);
    
}
