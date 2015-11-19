package com.ckx.web.persist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ckx.web.persist.entity.SysUsers;

public interface SysUsersMapper {
	int deleteByPrimaryKey(Integer userId);
	
	int batchDeleteUser(@Param("ids") List<String> ids);
	
	int insert(SysUsers record);
	
	int insertSelective(SysUsers record);
	
	SysUsers selectByPrimaryKey(Integer userId);
	
	int updateByPrimaryKeySelective(SysUsers record);
	
	int updateByPrimaryKey(SysUsers record);
	
	SysUsers selectByUsername(String username);
	
	List<SysUsers> selectByParams(Map<String, Object> params);
	
	List<SysUsers> selectPager(Map<String, Object> paramsMap);
	
	int selectPagerCount(Map<String, Object> paramsMap);
	
	List<SysUsers> getAllUsers();
	
	List<SysUsers> selectRoleChild(@Param("childRole") String childRole,
									@Param("roleId") Integer roleId);
}
