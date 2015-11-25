package com.ckx.web.core.base.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.core.base.SysConfigService;
import com.ckx.web.persist.entity.SysConfig;
import com.ckx.web.persist.mapper.SysConfigMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class SysConfigServiceImpl implements SysConfigService
{
	private @Autowired SysConfigMapper	configMapper;

	@Override
	public SysConfig getConfigByParam(Map<String, Object> param)
	{
		return configMapper.selectByKey(param);
	}

	@Override
	public void updateConfig(Map<String, Object> param)
	{
		configMapper.updateConfig(param);
	}

	@Override
	public void paginate(Pager pager)
	{
		pager.setResult(configMapper.paginate(pager.getParamsMap()));
		pager.setTotalRecord(configMapper.paginateCount(pager.getParamsMap()));
	}

	@Override
	public boolean updateByPk(SysConfig config)
	{
		return configMapper.updateByPrimaryKey(config) > 0 ? true : false;
	}

	@Override
	public boolean addConfig(SysConfig config)
	{
		return configMapper.insert(config) > 0 ? true : false;
	}

	@Override
	public int batchDelete(String ids)
	{
		List<String> idsList = Arrays.asList(ids.split("\\|"));
		return configMapper.batchDelete(idsList);
	}

	@Override
	public int delete(Integer id)
	{
		return configMapper.deleteByPrimaryKey(id);
	}

}
