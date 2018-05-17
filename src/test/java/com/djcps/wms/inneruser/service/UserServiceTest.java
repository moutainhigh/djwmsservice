package com.djcps.wms.inneruser.service;

import com.dj.com.wms.commons.base.BaseTest;
import com.djcps.wms.inneruser.model.param.UserInfoBO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 单元测试
 * 用户service模块
 * @author Chengw
 * @version 1.0.0
 * @since 2018/5/15 15:04.
 */
public class UserServiceTest extends BaseTest {

    @Resource
    private UserService userService;

    /**
     * 根据角色类型获取用户信息
     */
    @Test
    public void listUserByType() {
        Map<String,Object> result = userService.listUserByType(new UserInfoBO(){{
            setRoleTypeCode(Arrays.asList("01"));
            setPartnerId("400");
        }});

        Assert.assertNotEquals(result,null);
    }
}