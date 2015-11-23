package com.ckx.web.core.location.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ckx.web.core.location.LocationService;
import com.ckx.lang.Pager;
import com.ckx.web.persist.entity.Location;
import com.ckx.web.persist.mapper.LocationMapper;

@Service
public class LocationServiceImpl implements LocationService
{
	private @Autowired LocationMapper	locationMapper;

	@Override
	public void paginate(Pager pager)
	{
		pager.setResult(locationMapper.paginate(pager.getParamsMap()));
		pager.setTotalRecord(locationMapper.paginateCount(pager.getParamsMap()));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean updateByPk(Location location)
	{
		return locationMapper.updateByPrimaryKey(location) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public boolean add(Location location)
	{
		return locationMapper.insert(location) > 0 ? true : false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int batchDelete(String ids)
	{
		List<String> idsList = Arrays.asList(ids.split("\\|"));
		int i = 0;
		for(String id:idsList){
			i += locationMapper.deleteByPrimaryKey(Integer.valueOf(id));
		}
		return i;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public int deleteById(Integer id)
	{
		return locationMapper.deleteByPrimaryKey(id);
	}

}
