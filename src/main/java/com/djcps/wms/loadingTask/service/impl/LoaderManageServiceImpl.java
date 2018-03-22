package com.djcps.wms.loadingTask.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingTask.model.DelLoaderBO;
import com.djcps.wms.loadingTask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingTask.model.LoadingPersonBO;
import com.djcps.wms.loadingTask.model.SaveLoaderBO;
import com.djcps.wms.loadingTask.model.updataLoaderBO;
import com.djcps.wms.loadingTask.model.result.addOrderApplicationResult;
import com.djcps.wms.loadingTask.server.LoaderManageHttpServer;
import com.djcps.wms.loadingTask.service.LoaderManageService;
import com.djcps.wms.stocktaking.model.orderresult.InnerDate;

/**
 * 装车员管理实现类
 *
 * @author wyb
 * @since 2018/3/19
 */
@Service
public class LoaderManageServiceImpl implements LoaderManageService{
    
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
    public Map<String, Object> updataLoader(updataLoaderBO param) {
        HttpResult result = loaderManageHttpServer.updataLoader(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
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
        HttpResult result = loaderManageHttpServer.saveLoader(param);
        if (!ObjectUtils.isEmpty(result)) {
            return MsgTemplate.customMsg(result);
        }
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }
    /**
     * 获取装车员列表
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    @Override
    public Map<String, Object> loadingPersonList(GetLoadingPersonInfoBO param) {
        addOrderApplicationResult result = loaderManageHttpServer.loadingPersonList(param);
        if(ObjectUtils.isEmpty(result.getData())) {
            return MsgTemplate.successMsg();
        }
        InnerDate innerDate=new InnerDate();
        innerDate.setTotal(result.getData().getTotal());
        innerDate.setResult(result.getData().getResult());
        return MsgTemplate.successMsg(innerDate);
    }

    
    
    
    
    
}
