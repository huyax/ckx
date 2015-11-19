package com.ckx.web.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ckx.web.persist.entity.SysConfig;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

	SysConfig selectByKey(Map<String, Object> param);

	void updateConfig(Map<String, Object> param);

	List<SysConfig> paginate(Map<String, Object> paramsMap);

	int paginateCount(Map<String, Object> paramsMap);

	int batchDelete(@Param("ids") List<String> idsList);
}