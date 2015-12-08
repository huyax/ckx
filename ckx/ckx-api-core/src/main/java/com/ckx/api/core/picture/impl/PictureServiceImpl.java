package com.ckx.api.core.picture.impl;

import com.ckx.api.core.picture.PictureService;
import com.ckx.api.persist.entity.PictureMap;
import com.ckx.api.persist.mapper.PictureMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2015/12/5.
 */

@Service
public class PictureServiceImpl implements PictureService{

    private @Autowired
    PictureMapMapper pictureMapMapper;

    @Override
    public List<PictureMap> albumList(int page, int size) {
        int start = (page - 1) * size;
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("start",start);
        params.put("size",size);
        return pictureMapMapper.paginate(params);
    }
}
