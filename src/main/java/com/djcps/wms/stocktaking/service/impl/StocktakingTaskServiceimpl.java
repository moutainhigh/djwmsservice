package com.djcps.wms.stocktaking.service.impl;

import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.order.model.WarehouseOrderDetailPO;
import com.djcps.wms.stocktaking.constant.StocktakingTaskConstant;
import com.djcps.wms.stocktaking.model.*;
import com.djcps.wms.stocktaking.server.StocktakingOrderServer;
import com.djcps.wms.stocktaking.server.StocktakingTaskServer;
import com.djcps.wms.stocktaking.service.StocktakingTaskService;
import com.google.gson.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**盘点任务实现类
 * @title:
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/10
 **/
@Service
public class StocktakingTaskServiceimpl implements StocktakingTaskService {

    @Autowired
    private StocktakingTaskServer stocktakingTaskServer;

    @Autowired
    private StocktakingOrderServer stocktakingOrderServer;

    Gson gson=new Gson();
    /**
     * 获取全盘仓库关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/10 9:55
     **/
    @Override
    public Map<String, Object> getAllStocktakingInfo(AddStocktakingBO addStocktakingBO) {
        //获取库位关联订单信息
        HttpResult result=stocktakingTaskServer.getAllStocktakingInfo(addStocktakingBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        //获取所有订单id
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        for (JsonElement jsonElement : asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
            orderidlist.add(orderId);
        }
        map.put("childOrderIds",orderidlist);

        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //组装参数
        List<StocktakingTaskBO> stocktakingTaskBOList=getStocktakingOrderDetail(asJsonArray,orderJsonArray0);
        return MsgTemplate.successMsg(stocktakingTaskBOList);
    }

    /**
     * 获取部分盘点关联订单
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:59
     **/
    @Override
    public Map<String, Object> getPartStocktakingInfo(AddStocktakingBO addStocktakingBO) {
        //获取库位关联订单信息
        HttpResult result=stocktakingTaskServer.getPartStocktakingInfo(addStocktakingBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        //获取所有订单id
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        for (JsonElement jsonElement : asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
            orderidlist.add(orderId);
        }
        map.put("childOrderIds",orderidlist);

        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //组装参数
        List<StocktakingTaskBO> stocktakingTaskBOList=getPartStocktakingOrderDetail(asJsonArray,orderJsonArray0);
        return MsgTemplate.successMsg(stocktakingTaskBOList);
    }

    /**
     * 更新盘点状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:18
     **/
    @Override
    public Map<String, Object> updateTaskState(UpdateStocktakingTaskBO updateStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.updateTaskState(updateStocktakingTaskBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 获取可用盘点人员列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:07
     **/
    @Override
    public Map<String, Object> getInventoryclerk() {
        //HttpResult result=stocktakingTaskServer.getInventoryclerk();
        HttpResult result1=new HttpResult();
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("盘点员01","1000001");
        map.put("盘点员02","1000002");
        map.put("盘点员03","1000003");
        result1.setData(map);
        return MsgTemplate.customMsg(result1);
    }

    /**
     * 获取新增盘点任务作业单号
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:44
     **/
    @Override
    public Map<String, Object> getNumber() {
        HttpResult result=stocktakingTaskServer.getNumber();
        //更换data为字符串"TS+日期20180101"
        String backjson= gson.toJson(result.getData());
        Map<String,List<String>> map=gson.fromJson(backjson, Map.class);
        String number= "";
        //返回的编号键名
        String key="numbers";
        if((map.get(key)).size()>0){
            number= AppConstant.TS+ (new SimpleDateFormat("yyyyMMdd")).format(new Date())+(map.get("numbers")).get(0);
        }
        result.setData(number);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 组装盘点订单
     * @author  wzy
     * @param asJsonArray 关联订单id信息的库位信息
     * @param orderJsonArray0 筛选过后的订单详情列表
     * @return
     * @create  2018/1/11 12:58
     **/
    public List<StocktakingTaskBO> getStocktakingOrderDetail(JsonArray asJsonArray,JsonArray orderJsonArray0){
        List<StocktakingTaskBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskBO>();
        for (JsonElement warehouseElement: asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("orderId").getAsString();
            JsonArray warehouseAreaInfo=warehouseElement.getAsJsonObject().get("warehouseAreaInfo").getAsJsonArray();
            //组装盘点任务仓库id
            String warehouseId=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseId").getAsString();
            String warehouseName=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseName").getAsString();
            for (JsonElement warehouseAreaElement:warehouseAreaInfo){
                //组装盘点任务库区id
                String warehouseAreaId=new JsonParser().parse(gson.toJson(warehouseAreaElement)).getAsJsonObject().get("warehouseAreaId").getAsString();
                String warehouseAreaName=new JsonParser().parse(gson.toJson(warehouseAreaElement)).getAsJsonObject().get("warehouseAreaName").getAsString();
                JsonArray warehouseLocInfo=warehouseAreaElement.getAsJsonObject().get("locationList").getAsJsonArray();
                for(JsonElement warehouseLocElement:warehouseLocInfo){
                    //组装盘点任务库位id
                    String warehouseLocId=new JsonParser().parse(gson.toJson(warehouseLocElement)).getAsJsonObject().get("warehouseLocId").getAsString();
                    String warehouseLocName=new JsonParser().parse(gson.toJson(warehouseLocElement)).getAsJsonObject().get("warehouseLocName").getAsString();
                    StocktakingTaskBO stocktakingTaskBO=new StocktakingTaskBO();
                    stocktakingTaskBO.setWarehouseId(warehouseId);
                    stocktakingTaskBO.setWarehouseName(warehouseName);
                    stocktakingTaskBO.setWarehouseAreaId(warehouseAreaId);
                    stocktakingTaskBO.setWarehouseAreaName(warehouseAreaName);
                    stocktakingTaskBO.setWarehouseLocId(warehouseLocId);
                    stocktakingTaskBO.setWarehouseLocName(warehouseLocName);
                    //组装盘点任务订单详情
                    for (JsonElement orderElement:orderJsonArray0){
                        String fchildorderid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fchildorderid").getAsString();
                        if (orderId.equals(fchildorderid)){
                            WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                            //订单参数拼接
                            getOrderDetail(orderDetailPO,orderDetailPO);
                            stocktakingTaskBO.setOrderDetail(orderDetailPO);
                            stocktakingTaskBOList.add(stocktakingTaskBO);
                        }
                    }
                }
            }
        }
        return stocktakingTaskBOList;
    }

    /**
     * 组装部分部分盘点订单
     * @author  wzy
     * @param asJsonArray 关联订单id信息的库位信息
     * @param orderJsonArray0 筛选过后的订单详情列表
     * @return
     * @create  2018/1/14 15:21
     **/
    public List<StocktakingTaskBO> getPartStocktakingOrderDetail(JsonArray asJsonArray,JsonArray orderJsonArray0){
        List<StocktakingTaskBO> stocktakingTaskBOList=new ArrayList<StocktakingTaskBO>();
        for (JsonElement warehouseElement: asJsonArray){
            String orderId = new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("orderId").getAsString();
            //组装盘点任务仓库id
            String warehouseId=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseId").getAsString();
            String warehouseName=new JsonParser().parse(gson.toJson(warehouseElement)).getAsJsonObject().get("warehouseName").getAsString();
            JsonObject warehouseAreaInfo=warehouseElement.getAsJsonObject().get("orderAreaAndLocDetailInfo").getAsJsonObject();
                //组装盘点任务库区id
                String warehouseAreaId=warehouseAreaInfo.get("warehouseAreaId").getAsString();
                String warehouseAreaName=warehouseAreaInfo.get("warehouseAreaName").getAsString();
               //组装盘点任务库位id
                String warehouseLocId=warehouseAreaInfo.get("warehouseLocId").getAsString();
                String warehouseLocName=warehouseAreaInfo.get("warehouseLocName").getAsString();
                StocktakingTaskBO stocktakingTaskBO=new StocktakingTaskBO();
                stocktakingTaskBO.setWarehouseId(warehouseId);
                stocktakingTaskBO.setWarehouseName(warehouseName);
                stocktakingTaskBO.setWarehouseAreaId(warehouseAreaId);
                stocktakingTaskBO.setWarehouseAreaName(warehouseAreaName);
                stocktakingTaskBO.setWarehouseLocId(warehouseLocId);
                stocktakingTaskBO.setWarehouseLocName(warehouseLocName);
                //组装盘点任务订单详情
                for (JsonElement orderElement:orderJsonArray0){
                    String fchildorderid=new JsonParser().parse(gson.toJson(orderElement)).getAsJsonObject().get("fchildorderid").getAsString();
                    if (orderId.equals(fchildorderid)){
                        WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                        //订单参数拼接
                        getOrderDetail(orderDetailPO,orderDetailPO);
                        stocktakingTaskBO.setOrderDetail(orderDetailPO);
                        stocktakingTaskBOList.add(stocktakingTaskBO);
                    }
                }
        }
        return stocktakingTaskBOList;
    }

    /**
     * 规格参数拼接
     * @param source
     * @param target
     * @return
     * @author:zdx
     * @date:2018年1月8日
     */
    private WarehouseOrderDetailPO getOrderDetail(WarehouseOrderDetailPO source, WarehouseOrderDetailPO target){
        //规格长宽高都不为null,才进行拼接
        if(!ObjectUtils.isEmpty(target.getFboxlength()) && !ObjectUtils.isEmpty(target.getFboxwidth()) &&
                !ObjectUtils.isEmpty(target.getFboxheight())){
            //拼接字符串,拼接成产品规格和下料规格
            source.setFproductRule(new StringBuffer().append(target.getFboxlength()).append("*")
                    .append(target.getFboxwidth()).append("*").append(target.getFboxheight()).toString());
        }
        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
                .append(target.getFmaterialwidth()).toString());
        //组织参数
        source.setFmaterialRule(new StringBuffer().append(target.getFmateriallength()).append("*")
                .append(target.getFmaterialwidth()).toString());
        source.setFordertime(target.getFordertime());
        source.setFdelivery(target.getFdelivery());
        source.setFgroupgoodname(target.getFgroupgoodname());
        source.setFflutetype(target.getFflutetype());
        source.setFstatus(target.getFstatus());
        source.setFmaterialname(target.getFmaterialname());
        source.setFlnglat(target.getFlnglat());
        source.setFpaymenttime(target.getFpaymenttime());
        source.setFaddressdetail(target.getFaddressdetail());
        source.setFcodeprovince(target.getFcodeprovince());
        source.setFconsignee(target.getFconsignee());
        source.setFcontactway(target.getFcontactway());
        source.setFpusername(target.getFpusername());
        source.setOrderId(source.getFchildorderid());
        source.setAmount(source.getFamount());
        source.setFamount(source.getAmount());

        return null;
    }

    /**
     * 保存新增的盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 22:18
     **/
    @Override
    public Map<String, Object> saveSoctakingTask(SaveStocktakingTaskBO saveStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.saveSoctakingTask(saveStocktakingTaskBO);
        int saveStocktakingType=saveStocktakingTaskBO.getSaveStocktakingType();
        if(saveStocktakingType==StocktakingTaskConstant.CONFIRM_PUSH){
            //请求推送服务
        }
        return  MsgTemplate.customMsg(result);
    }

    /**
     * 请求盘点任务信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 9:44
     **/
    @Override
    public Map<String, Object> stocktakingOrderInfoList(String jobId){
            HttpResult result=stocktakingTaskServer.stocktakingOrderInfoList(jobId);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空将值赋值为null,这里取到的是空数组
        if(ObjectUtils.isEmpty(result.getData())){
            return MsgTemplate.successMsg(null);
        }
            List<StocktakingTaskBO> list=new ArrayList<StocktakingTaskBO>();
            JsonArray OrderInfoJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
            for (JsonElement jsonElement : OrderInfoJsonArray){
                StocktakingTaskBO stocktakingTaskBO=gson.fromJson(jsonElement,StocktakingTaskBO.class);
                WarehouseOrderDetailPO warehouseOrderDetailPO=gson.fromJson(jsonElement,WarehouseOrderDetailPO.class);
                getOrderDetail(warehouseOrderDetailPO,warehouseOrderDetailPO);
                stocktakingTaskBO.setOrderDetail(warehouseOrderDetailPO);
                list.add(stocktakingTaskBO);
            }
            return  MsgTemplate.successMsg(list);
    }

    /**
     * 获取盘点任务订单信息，校验是否需要盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 11:26
     **/
    @Override
    public Map<String, Object> inventorySurplus(StocktakingTaskBO stocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.stocktakingOrderInfoByOrderId(stocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空,表示需要盘盈,请求订单详细信息
        if(ObjectUtils.isEmpty(result.getData())){
            Map<String,List<String>> map=new HashMap<String,List<String>>();
            String orderId=stocktakingTaskBO.getOrderId();
            List<String> list=new ArrayList<String>();
            list.add(orderId);
            map.put("childOrderIds",list);
            //请求订单详细信息
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();
            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //组装盘点任务订单详情
            for (JsonElement orderElement:orderJsonArray0){
                    WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                    //订单参数拼接
                    getOrderDetail(orderDetailPO,orderDetailPO);
                    stocktakingTaskBO.setOrderDetail(orderDetailPO);
            }
            return MsgTemplate.successMsg(stocktakingTaskBO);
        }
        //订单存在,盘盈失败
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 保存盘盈录入信息/录入单条盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 22:38
     **/
    @Override
    public Map<String, Object> saveInventoryProfitInfo(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO) {
        HttpResult result=stocktakingTaskServer.saveInventoryProfitInfo(saveStocktakingOrderInfoBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA发起盘盈
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 19:44
     **/
    @Override
    public Map<String, Object> pdaInventorySurplus(StocktakingTaskBO stocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.stocktakingOrderInfoByOrderId(stocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        // data数据为空,表示需要盘盈,请求订单详细信息
        if(ObjectUtils.isEmpty(result.getData())){
            Map<String,List<String>> map=new HashMap<String,List<String>>();
            String orderId=stocktakingTaskBO.getOrderId();
            List<String> list=new ArrayList<String>();
            list.add(orderId);
            map.put("childOrderIds",list);
            //请求订单详细信息
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //组装盘点任务订单详情
            for (JsonElement orderElement:orderJsonArray0){
                WarehouseOrderDetailPO orderDetailPO=gson.fromJson(orderElement,WarehouseOrderDetailPO.class);
                //订单参数拼接
                getOrderDetail(orderDetailPO,orderDetailPO);
                stocktakingTaskBO.setOrderDetail(orderDetailPO);
            }
            //获取相关订单库区库位信息,并拼接入返回信息中
            HttpResult warehouseAreaAndLocResult=stocktakingTaskServer.pdaWarehouseAreaAndLocInfo(stocktakingTaskBO);
//            JsonObject warehouseJsonObject = new JsonParser().parse(gson.toJson(warehouseAreaAndLocResult.getData())).getAsJsonObject();
//            StocktakingTaskBO warehouseStocktakingBo=gson.fromJson(warehouseJsonObject,StocktakingTaskBO.class);
//            BeanUtils.copyProperties(warehouseStocktakingBo,stocktakingTaskBO);
            return MsgTemplate.successMsg(stocktakingTaskBO);
        }
        //订单存在,盘盈失败
        return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
    }

    /**
     * 暂存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:06
     **/
    @Override
    public Map<String, Object> saveStocktakingResult(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList) {
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoBOList.size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoBOList.get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
        }
        map.put("childOrderIds",orderidlist);
        //获取批量订单信息列表
        HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
        JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

        //筛选fdblflag为0的订单信息
        JsonArray orderJsonArray0=new JsonArray();
        for (JsonElement jsonElement : orderJsonArray){
            int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
            if(fdbflag==0){
                orderJsonArray0.add(jsonElement);
            }
        }
        //拼接盘点任务订单+详细信息
        for (JsonElement jsonElement : orderJsonArray0){
            String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
            for(int i=0;i<saveStocktakingOrderInfoBOList.size();i++){
                if(orderId.equals(saveStocktakingOrderInfoBOList.get(i).getOrderId())){
                    SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO1=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
                    BeanUtils.copyProperties(saveStocktakingOrderInfoBO1,saveStocktakingOrderInfoBOList.get(i));
                }
            }
        }
        HttpResult result= stocktakingTaskServer.saveStocktakingResult(saveStocktakingOrderInfoBOList);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 15:22
     **/
    @Override
    public Map<String, Object> completeStocktakingTask(List<SaveStocktakingOrderInfoBO> saveStocktakingOrderInfoBOList,String PartentId) {
        List<String> orderidlist=new ArrayList<String>();
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=null;
        String jobId=null;
        String WarehouseId=null;
        //获取所有订单列表
        for(int i=0;i<saveStocktakingOrderInfoBOList.size();i++){
            saveStocktakingOrderInfoBO=saveStocktakingOrderInfoBOList.get(i);
            orderidlist.add(saveStocktakingOrderInfoBO.getOrderId());
            jobId=saveStocktakingOrderInfoBO.getRelativeId();
            WarehouseId=saveStocktakingOrderInfoBO.getWarehouseId();
        }
        //校验是否盘点完毕
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=new PdaGetStocktakingOrderBO();
        pdaGetStocktakingOrderBO.setJobId(jobId);
        pdaGetStocktakingOrderBO.setPartnerId(PartentId);
        pdaGetStocktakingOrderBO.setWarehouseId(WarehouseId);
        HttpResult result=stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO);
        //JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        int unfinishedStocktakingAmount = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonObject().get("unfinishedStocktakingAmount").getAsInt();
        //全部盘点完了
        if(unfinishedStocktakingAmount==0){
            map.put("childOrderIds",orderidlist);
            //获取批量订单信息列表
            HttpResult orderResult=stocktakingOrderServer.getInfoByChildIds(map);
            JsonArray orderJsonArray = new JsonParser().parse(gson.toJson(orderResult.getData())).getAsJsonArray();

            //筛选fdblflag为0的订单信息
            JsonArray orderJsonArray0=new JsonArray();
            for (JsonElement jsonElement : orderJsonArray){
                int fdbflag =new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("fdblflag").getAsInt();
                if(fdbflag==0){
                    orderJsonArray0.add(jsonElement);
                }
            }
            //拼接盘点任务订单+详细信息
            for (JsonElement jsonElement : orderJsonArray0){
                String orderId = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("orderId").getAsString();
                for(int i=0;i<saveStocktakingOrderInfoBOList.size();i++){
                    if(orderId.equals(saveStocktakingOrderInfoBOList.get(i).getOrderId())){
                        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO1=gson.fromJson(jsonElement,SaveStocktakingOrderInfoBO.class);
                        BeanUtils.copyProperties(saveStocktakingOrderInfoBO1,saveStocktakingOrderInfoBOList.get(i));
                    }
                }
            }
            HttpResult saveresult= stocktakingTaskServer.completeStocktakingTask(saveStocktakingOrderInfoBOList);
            return MsgTemplate.customMsg(saveresult);
        }
        //有未盘点的订单
        else {
            return MsgTemplate.customMsg(stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO));
        }
//        for(JsonElement jsonElement:asJsonArray){
//            int unfinishedStocktakingAmount = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("unfinishedStocktakingAmount").getAsInt();
//            if(unfinishedStocktakingAmount==0){
//                return MsgTemplate.successMsg(pdaCompleteStocktaking(pdaGetStocktakingOrderBO));
//            }
//        }
    }

    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:33
     **/
    @Override
    public Map<String, Object> stocktakingTaskList(GetStocktakingTaskBO getStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.stocktakingTaskList(getStocktakingTaskBO);
        return MsgTemplate.customMsg(result);
    }

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:35
     **/
    @Override
    public Map<String, Object> searchTaskList(GetStocktakingTaskBO getStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.searchTaskList(getStocktakingTaskBO);
        return MsgTemplate.customMsg(result);
    }


    /**
     * PDA请求盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:01
     **/
    @Override
    public Map<String, Object> pdaStocktakingTaskList(PdaStocktakingTaskBO pdaStocktakingTaskBO) {
        HttpResult result=stocktakingTaskServer.pdaStocktakingTaskList(pdaStocktakingTaskBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA请求盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 14:51
     **/
    @Override
    public Map<String, Object> pdaStocktakingOrderList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.pdaStocktakingOrderList(pdaGetStocktakingOrderBO);
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        List<PdaStocktakingOrderBO> pdaorderlist=new ArrayList<PdaStocktakingOrderBO>();
        //订单参数拼接
        for (JsonElement jsonElement:asJsonArray){
            PdaStocktakingOrderBO pdaStocktakingOrderBO=gson.fromJson(jsonElement,PdaStocktakingOrderBO.class);
            pdaStocktakingOrderBO.setMaterialRule(new StringBuffer().append(pdaStocktakingOrderBO.getMateriallength()).append("*")
                    .append(pdaStocktakingOrderBO.getMaterialwidth()).toString());
            pdaorderlist.add(pdaStocktakingOrderBO);
        }
        return MsgTemplate.successMsg(pdaorderlist);
    }

    /**
     * PDA请求订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:50
     **/
    @Override
    public Map<String, Object> pdaStocktakingOrderInfo(PdaStocktakingOrderInfo pdaStocktakingOrderInfo) {
        HttpResult result=stocktakingTaskServer.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:17
     **/
    @Override
    public Map<String, Object> savePdaStocktakingResult(SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO) {
        HttpResult result=stocktakingTaskServer.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA完成盘点请求
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 16:57
     **/
    @Override
    public Map<String, Object> getOrderAmount(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.getOrderAmount(pdaGetStocktakingOrderBO);
        JsonArray asJsonArray = new JsonParser().parse(gson.toJson(result.getData())).getAsJsonArray();
        for(JsonElement jsonElement:asJsonArray){
            int unfinishedStocktakingAmount = new JsonParser().parse(gson.toJson(jsonElement)).getAsJsonObject().get("unfinishedStocktakingAmount").getAsInt();
            if(unfinishedStocktakingAmount==0){
               return MsgTemplate.successMsg(pdaCompleteStocktaking(pdaGetStocktakingOrderBO));
            }
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:35
     **/
    @Override
    public Map<String, Object> pdaCompleteStocktaking(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.pdaCompleteStocktaking(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 13:44
     **/
    @Override
    public Map<String, Object> stocktakingResultList(PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO) {
        HttpResult result=stocktakingTaskServer.pdaCompleteStocktaking(pdaGetStocktakingOrderBO);
        if(!result.isSuccess()){
            HttpResult otherResult = new HttpResult();
            BeanUtils.copyProperties(result, otherResult);
            return MsgTemplate.customMsg(otherResult);
        }
        return MsgTemplate.customMsg(result);
    }
}
