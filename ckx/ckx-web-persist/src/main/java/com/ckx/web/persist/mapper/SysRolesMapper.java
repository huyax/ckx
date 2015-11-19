package com.ckx.web.persist.mapper;

import java.util.List;

import com.ckx.lang.Pager;
import com.ckx.web.persist.entity.SysRoles;

public interface SysRolesMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRoles record);

    int insertSelective(SysRoles record);

    SysRoles selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRoles record);

    int updateByPrimaryKey(SysRoles record);

    List<SysRoles> getRoles(Pager pager);

    List<SysRoles> getAllRoles();

    String getRoleTree(Integer roleId);

    String getRoleChild(Integer roleId);

    String getRoleParent(Integer roleId);

    List<SysRoles> selectRoleByParent(String childRole);
    
    Integer selectIdByName();
    
}
