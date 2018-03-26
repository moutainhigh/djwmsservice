package com.djcps.wms.loadingtask.controller;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.loadingtask.model.DelLoaderBO;
import com.djcps.wms.loadingtask.model.GetLoadingPersonInfoBO;
import com.djcps.wms.loadingtask.model.SaveLoaderBO;
import com.djcps.wms.loadingtask.model.UpdataLoaderBO;
import com.djcps.wms.loadingtask.service.LoaderManageService;

/**
 * @title:装车员管理控制层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/3/19
 **/
@RestController
@RequestMapping(value = "/loader")
public class LoaderManageController {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(LoaderManageController.class);

    @Autowired
    private LoaderManageService loaderManageService;
    /**
     * 装车员列表
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "装车员列表", value = "/list", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> loadingPersonList(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            GetLoadingPersonInfoBO param = gson.fromJson(json, GetLoadingPersonInfoBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<GetLoadingPersonInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loaderManageService.loadingPersonList(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 修改装车员信息
     * @TODO
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "修改装车员信息", value = "/updata", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> updataLoaderInfo(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            UpdataLoaderBO param = gson.fromJson(json, UpdataLoaderBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<UpdataLoaderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loaderManageService.updataLoader(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 删除装车员信息
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "删除装车员信息", value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> delLoaderInfo(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            DelLoaderBO param = gson.fromJson(json, DelLoaderBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DelLoaderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loaderManageService.delLoader(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 新增装车员信息
     * 
     * @autuor wyb
     * @since 2018/3/19
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "新增装车员信息", value = "/save", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveLoaderInfo(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            SaveLoaderBO param = gson.fromJson(json, SaveLoaderBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<SaveLoaderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return loaderManageService.saveLoader(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
