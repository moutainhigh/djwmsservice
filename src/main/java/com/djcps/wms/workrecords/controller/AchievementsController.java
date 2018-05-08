package com.djcps.wms.workrecords.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.inneruser.annotation.InnerUser;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.model.result.UserInfoVO;
import com.djcps.wms.record.enums.OperationTypeEnum;
import com.djcps.wms.workrecords.model.AchievementsBO;
import com.djcps.wms.workrecords.model.AchievementsInfoBO;
import com.djcps.wms.workrecords.service.AchievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validation;
import java.util.Map;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 个人中心 控制层
 * @author Chengw
 * @create 2018/4/23 15:45.
 * @since 1.0.0
 */
@RestController
@RequestMapping("/achievements")
public class AchievementsController {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(AchievementsController.class);

    @Autowired
    private AchievementsService achievementsService;

    /**
     * 绩效统计
     * @param json
     * @author py
     */
    @RequestMapping(name = "绩效统计", value = "/statistics", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> statistics(@RequestBody(required = false) String json, @InnerUser UserInfoVO userInfoVO) {
        try {
            LOGGER.debug("json : " + json);
            AchievementsBO param = gson.fromJson(json, AchievementsBO.class);
            param.setOperatorId(userInfoVO.getId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<AchievementsBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return achievementsService.statistics(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 提货绩效
     * @param json
     * @author py
     */
    @RequestMapping(name = "提货绩效", value = "/delivery", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> delivery(@RequestBody(required = false) String json,@InnerUser UserInfoVO userInfoVO) {
        try {
            LOGGER.debug("delivery : " + json);
            AchievementsInfoBO param = gson.fromJson(json, AchievementsInfoBO.class);
            param.setOperationType(OperationTypeEnum.DELIVERY.getCode());
            param.setOperatorId(userInfoVO.getId());
            ComplexResult ret = FluentValidator.checkAll().failFast().on(param,
                    new HibernateSupportedValidator<AchievementsInfoBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()){
                return MsgTemplate.failureMsg(ret);
            }
            return achievementsService.deliveryInfo(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 入库绩效
     * @param json
     * @author py
     */
    @RequestMapping(name = "入库绩效", value = "/entry", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> entry(@RequestBody(required = false) String json,@InnerUser UserInfoVO userInfoVO) {
        try {
            LOGGER.debug("entry : " + json);
            AchievementsInfoBO param = gson.fromJson(json, AchievementsInfoBO.class);
            param.setOperationType(OperationTypeEnum.ENTRY.getCode());
            param.setOperatorId(userInfoVO.getId());
            ComplexResult ret = FluentValidator.checkAll().failFast().on(param,
                    new HibernateSupportedValidator<AchievementsInfoBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()){
                return MsgTemplate.failureMsg(ret);
            }
            return achievementsService.entryInfo(param);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
