package com.djcps.wms.inneruser.model.result;

/**
 * 内部用户登出 返回类
 * @author Chengw
 * @since 2017/12/4 15:12.
 */
public class UserLogoutVo{

    /**
     * url
     */
    private String url;

    public UserLogoutVo(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
