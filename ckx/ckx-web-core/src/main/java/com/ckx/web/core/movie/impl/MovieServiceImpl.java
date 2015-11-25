package com.ckx.web.core.movie.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.core.movie.MovieService;
import com.ckx.web.persist.entity.Movie;
import com.ckx.web.persist.mapper.MovieMapper;

@Service
public class MovieServiceImpl implements MovieService
{
	private @Autowired MovieMapper	movieMapper;

	@Override
	public void paginate(Pager pager)
	{
		pager.setResult(movieMapper.paginate(pager.getParamsMap()));
		pager.setTotalRecord(movieMapper.paginateCount(pager.getParamsMap()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean updateByPk(Movie movie)
	{
		return movieMapper.updateByPrimaryKey(movie) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean add(Movie movie)
	{
		return movieMapper.insert(movie) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int batchDelete(String ids)
	{
		List<String> idsList = Arrays.asList(ids.split("\\|"));
		int i = 0;
		for(String id:idsList){
			i += movieMapper.deleteByPrimaryKey(Integer.valueOf(id));
		}
		return i;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int deleteById(Integer id)
	{
		return movieMapper.deleteByPrimaryKey(id);
	}

}
