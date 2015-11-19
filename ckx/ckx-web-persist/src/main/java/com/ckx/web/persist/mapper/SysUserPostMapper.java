package com.ckx.web.persist.mapper;

import com.ckx.web.persist.entity.SysUserPostKey;

public interface SysUserPostMapper {
    int deleteByPrimaryKey(SysUserPostKey key);

    int insert(SysUserPostKey record);

    int insertSelective(SysUserPostKey record);

    int deleteByUserId(Integer userId);
}
