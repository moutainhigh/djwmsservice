package com.djcps.wms.loadingtask.service;

import java.util.Map;

import com.djcps.wms.loadingtask.model.DelLoaderBO;
import com.djcps.wms.loadingtask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingtask.model.SaveLoaderBO;
import com.djcps.wms.loadingtask.model.UpdataLoaderBO;

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
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> updataLoader(UpdataLoaderBO param);
    /**
     * 删除装车员
     *
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> delLoader(DelLoaderBO param);
    /**
     * 新增装车员
     *
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> saveLoader(SaveLoaderBO param);
    /**
     * 获取装车员列表
     * 
     * @author wyb
     * @since 2018/3/20
     * @param param
     * @return
     */
    Map<String, Object> loadingPersonList(GetLoadingPersonInfoBO param);
    
}
