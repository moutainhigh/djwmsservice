package com.djcps.wms.inneruser.model.userparam;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * org删除用户参数类
 * @author:wzy
 * @date:2018/4/13
 **/
public class OrgDeleteUserBO {

    /**
     * 给org的用户id
     */
    @NotBlank
    private String id;

    /**
     * org用户状态
     */
    @NotNull
    private Integer status;

    private String operator;

    @NotBlank
    private String ip;

    @NotBlank
    private String bussion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBussion() {
        return bussion;
    }

    public void setBussion(String bussion) {
        this.bussion = bussion;
    }

    @Override
    public String toString() {
        return "OrgDeleteUserBO{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", operator='" + operator + '\'' +
                ", ip='" + ip + '\'' +
                ", bussion='" + bussion + '\'' +
                '}';
    }
}
