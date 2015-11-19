package com.ckx.web.persist.mapper;

import java.util.List;

import com.ckx.web.persist.entity.SysUserRoleKey;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);

    List<SysUserRoleKey> selectByUserId(int userId);

    int deleteByUserId(Integer userId);
}
