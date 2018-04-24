package com.djcps.wms.permission.controller;

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
import com.djcps.wms.commons.aop.inneruser.annotation.OperatorAnnotation;
import com.djcps.wms.commons.constant.AppConstant;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.permission.constants.PermissionConstants;
import com.djcps.wms.permission.model.bo.DeletePerParamBO;
import com.djcps.wms.permission.model.bo.DeletePermissionBO;
import com.djcps.wms.permission.model.bo.GetWmsPermissionBO;
import com.djcps.wms.permission.model.bo.PermissionBO;
import com.djcps.wms.permission.model.bo.PermissionChooseBO;
import com.djcps.wms.permission.model.bo.UpdatePermissionBO;
import com.djcps.wms.permission.model.bo.UserPermissionBO;
import com.djcps.wms.permission.service.PermissionService;

import static com.djcps.wms.commons.utils.GsonUtils.gson;


/**
 * @author zhq
 * 权限控制层
 * 2018年4月12日
 */
@RestController
@RequestMapping("/permission")
public class PersmissionController {
    @Autowired
    private PermissionService permissionService;

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(PersmissionController.class);

    /**
     * 得到组合权限列表
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map<String, Object> getPermissionAll(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            LOGGER.debug("json:" + json);
            PermissionBO perBO = gson.fromJson(json, PermissionBO.class);
            BeanUtils.copyProperties(operatorInfoBO, perBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(perBO, new HibernateSupportedValidator<PermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.getPermissionList(perBO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取组合权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 得到wms权限
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/getWmsPermission", method = RequestMethod.POST)
    public Map<String, Object> getWmsPermission(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            LOGGER.debug("json:" + json);
            //BaseOrgBO baseOrgBO=gson.fromJson(json, BaseOrgBO.class);
            GetWmsPermissionBO wmsPerBO = new GetWmsPermissionBO();
            BeanUtils.copyProperties(operatorInfoBO, wmsPerBO);
            //wms对应的id
            wmsPerBO.setFirstnode(PermissionConstants.BUSINESS_ID);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(wmsPerBO, new HibernateSupportedValidator<GetWmsPermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.getWmsPermission(wmsPerBO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取WMS权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 新增权限包
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/insertWmsPermission", method = RequestMethod.POST)
    public Map<String, Object> insertWmsPermission(@RequestBody(required = false) String json, HttpServletRequest request, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            LOGGER.debug("json:" + json);
            UpdatePermissionBO updatePermissionBO = gson.fromJson(json, UpdatePermissionBO.class);
            BeanUtils.copyProperties(operatorInfoBO, updatePermissionBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(updatePermissionBO, new HibernateSupportedValidator<UpdatePermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.insertPermission(updatePermissionBO, partnerInfoBo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("新增权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 删除权限包
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
    public Map<String, Object> deletePermission(@RequestBody(required = false) String json, HttpServletRequest request, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            LOGGER.debug("json:" + json);
            DeletePermissionBO deleteBO = gson.fromJson(json, DeletePermissionBO.class);
            BeanUtils.copyProperties(operatorInfoBO, deleteBO);
            deleteBO.setUserid(partnerInfoBo.getOperatorId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(deleteBO, new HibernateSupportedValidator<DeletePermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            DeletePerParamBO deletePerParam = new DeletePerParamBO();
            BeanUtils.copyProperties(deleteBO, deletePerParam);
            deletePerParam.setBusiness(deleteBO.getBusiness());
            return permissionService.deletePermission(deletePerParam);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 根据组合权限id和公司id，获取获取组合权限信息
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/getPermissionChoose", method = RequestMethod.POST)
    public Map<String, Object> getPermissionList(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            LOGGER.debug("json:" + json);
            PermissionChooseBO perChooseBO = gson.fromJson(json, PermissionChooseBO.class);
            BeanUtils.copyProperties(operatorInfoBO, perChooseBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(perChooseBO, new HibernateSupportedValidator<PermissionChooseBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.getPerChoose(perChooseBO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("批量查询异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 修改权限包
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "/updateWmsPermission", method = RequestMethod.POST)
    public Map<String, Object> updateWmsPermission(@RequestBody(required = false) String json, HttpServletRequest request, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            LOGGER.debug("json:" + json);
            UpdatePermissionBO updatePermissionBO = gson.fromJson(json, UpdatePermissionBO.class);
            BeanUtils.copyProperties(operatorInfoBO, updatePermissionBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(updatePermissionBO, new HibernateSupportedValidator<UpdatePermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.updatePermission(updatePermissionBO, partnerInfoBo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取用户权限数据项
     *
     * @param json
     * @param operatorInfoBO
     * @return
     */
    @RequestMapping(name = "获取用户权限数据项", value = "/userPermission", method = RequestMethod.POST)
    public Map<String, Object> userPermissionForPda(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            LOGGER.debug("json:" + json);
            UserPermissionBO userPermissionBO = gson.fromJson(json, UserPermissionBO.class);
            userPermissionBO.setId(operatorInfoBO.getOperator());
            userPermissionBO.setBusiness(AppConstant.WMS);
            userPermissionBO.setpBusiness(PermissionConstants.BUSINESS_ID);
            userPermissionBO.setIp(operatorInfoBO.getIp());
            BeanUtils.copyProperties(operatorInfoBO, userPermissionBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(userPermissionBO, new HibernateSupportedValidator<UserPermissionBO>()
                            .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return permissionService.getUserPermission(userPermissionBO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户权限异常:{}" + e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

}
