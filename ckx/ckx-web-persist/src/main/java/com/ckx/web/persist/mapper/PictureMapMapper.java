package com.ckx.web.persist.mapper;

import com.ckx.web.persist.entity.PictureMap;

public interface PictureMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureMap record);

    int insertSelective(PictureMap record);

    PictureMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureMap record);

    int updateByPrimaryKey(PictureMap record);
}