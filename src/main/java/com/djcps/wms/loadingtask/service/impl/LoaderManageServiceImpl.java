package com.djcps.wms.loadingtask.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.model.DelLoaderBO;
import com.djcps.wms.loadingtask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingtask.model.SaveLoaderBO;
import com.djcps.wms.loadingtask.model.UpdataLoaderBO;
import com.djcps.wms.loadingtask.server.LoaderManageHttpServer;
import com.djcps.wms.loadingtask.service.LoaderManageService;

/**
 * 装车员管理实现类
 *
 * @author wyb
 * @since 2018/3/19
 */
@Service
public class LoaderManageServiceImpl implements LoaderManageService {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoaderManageService.class);

    @Autowired
    private LoaderManageHttpServer loaderManageHttpServer;

    /**
     * 修改装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    @Override
    public Map<String, Object> updataLoader(UpdataLoaderBO param) {
        HttpResult result = loaderManageHttpServer.updataLoader(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 删除装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    @Override
    public Map<String, Object> delLoader(DelLoaderBO param) {
        HttpResult result = loaderManageHttpServer.delLoader(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 新增装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    @Override
    public Map<String, Object> saveLoader(SaveLoaderBO param) {
    	param.setId(UUID.randomUUID().toString());
        HttpResult result = loaderManageHttpServer.saveLoader(param);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 获取装车员列表
     * 
     * @author  wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    @Override
    public Map<String, Object> loadingPersonList(GetLoadingPersonInfoBO param) {
        HttpResult result = loaderManageHttpServer.loadingPersonList(param);
        return MsgTemplate.customMsg(result);
    }
}
