package com.ckx.web.persist.mapper;

import java.util.List;

import com.ckx.web.persist.entity.SysRoleMenuKey;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(SysRoleMenuKey key);

    int insert(SysRoleMenuKey record);

    int insertSelective(SysRoleMenuKey record);

    List<SysRoleMenuKey> selectByRoleId(Integer roleId);


}
