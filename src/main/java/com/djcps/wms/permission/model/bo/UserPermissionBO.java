package com.djcps.wms.permission.model.bo;

import com.djcps.wms.commons.model.OperatorInfoBO;
import com.djcps.wms.permission.constants.PermissionConstants;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户权限 参数类
 * @author Chengw
 * @create 2018/4/23 14:33.
 * @since 1.0.0
 */
public class UserPermissionBO extends OperatorInfoBO {

    /**
     * 用户id
     */
    @NotBlank
    private String id;

    /**
     * 业务 目前固定 30
     */
    private String pbusiness;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpBusiness() {
        return pbusiness;
    }

    public void setpBusiness(String pbusiness) {
        this.pbusiness = pbusiness;
    }

    @Override
    public String toString() {
        return "UserPermissionBO{" +
                "id='" + id + '\'' +
                ", business='" + pbusiness + '\'' +
                '}';
    }
}
