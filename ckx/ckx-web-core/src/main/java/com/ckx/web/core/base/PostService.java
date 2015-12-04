package com.ckx.web.core.base;

import java.util.List;

import com.ckx.web.persist.entity.SysPosts;

public interface PostService {

    /**
     * 查询所有的岗位
     *
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午2:51:04
     */
    public List<SysPosts> getAllPost();
}
