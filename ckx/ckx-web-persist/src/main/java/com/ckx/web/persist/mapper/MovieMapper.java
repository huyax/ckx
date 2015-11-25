package com.ckx.web.persist.mapper;

import java.util.List;
import java.util.Map;

import com.ckx.web.persist.entity.Movie;

public interface MovieMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKeyWithBLOBs(Movie record);

    int updateByPrimaryKey(Movie record);

	List<Movie> paginate(Map<String, Object> paramsMap);

	int paginateCount(Map<String, Object> paramsMap);

	List<Movie> listAll();
}