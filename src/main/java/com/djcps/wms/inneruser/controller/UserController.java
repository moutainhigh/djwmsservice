package com.djcps.wms.inneruser.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.inneruser.annotation.OperatorAnnotation;
import com.djcps.wms.commons.aop.log.AddLog;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.model.param.*;
import com.djcps.wms.inneruser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

import static com.djcps.wms.commons.utils.GsonUtils.gson;

/**
 * 用户管理
 * @author wzy
 * @date 2018/4/12
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 查看用户信息
     * @author  wzy
     * @param json String
     * @param request HttpServletRequest
     * @param operatorInfoBO OperatorInfoBO
     * @return http
     * @date  2018/4/12 15:21
     **/
    @AddLog(value ="org获取单条用户信息",module = "用户管理")
    @RequestMapping(name="org获取单条用户信息",value = "/getUser", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getUser(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            GetOrgUserInfoBO orgGetUserInfoById=gson.fromJson(json,GetOrgUserInfoBO.class);
            orgGetUserInfoById.setId(orgGetUserInfoById.getUserId());
            BeanUtils.copyProperties(operatorInfoBO,orgGetUserInfoById);
            orgGetUserInfoById.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(orgGetUserInfoById,
                            new HibernateSupportedValidator<GetOrgUserInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getUser(orgGetUserInfoById);
        }catch (Exception e){
            LOGGER.error("查看用户信息异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="wms获取单条用户关联信息",module = "用户管理")
    @RequestMapping(name="wms获取单条用户关联信息",value = "/getUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            UserBO userBO =gson.fromJson(json,UserBO.class);
            userBO.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO, userBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(userBO,
                            new HibernateSupportedValidator<UserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getUserRelevance(userBO);
        }catch (Exception e){
            LOGGER.error("wms获取单条用户信息异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="修改用户关联信息",module = "用户管理")
    @RequestMapping(name="修改用户关联信息",value = "/updateUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> updateUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            UpdateUserBO updateUserBO = gson.fromJson(json, UpdateUserBO.class);
            updateUserBO.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO, updateUserBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(updateUserBO,
                            new HibernateSupportedValidator<UpdateUserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.updateUserRelevance(updateUserBO);
        } catch (Exception e) {
            LOGGER.error("修改用户关联信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="删除用户信息",module = "用户管理")
    @RequestMapping(name="删除用户信息",value = "/deleteUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> deleteUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            OrgDeleteUserBO deleteUserBO = gson.fromJson(json, OrgDeleteUserBO.class);
            deleteUserBO.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO,deleteUserBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(deleteUserBO,
                            new HibernateSupportedValidator<OrgDeleteUserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.deleteUser(deleteUserBO);
        } catch (Exception e) {
            LOGGER.error("删除用户信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="分页获取用户列表",module = "用户管理")
    @RequestMapping(name="分页获取用户列表",value = "/pageUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pageUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            PageUserInfoBO pageUserInfoBO = new PageUserInfoBO();
            if(!ObjectUtils.isEmpty(json)){
                pageUserInfoBO =gson.fromJson(json, PageUserInfoBO.class);
            }
            BeanUtils.copyProperties(operatorInfoBO, pageUserInfoBO);
            pageUserInfoBO.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(pageUserInfoBO,
                            new HibernateSupportedValidator<PageUserInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.pageUserRelevance(pageUserInfoBO);
        } catch (Exception e) {
            LOGGER.error("分页获取用户列表异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="保存用户信息 新增/保存",module = "用户管理")
    @RequestMapping(name="保存用户信息 新增/保存",value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveUser(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            WmsSaveUserBO wmsSaveUserBO=gson.fromJson(json, WmsSaveUserBO.class);
            wmsSaveUserBO.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO,wmsSaveUserBO);
            wmsSaveUserBO.setOperator(partnerInfoBo.getOperatorId());
            wmsSaveUserBO.setOnlineUserId(partnerInfoBo.getOperatorId());
            return userService.saveUser(wmsSaveUserBO);
        } catch (Exception e) {
            LOGGER.error("保存信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="获取公司所有部门",module = "用户管理")
    @RequestMapping(name="获取公司所有部门",value = "/getAllDepartment", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getAllDepartment(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            GetDepartmentBO getDepartmentBO=gson.fromJson(json, GetDepartmentBO.class);
            BeanUtils.copyProperties(operatorInfoBO,getDepartmentBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(getDepartmentBO,
                            new HibernateSupportedValidator<GetDepartmentBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getAllDepartment(getDepartmentBO);
        } catch (Exception e) {
            LOGGER.error("获取公司所有部门信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="获取公司所有职务",module = "用户管理")
    @RequestMapping(name="获取公司所有职务",value = "/getJob", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getJob(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            GetJobBO getJobBO = gson.fromJson(json, GetJobBO.class);
            BeanUtils.copyProperties(operatorInfoBO,getJobBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(getJobBO,
                            new HibernateSupportedValidator<GetJobBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getUjob(getJobBO);
        } catch (Exception e) {
            LOGGER.error("获取公司所有职务异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="获取公司所有职位",module = "用户管理")
    @RequestMapping(name="获取公司所有职位",value = "/getPosition", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getPosition(@RequestBody(required = false) String json, @OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            OrgPositionBO orgPositionBO =gson.fromJson(json, OrgPositionBO.class);
            BeanUtils.copyProperties(operatorInfoBO, orgPositionBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(orgPositionBO,
                            new HibernateSupportedValidator<OrgPositionBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getPosition(orgPositionBO);
        } catch (Exception e) {
            LOGGER.error("获取公司所有职位异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取用户部门职位和所有部门职位职务信息
     * @author  wzy
     * @param json String
     * @param request HttpServletRequest
     * @param operatorInfoBO OperatorInfoBO
     * @return map
     * @date  2018/4/17 10:45
     **/
    @AddLog(value ="获取用户部门职位和所有部门职位职务信息",module = "用户管理")
    @RequestMapping(name="获取用户部门职位和所有部门职位职务信息",value = "/getDepartAndJob", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getDepartAndJob(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            GetOrgUserInfoBO getOrgUserInfoBO = gson.fromJson(json, GetOrgUserInfoBO.class);
            getOrgUserInfoBO.setOperator(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO, getOrgUserInfoBO);
            getOrgUserInfoBO.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(getOrgUserInfoBO,
                            new HibernateSupportedValidator<GetOrgUserInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getDepartAndJob(getOrgUserInfoBO);
        } catch (Exception e) {
            LOGGER.error("获取用户部门职位和所有部门职位职务信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取角色列表，根据id获取或者获取全部
     * @author  wzy
     * @param json String
     * @param request HttpServletRequest
     * @param operatorInfoBO OperatorInfoBO
     * @return  map
     * @date  2018/4/17 9:41
     **/
    @AddLog(value ="获取角色列表",module = "用户管理")
    @RequestMapping(name="获取角色列表",value = "/roleList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> roleList(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            RoleTypeBO roleTypeBO = gson.fromJson(json, RoleTypeBO.class);
            roleTypeBO.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO,roleTypeBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(roleTypeBO,
                            new HibernateSupportedValidator<RoleTypeBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.roleList(roleTypeBO);
        } catch (Exception e) {
            LOGGER.error("获取角色列表异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="获取用户关联仓库信息",module = "用户管理")
    @RequestMapping(name="获取用户关联仓库信息",value = "/getUserWarehouse", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getUserWarehouse(@RequestBody(required = false) String json, HttpServletRequest request,@OperatorAnnotation OperatorInfoBO operatorInfoBO){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            UserBO getUserWarehouse = gson.fromJson(json, UserBO.class);
            getUserWarehouse.setPartnerId(partnerInfoBo.getPartnerId());
            BeanUtils.copyProperties(operatorInfoBO,getUserWarehouse);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(getUserWarehouse,
                            new HibernateSupportedValidator<UserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getUserWarehouse(getUserWarehouse);
        } catch (Exception e) {
            LOGGER.error("获取用户关联仓库信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }
}
