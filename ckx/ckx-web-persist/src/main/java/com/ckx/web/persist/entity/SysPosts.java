package com.ckx.web.persist.entity;

import java.io.Serializable;

public class SysPosts implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer postId;

    private String postName;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }
}