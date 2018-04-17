package com.djcps.wms.inneruser.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.log.AddLog;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.inneruser.model.userparam.*;
import com.djcps.wms.inneruser.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

/**
 * 用户管理
 * @author:wzy
 * @date:2018/4/12
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(UserController.class);

    private Gson gson = new Gson();

    @Autowired
    private UserService userService;

    /**
     * 查看用户信息
     * @author  wzy
     * @param json
     * @return http
     * @date  2018/4/12 15:21
     **/
    @AddLog(value ="org获取单条用户信息",module = "用户管理")
    @RequestMapping(name="org获取单条用户信息",value = "/getUser", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getUser(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            OrgGetUserInfoById orgGetUserInfoById=gson.fromJson(json,OrgGetUserInfoById.class);
            orgGetUserInfoById.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(orgGetUserInfoById,
                            new HibernateSupportedValidator<OrgGetUserInfoById>()
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
    public Map<String, Object> getUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            DeleteUserBO deleteUserBO=gson.fromJson(json,DeleteUserBO.class);
            deleteUserBO.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(deleteUserBO,
                            new HibernateSupportedValidator<DeleteUserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getUserRelevance(deleteUserBO);
        }catch (Exception e){
            LOGGER.error("wms获取单条用户信息异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }


    @AddLog(value ="修改用户工作状态和仓库等信息",module = "用户管理")
    @RequestMapping(name="修改用户工作状态和仓库等信息",value = "/updateUserStatus", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> updateUserStatus(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            UpdateUserStatusBO updateUserStatusBO = gson.fromJson(json, UpdateUserStatusBO.class);
            updateUserStatusBO.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(updateUserStatusBO,
                            new HibernateSupportedValidator<UpdateUserStatusBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.updateUserStatus(updateUserStatusBO);
        } catch (Exception e) {
            LOGGER.error("修改用户关联信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }


    @AddLog(value ="删除用户信息",module = "用户管理")
    @RequestMapping(name="删除用户信息",value = "/deleteUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> deleteUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            OrgDeleteUserBO deleteUserBO = gson.fromJson(json, OrgDeleteUserBO.class);
            deleteUserBO.setOperator(partnerInfoBo.getPartnerId());
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
    @RequestMapping(name="分页获取用户列表",value = "/pageGetUserRelevance", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pageGetUserRelevance(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            PageGetUserBO pageGetUserBO = gson.fromJson(json, PageGetUserBO.class);
            pageGetUserBO.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(pageGetUserBO,
                            new HibernateSupportedValidator<PageGetUserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.pageGetUserRelevance(pageGetUserBO);
        } catch (Exception e) {
            LOGGER.error("分页获取用户列表异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="保存用户信息 新增/保存",module = "用户管理")
    @RequestMapping(name="保存用户信息 新增/保存",value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveUser(@RequestBody(required = false) String json, HttpServletRequest request) {
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            SaveUserBO saveUserBO = gson.fromJson(json, SaveUserBO.class);
            saveUserBO.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(saveUserBO,
                            new HibernateSupportedValidator<SaveUserBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.saveUser(saveUserBO);
        } catch (Exception e) {
            LOGGER.error("保存信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @AddLog(value ="获取公司所有部门",module = "用户管理")
    @RequestMapping(name="获取公司所有部门，未使用",value = "/getAllDepartment", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getAllDepartment(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            OrgGetDepartmentBO orgGetDepartmentBO = gson.fromJson(json, OrgGetDepartmentBO.class);
            orgGetDepartmentBO.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(orgGetDepartmentBO,
                            new HibernateSupportedValidator<OrgGetDepartmentBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getAllDepartment(orgGetDepartmentBO);
        } catch (Exception e) {
            LOGGER.error("获取公司所有部门信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取用户部门职位和所有部门职位职务信息
     * @author  wzy
     * @param json
     * @return map
     * @date  2018/4/17 10:45
     **/
    @AddLog(value ="获取用户部门职位和所有部门职位职务信息",module = "用户管理")
    @RequestMapping(name="获取用户部门职位和所有部门职位职务信息",value = "/getDepartAndJob", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getDepartAndJob(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            OrgGetUserInfoById orgGetUserInfoById = gson.fromJson(json, OrgGetUserInfoById.class);
            orgGetUserInfoById.setOperator(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(orgGetUserInfoById,
                            new HibernateSupportedValidator<OrgGetUserInfoById>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return userService.getDepartAndJob(orgGetUserInfoById);
        } catch (Exception e) {
            LOGGER.error("获取用户部门职位和所有部门职位职务信息异常：{} ", e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }


    /**
     * 获取角色列表，根据id获取或者获取全部
     * @author  wzy
     * @param json
     * @return  map
     * @date  2018/4/17 9:41
     **/
    @AddLog(value ="获取角色列表",module = "用户管理")
    @RequestMapping(name="获取角色列表",value = "/roleList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> roleList(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            RoleTypeBO roleTypeBO = gson.fromJson(json, RoleTypeBO.class);
            roleTypeBO.setPartnerId(partnerInfoBo.getPartnerId());
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
    @RequestMapping(name="获取用户关联仓库信息",value = "/roleList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getUserWarehouse(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo = (PartnerInfoBO) request.getAttribute("partnerInfo");
            DeleteUserBO getUserWarehouse = gson.fromJson(json, DeleteUserBO.class);
            getUserWarehouse.setPartnerId(partnerInfoBo.getPartnerId());
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(getUserWarehouse,
                            new HibernateSupportedValidator<DeleteUserBO>()
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
