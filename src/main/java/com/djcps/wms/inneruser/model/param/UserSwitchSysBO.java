package com.djcps.wms.inneruser.model.param;

import com.djcps.wms.commons.base.BaseBO;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 用户切换系统 参数类
 * @author Chengw
 * @since 2017/12/5 16:17.
 */
public class UserSwitchSysBO extends BaseBO implements Serializable{

    /**
     * 用户在线token
     */
    private String oldToken;

    /**
     * 系统名称
     */
    @NotBlank
    private String sys;

    public String getOldToken() {
        return oldToken;
    }

    public void setOldToken(String oldToken) {
        this.oldToken = oldToken;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }
}
