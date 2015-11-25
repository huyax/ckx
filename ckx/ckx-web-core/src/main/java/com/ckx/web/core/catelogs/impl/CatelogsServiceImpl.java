package com.ckx.web.core.catelogs.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.core.catelogs.CatelogsService;
import com.ckx.web.persist.entity.Catelogs;
import com.ckx.web.persist.mapper.CatelogsMapper;

@Service
public class CatelogsServiceImpl implements CatelogsService
{
	private @Autowired CatelogsMapper	catelogsMapper;

	@Override
	public void paginate(Pager pager)
	{
		pager.setResult(catelogsMapper.paginate(pager.getParamsMap()));
		pager.setTotalRecord(catelogsMapper.paginateCount(pager.getParamsMap()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean updateByPk(Catelogs catelogs)
	{
		return catelogsMapper.updateByPrimaryKey(catelogs) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean add(Catelogs catelogs)
	{
		return catelogsMapper.insert(catelogs) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int batchDelete(String ids)
	{
		List<String> idsList = Arrays.asList(ids.split("\\|"));
		int i = 0;
		for(String id:idsList){
			i += catelogsMapper.deleteByPrimaryKey(Integer.valueOf(id));
		}
		return i;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int deleteById(Integer id)
	{
		return catelogsMapper.deleteByPrimaryKey(id);
	}

}
