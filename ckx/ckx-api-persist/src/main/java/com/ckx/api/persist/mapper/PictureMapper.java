package com.ckx.api.persist.mapper;


import com.ckx.api.persist.entity.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> getByMap(int mapId);
}