package com.djcps.wms.role.controller;

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
import com.djcps.wms.commons.aop.inneruser.annotation.OperatorAnnotation;
import com.djcps.wms.commons.base.BaseBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.role.model.DeleteBO;
import com.djcps.wms.role.model.RoleListBO;
import com.djcps.wms.role.model.SaveBO;
import com.djcps.wms.role.model.UpdateRoleInfoBO;
import com.djcps.wms.role.service.RoleService;

/**
 * @title:角色信息控制层
 * @description:
 * @author:wyb
 * @company:djwms
 * @create:2018/4/13
 **/
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    /**
     * 角色列表
     * 
     * @autuor wyb
     * @since 2018/4/13
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "角色列表", value = "/roleList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> roleList(@RequestBody(required = false) String json,@OperatorAnnotation OperatorInfoBO operatorInfoBO
            ,HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            RoleListBO param = gson.fromJson(json, RoleListBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            param.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<RoleListBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return roleService.roleList(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 更新角色信息
     * 
     * @autuor wyb
     * @since 2018/4/13
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "更新角色信息", value = "/update", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> update(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            UpdateRoleInfoBO param = gson.fromJson(json, UpdateRoleInfoBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            param.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<UpdateRoleInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return roleService.update(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 获取角色及权限信息
     * 
     * @autuor wyb
     * @since 2018/4/13
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "获取角色及权限信息", value = "/getRoleInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getRoleInfo(HttpServletRequest request) {
        try {
            // 解析参数
            BaseBO param = new BaseBO();
            return roleService.getRoleType(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 删除角色信息
     * 
     * @autuor wyb
     * @since 2018/4/13
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "删除角色信息", value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> delete(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            DeleteBO param = gson.fromJson(json, DeleteBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            param.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<DeleteBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return roleService.delete(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
    /**
     * 保存角色信息
     * 
     * @autuor wyb
     * @since 2018/4/13
     * @param request
     * @param json
     * @return
     */
    @RequestMapping(name = "保存角色信息", value = "/save", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> save(@RequestBody(required = false) String json,
            HttpServletRequest request) {
        try {
            LOGGER.debug("json : " + json);
            SaveBO param = gson.fromJson(json, SaveBO.class);
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            BeanUtils.copyProperties(partnerInfoBo, param);
            param.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(param,
                            new HibernateSupportedValidator<SaveBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return roleService.save(param);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
