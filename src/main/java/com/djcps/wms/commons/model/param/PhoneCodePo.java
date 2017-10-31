package com.djcps.wms.commons.model.param;

/**
 * 手机验证码 参数
 * @author Chengw
 * @since 2017/10/31 16:38.
 */
public class PhoneCodePo {

    private String phone;
    private String code;
    private String appSystem;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(String appSystem) {
        this.appSystem = appSystem;
    }
}
