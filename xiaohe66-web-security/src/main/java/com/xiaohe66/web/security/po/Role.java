package com.xiaohe66.web.security.po;

import com.xiaohe66.web.base.base.BasePo;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public class Role extends BasePo {

    private String roleName;
    private String roleDesc;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "{" + "\"roleName\":\"" + roleName + "\""
                + ", \"roleDesc\":\"" + roleDesc + "\""
                + ", \"id\":\"" + id + "\""
                + "}";
    }
}
