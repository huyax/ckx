package com.ckx.api.core.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckx.api.core.location.LocationService;
import com.ckx.api.core.redis.RedisClient;
import com.ckx.lang.constants.Constants;
import com.ckx.lang.util.DateUtil;

@Service
public class LocationServiceImpl implements LocationService
{
	//private @Autowired LocationMapper	locationMapper;
	
	private @Autowired RedisClient redis;

	@Override
	public boolean post(Integer uid, String longitude, String latitude)
	{
		/*Location loc = new Location();
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		loc.setLocation(longitude+ "," + latitude);
		loc.setUserId(uid);
		loc.setCreateTime(new Date());
		loc.setLocTime(loc.getCreateTime());
		return locationMapper.insert(loc) > 0 ? true : false;*/
		
		String location = uid + "|" + longitude + "|" + latitude + "|" + DateUtil.getDatetime();
		return redis.lPush(Constants.REDIS_LIST_LOC, location) > 0 ? true : false;
	}

}
