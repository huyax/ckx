package com.ckx.web.core.location.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.constants.Constants;
import com.ckx.lang.mybatis.Pager;
import com.ckx.lang.util.DateUtil;
import com.ckx.web.core.location.LocationService;
import com.ckx.web.core.redis.RedisClient;
import com.ckx.web.persist.entity.Location;
import com.ckx.web.persist.mapper.LocationMapper;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
    private
    @Autowired
    LocationMapper locationMapper;
    private
    @Autowired
    RedisClient redis;

    @Override
    public void paginate(Pager pager) {
        pager.setResult(locationMapper.paginate(pager.getParamsMap()));
        pager.setTotalRecord(locationMapper.paginateCount(pager.getParamsMap()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public boolean updateByPk(Location location) {
        return locationMapper.updateByPrimaryKey(location) > 0 ? true : false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public boolean add(Location location) {
        return locationMapper.insert(location) > 0 ? true : false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public int batchDelete(String ids) {
        List<String> idsList = Arrays.asList(ids.split("\\|"));
        int i = 0;
        for (String id : idsList) {
            i += locationMapper.deleteByPrimaryKey(Integer.valueOf(id));
        }
        return i;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
    public int deleteById(Integer id) {
        return locationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void scanQueue() {
        Location vo = new Location();
        while (true) {
            String loc = (String) redis.bRPop(Constants.REDIS_LIST_LOC, 0);
            String[] attrs = loc.split("\\|");
            try {
                vo.setId(null);
                vo.setUserId(Integer.parseInt(attrs[0]));
                vo.setLongitude(attrs[1]);
                vo.setLatitude(attrs[2]);
                vo.setLocTime(DateUtil.paseDatetime(attrs[3]));
                vo.setCreateTime(vo.getLocTime());
                vo.setLocation(vo.getLongitude() + "," + vo.getLatitude());
                locationMapper.insert(vo);
            } catch (Exception e) {
                continue;
            }
        }
    }
}
