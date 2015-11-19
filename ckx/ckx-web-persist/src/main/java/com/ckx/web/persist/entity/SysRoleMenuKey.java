package com.ckx.web.persist.entity;

import java.io.Serializable;

public class SysRoleMenuKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer menuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}