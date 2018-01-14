package com.djcps.wms.stocktaking.service;

import com.djcps.wms.stocktaking.model.*;

import java.util.List;
import java.util.Map;

/**
 * @title:盘点任务业务层
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/10
 **/
public interface StocktakingTaskService {

    /**
     * 获取全盘仓库关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/10 9:53
     **/
    Map<String,Object> getAllStocktakingInfo(AddStocktakingBO addStocktakingBO);

    /**
     * 获取部分盘点仓库关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:57
     **/
    Map<String,Object> getPartStocktakingInfo(AddStocktakingBO addStocktakingBO);

    /**
     * 更新盘点状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:17
     **/
    Map<String,Object> updateTaskState(UpdateStocktakingTaskBO updateStocktakingTaskBO);

    /**
     * 获取可用盘点人员列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    Map<String,Object> getInventoryclerk();

    /**
     * 获取随机编码生成作业单号
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    Map<String,Object> getNumber();

    /**
     * 确认盘点订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    Map<String,Object> saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO);

    /**
     * 请求盘点任务信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 9:37
     **/
    Map<String,Object> stocktakingOrderInfoList(String jobId);

    /**
     * 获取盘点任务订单信息，校验是否需要盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 9:37
     **/
    Map<String,Object> inventorySurplus(StocktakingTaskBO stocktakingTaskBO);

    /**
     * 保存盘盈录入信息/录入单挑盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 22:36
     **/
    Map<String,Object> saveInventoryProfitInfo(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO);

    /**
     * 暂存盘点结果
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/12 15:06
     **/
    Map<String,Object> saveStocktakingResult(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList);

    /**
     * 保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:22
     **/
    Map<String,Object> completeStocktakingTask(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList,String partentId);

    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:33
     **/
    Map<String,Object> stocktakingTaskList(GetStocktakingTaskBO getStocktakingTaskBO);

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:35
     **/
    Map<String,Object> searchTaskList(GetStocktakingTaskBO getStocktakingTaskBO);


    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:01
     **/
    Map<String,Object> pdaStocktakingTaskList(PdaStocktakingTaskBO pdaStocktakingTaskBO);

    /**
     *  PDA请求盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:46
     **/
    Map<String,Object> pdaStocktakingOrderList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA获取订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:49
     **/
    Map<String,Object> pdaStocktakingOrderInfo(PdaStocktakingOrderInfo pdaStocktakingOrderInfo);

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:17
     **/
    Map<String,Object> savePdaStocktakingResult(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO);

    /**
     * PDA完成盘点请求
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:54
     **/
    Map<String,Object> getOrderAmount(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:35
     **/
    Map<String,Object> pdaCompleteStocktaking(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA发起盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 19:44
     **/
    Map<String,Object> pdaInventorySurplus(StocktakingTaskBO stocktakingTaskBO);

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 13:37
     **/
    Map<String,Object> stocktakingResultList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

}
