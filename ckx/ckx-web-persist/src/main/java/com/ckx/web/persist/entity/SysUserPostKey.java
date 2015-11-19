package com.ckx.web.persist.entity;

import java.io.Serializable;

public class SysUserPostKey implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer postId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}