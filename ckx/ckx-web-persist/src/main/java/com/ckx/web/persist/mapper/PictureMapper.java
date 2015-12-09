package com.ckx.web.persist.mapper;
import java.util.List;
import java.util.Map;

import com.ckx.web.persist.entity.Picture;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);


	List<Picture> paginate(Map<String, Object> paramsMap);
	int paginateCount(Map<String, Object> paramsMap);
}