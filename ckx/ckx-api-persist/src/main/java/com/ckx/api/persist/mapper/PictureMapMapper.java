package com.ckx.api.persist.mapper;

import com.ckx.api.persist.entity.Location;
import com.ckx.api.persist.entity.PictureMap;

import java.util.List;
import java.util.Map;

public interface PictureMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureMap record);

    int insertSelective(PictureMap record);

    PictureMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureMap record);

    int updateByPrimaryKey(PictureMap record);

    List<PictureMap> paginate(Map<String, Object> paramsMap);

    int paginateCount(Map<String, Object> paramsMap);
}