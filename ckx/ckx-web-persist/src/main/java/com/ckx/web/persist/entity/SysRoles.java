package com.ckx.web.persist.entity;

import java.io.Serializable;

public class SysRoles implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private String roleName;

    private Integer parentRole;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getParentRole() {
        return parentRole;
    }

    public void setParentRole(Integer parentRole) {
        this.parentRole = parentRole;
    }
}