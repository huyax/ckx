package com.ckx.web.core.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckx.web.core.base.PostService;
import com.ckx.web.persist.entity.SysPosts;
import com.ckx.web.persist.mapper.SysPostsMapper;

@Service
public class PostServiceImpl implements PostService {

    private
    @Autowired
    SysPostsMapper postDao;

    public List<SysPosts> getAllPost() {
        return postDao.getAllPosts();
    }

}
