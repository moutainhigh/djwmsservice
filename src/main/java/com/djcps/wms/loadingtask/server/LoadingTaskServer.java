package com.djcps.wms.loadingtask.server;

import static com.djcps.wms.commons.utils.GsonUtils.gson;
import static com.djcps.wms.commons.utils.HttpResultUtils.returnResult;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.httpclient.HttpResult;
import com.djcps.wms.commons.request.NumberServerHttpRequest;
import com.djcps.wms.inneruser.model.param.RoleTypeBO;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.loadingtable.model.GetNumberBO;
import com.djcps.wms.loadingtask.model.AddOrderApplicationListBO;
import com.djcps.wms.loadingtask.model.AdditionalOrderBO;
import com.djcps.wms.loadingtask.model.ConfirmBO;
import com.djcps.wms.loadingtask.model.FinishLoadingBO;
import com.djcps.wms.loadingtask.model.LoadingBO;
import com.djcps.wms.loadingtask.model.LoadingPersonBO;
import com.djcps.wms.loadingtask.model.OutOrderInfoBO;
import com.djcps.wms.loadingtask.model.RejectRequestBO;
import com.djcps.wms.loadingtask.model.RemoveLoadingPersonBO;
import com.djcps.wms.loadingtask.model.result.FinishLoadingPO;
import com.djcps.wms.loadingtask.model.result.OrderInventoryPO;
import com.djcps.wms.loadingtask.model.result.OrderRedundantPO;
import com.djcps.wms.loadingtask.model.result.PickerPO;
import com.djcps.wms.loadingtask.request.WmsForLoadingTaskHttpRequest;
import com.djcps.wms.outorder.request.WmsForOutOrderHttpRequest;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rpc.plugin.http.HTTPResponse;

/**
 * @author wyb
 * @since 2018/3/19
 */
@Component
public class LoadingTaskServer {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoadingTaskServer.class);
    @Autowired
    private WmsForLoadingTaskHttpRequest wmsForLoadingTaskHttpRequest;
    @Autowired
    private NumberServerHttpRequest numberServerHttpRequest;
    @Autowired
    private WmsForOutOrderHttpRequest wmsForOutOrderHttpRequest;

    /**
     * 获取装车员列表
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult loadingPersonList(LoadingPersonBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.loadingPersonList(requestBody);
        return returnResult(httpResponse);

    }

    /**
     * 移除装车员
     * 
     * @author wyb
     * @param
     * @return
     * @create 2018/3/20
     **/
    public HttpResult removeLoadingPerson(RemoveLoadingPersonBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.removeLoadingPerson(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * (装车员界面确认)获取订单列表和运单信息
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/20
     */
    public HttpResult getOrderList(ConfirmBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.getOrderList(requestBody);
        return returnResult(httpResponse);

    }
    /**
     * (装车员界面确认)修改装车员状态，批量接口
     * @param param
     * @return
     */
    public HttpResult updateLoadPersonStatus(ConfirmBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.updateLoadPersonStatus(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 装车
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult loading(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.loading(requestBody);
        return returnResult(httpResponse);
    }

    /**
     * 追加订单
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult additionalOrder(AdditionalOrderBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.additionalOrder(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 驳回申请
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult rejectRequest(RejectRequestBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.rejectRequest(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 追加订单申请列表web
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult addOrderApplicationList(AddOrderApplicationListBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.addOrderApplicationList(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 修改运单状态
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public HttpResult updateWayBill(FinishLoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.updateWayBill(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 完成装车
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public FinishLoadingPO finishLoading(FinishLoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody =RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.finishLoading(requestBody);
        HttpResult httpResult = returnResult(httpResponse);
        System.out.println(httpResult);
        FinishLoadingPO result = null;
        if(httpResponse.isSuccessful()){
            String http = gson.toJson(httpResult.getData());
            result = gson.fromJson(http, FinishLoadingPO.class);
        }
        if(result == null){
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    
    /**
     * 通过运单号获取订单id，车辆id，车牌号等信息
     */
    public HttpResult getInfoByWayBillId(FinishLoadingBO param){
    	String json = gson.toJson(param);
    	RequestBody rb =RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    	HTTPResponse response = wmsForOutOrderHttpRequest.getInfoByWayBillId(rb);
    	HttpResult result = returnResult(response);
    	return result;
    }

    /**
     * 生成出库单数据
     */
    public HttpResult insertOutOrder(List<OutOrderInfoBO> params){
    	String json = gson.toJson(params);
    	RequestBody rb =RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
    	HTTPResponse response = wmsForOutOrderHttpRequest.insertOutOrder(rb);
    	HttpResult result = returnResult(response);
    	return result;
    }

    /**
     * 获取统一编号服务的编号
     * @param count
     * @return
     * @author ldh
     */
    public HttpResult getNumber(int count){
    	GetNumberBO getNumberBO=new GetNumberBO();
		getNumberBO.setCount(count);
		String json=gson.toJson(getNumberBO);
		okhttp3.RequestBody rb = okhttp3.RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
		//调用借口获取信息
		HTTPResponse http = numberServerHttpRequest.getnumber(rb);
		//校验请求是否成功
		HttpResult result=returnResult(http);
		//更换data为字符串"ZTC+numbers"
		String backjson= gson.toJson(result.getData());
		System.out.println("");
		Map<String,List<String>> map=gson.fromJson(backjson, Map.class);
		String number= "";
		//返回的编号键名
		String key="numbers";
		if((map.get(key)).size()>0){
			number=AppConstant.CK+(map.get("numbers")).get(0);
		}
		result.setData(number);
		return result;
    	
    }

    public HttpResult getLoadingTableIdByUserId(UserInfoVO userInfoVO) {
        String json = gson.toJson(userInfoVO);
        RequestBody rb = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        HTTPResponse response = wmsForLoadingTaskHttpRequest.getLoadingTableIdByUserId(rb);
        HttpResult result = returnResult(response);
        return result;
    }
    /**
     * 装车
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/5/19
     */
    public HttpResult load(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.load(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 全部退库
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/5/19
     */
    public HttpResult allAncellingStock(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.allAncellingStock(requestBody);
        return returnResult(httpResponse);
    }
    /**
     * 部分退库
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/5/19
     */
    public HttpResult partAancellingStock(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.partAncellingStock(requestBody);
        return returnResult(httpResponse);
    }
    
    /**
     * 获取原库存表信息
     * 
     * @author wyb
     * @param param
     * @return
     * @create 2018/3/21
     */
    public OrderInventoryPO getInventoryInfo(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody =RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.getInventoryInfo(requestBody);
        HttpResult httpResult = returnResult(httpResponse);
        System.out.println(httpResult);
        OrderInventoryPO result = null;
        if(httpResponse.isSuccessful()){
            String http = gson.toJson(httpResult.getData());
            result = gson.fromJson(http, OrderInventoryPO.class);
        }
        if(result == null){
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    
    
    
    public OrderRedundantPO getOrderRedundantInfo(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody =RequestBody.create(MediaType.parse("application/json; charset=utf-8"),paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.getOrderRedundantInfo(requestBody);
        HttpResult httpResult = returnResult(httpResponse);
        System.out.println(httpResult);
        OrderRedundantPO result = null;
        if(httpResponse.isSuccessful()){
            String http = gson.toJson(httpResult.getData());
            result = gson.fromJson(http, OrderRedundantPO.class);
        }
        if(result == null){
            LOGGER.error("Http请求出错,HttpResult结果为null");
        }
        return result;
    }
    /**
     * 获取提货员信息列表
     * 
     * @param json
     * @return
     **/
    public List<PickerPO> getPickerList(LoadingBO param) {
        String paramJson = gson.toJson(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramJson);
        HTTPResponse httpResponse = wmsForLoadingTaskHttpRequest.getPickerList(requestBody);
        if (httpResponse.isSuccessful()) {
            HttpResult result = gson.fromJson(httpResponse.getBodyString(), HttpResult.class);
            if (result.isSuccess()) {
                String data = gson.toJson(result.getData());
                if (!ObjectUtils.isEmpty(data)) {
                    List<PickerPO> wmsRoleInfoPOList = JSONObject.parseArray(data,PickerPO.class);
                    return wmsRoleInfoPOList;
                }
            }
        }
        return null;
    }
}
