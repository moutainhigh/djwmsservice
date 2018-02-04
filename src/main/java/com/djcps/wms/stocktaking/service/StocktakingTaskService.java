package com.djcps.wms.stocktaking.service;

import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.stocktaking.model.*;

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
     * 优化版新增部分盘点
     * @author  wzy
     * @param addStocktakingBO
     * @return
     * @create  2018/1/24 16:36
     **/
    Map<String,Object> addTaskByPart(AddStocktakingBO addStocktakingBO);

    /**
     * 优化版新增全盘
     * @author  wzy
     * @param addTaskBO
     * @return
     * @create  2018/1/25 9:06
     **/
    Map<String,Object> addTaskByAll(AddTaskBO addTaskBO);

    /**
     * 更新盘点状态
     * @author  wzy
     * @param updateStocktakingTaskBO
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
     * @param saveStocktakingTaskBO
     * @return
     * @create  2018/1/11 15:07
     **/
    Map<String,Object> saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO);


    /**
     * 请求盘点任务信息
     * @author  wzy
     * @param pdaGetStocktakingOrderBO
     * @return
     * @create  2018/1/12 9:37
     **/
    Map<String,Object> stocktakingOrderInfoList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * 获取盘点任务订单信息，校验是否需要盘盈
     * @author  wzy
     * @param stocktakingTaskBO
     * @return
     * @create  2018/1/12 9:37
     **/
    //Map<String,Object> inventorySurplus(StocktakingTaskBO stocktakingTaskBO);

    Map<String,Object> inventorySurplus2(StocktakingTaskBO2 stocktakingTaskBO);
    /**
     * 保存盘盈录入信息/录入单挑盘点任务
     * @author  wzy
     * @param saveStocktakingOrderInfoBO
     * @return
     * @create  2018/1/14 22:36
     **/
    Map<String,Object> saveInventoryProfitInfo(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO);



    /**
     * 暂存盘点结果
     * @author  wzy
     * @param saveStocktakingOrderInfoList
     * @param  partnerInfoBO
     * @return
     * @create  2018/1/12 15:06
     **/
    Map<String,Object> saveStocktakingResult(SaveStocktakingOrderInfoList saveStocktakingOrderInfoList,PartnerInfoBO partnerInfoBO);

    /**
     * 保存盘点结果
     * @author  wzy
     * @param saveStocktakingOrderInfoList
     * @param  partnerInfoBO
     * @return
     * @create  2018/1/12 15:22
     **/
    Map<String,Object> completeStocktakingTask(SaveStocktakingOrderInfoList saveStocktakingOrderInfoList, PartnerInfoBO partnerInfoBO);

    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param getStocktakingTaskBO
     * @return
     * @create  2018/1/12 16:33
     **/
    Map<String,Object> stocktakingTaskList(GetStocktakingTaskBO getStocktakingTaskBO);

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param getStocktakingTaskBO
     * @return
     * @create  2018/1/12 16:35
     **/
    Map<String,Object> searchTaskList(GetStocktakingTaskBO getStocktakingTaskBO);


    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param pdaStocktakingTaskBO
     * @return
     * @create  2018/1/13 14:01
     **/
    Map<String,Object> pdaStocktakingTaskList(PdaStocktakingTaskBO pdaStocktakingTaskBO);

    /**
     *  PDA请求盘点任务订单列表
     * @author  wzy
     * @param pdaGetStocktakingOrderBO
     * @return
     * @create  2018/1/13 14:46
     **/
    Map<String,Object> pdaStocktakingOrderList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA获取订单详情
     * @author  wzy
     * @param pdaStocktakingOrderInfo
     * @return
     * @create  2018/1/13 15:49
     **/
    Map<String,Object> pdaStocktakingOrderInfo(PdaStocktakingOrderInfo pdaStocktakingOrderInfo);

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param saveStocktakingOrderInfoBO
     * @return
     * @create  2018/1/13 16:17
     **/
    Map<String,Object> savePdaStocktakingResult(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO);

    /**
     * PDA完成盘点请求
     * @author  wzy
     * @param pdaGetStocktakingOrderBO
     * @return
     * @create  2018/1/13 16:54
     **/
    Map<String,Object> getOrderAmount(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param pdaGetStocktakingOrderBO
     * @return
     * @create  2018/1/13 15:35
     **/
    Map<String,Object> pdaCompleteStocktaking(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * PDA发起盘盈
     * @author  wzy
     * @param stocktakingTaskBO
     * @return
     * @create  2018/1/13 19:44
     **/
    Map<String,Object> pdaInventorySurplus(StocktakingTaskBO stocktakingTaskBO);

    Map<String,Object> pdaInventorySurplus2(StocktakingTaskBO2 stocktakingTaskBO);

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param pdaGetStocktakingOrderBO
     * @return
     * @create  2018/1/14 13:37
     **/
    Map<String,Object> stocktakingResultList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO);

    /**
     * 获取操作记录
     * @author  wzy
     * @param getStocktakingTaskBO
     * @return
     * @create  2018/1/17 10:33
     **/
    Map<String,Object> operationRecordList(GetStocktakingTaskBO getStocktakingTaskBO);

    /**
     * 查看盘点任务进行情况
     * @author  wzy
     * @param getStocktakingTaskBO
     * @return 
     * @create  2018/1/17 10:52
     **/
    Map<String,Object> stocktakingCompleteStatus(GetStocktakingTaskBO getStocktakingTaskBO);

    /**
     * 获取库位订单信息
     * @author  wzy
     * @param orderWarehouseLocInfoBO
     * @return
     * @create  2018/1/18 18:24
     **/
    Map<String,Object> orderWarehouseLocInfo(OrderWarehouseLocInfoBO orderWarehouseLocInfoBO);

    /**
     * web盘盈是获取相关库区库位
     * @author  wzy
     * @param jobAndWarehouseBO
     * @return
     * @create  2018/1/23 17:20
     **/
    Map<String,Object> areaAndLocInfo(JobAndWarehouseBO jobAndWarehouseBO);

    /**
     * 获取所有库位
     * @author  wzy
     * @param jobAndWarehouseBO
     * @return
     * @create  2018/1/23 17:45
     **/
    Map<String,Object> getLocationAllList(JobAndWarehouseBO jobAndWarehouseBO);

    /**
     * 更新打印次数接口
     * @author  wzy
     * @param printCountBO
     * @return 
     * @create  2018/1/25 9:34
     **/
    Map<String,Object> printCount(PrintCountBO printCountBO);

    /**
     * 获取未下发作业订单信息
     * @author  wzy
     * @param printCountBO
     * @return 
     * @create  2018/1/25 15:29
     **/
    Map<String,Object> noSendOrderInfo(PrintCountBO printCountBO);

    /**
     * 单独的下发,更新pda作业状态
     * @author  wzy
     * @param updateStocktakingTaskBO
     * @return
     * @create  2018/1/26 9:41
     **/
    Map<String,Object> updatePdaStatus(UpdateStocktakingTaskBO updateStocktakingTaskBO);
}
