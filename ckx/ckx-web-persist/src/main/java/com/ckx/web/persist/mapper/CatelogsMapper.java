package com.ckx.web.persist.mapper;

import java.util.List;
import java.util.Map;

import com.ckx.web.persist.entity.Catelogs;

public interface CatelogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Catelogs record);

    int insertSelective(Catelogs record);

    Catelogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Catelogs record);

    int updateByPrimaryKey(Catelogs record);

	List<Catelogs> paginate(Map<String, Object> paramsMap);

	int paginateCount(Map<String, Object> paramsMap);
}