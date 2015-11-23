package com.ckx.web.persist.mapper;

import java.util.List;
import java.util.Map;

import com.ckx.web.persist.entity.Location;

public interface LocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);

	List<Location> paginate(Map<String, Object> paramsMap);

	int paginateCount(Map<String, Object> paramsMap);
}