package com.djcps.wms.stocktaking.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.warehouse.controller.AreaController;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

/**
 * @title:盘点订单控制层
 * @description:
 * @author;wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@RestController
@RequestMapping(value = "/Stockaking")
public class StocktakingOrderController {
    private static final Logger logger = LoggerFactory.getLogger(AreaController.class);

    private Gson gson = new Gson();

    @RequestMapping(name="获取",value = "/getxx", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getxx(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            String dada="";
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(null,
                            new HibernateSupportedValidator<Object>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
