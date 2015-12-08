package com.ckx.api.persist.entity;

public class PictureMap {
    private Integer id;

    private String name;

    private Integer count;

    private String url;

    private Integer catelogs;

    private String context;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getCatelogs() {
        return catelogs;
    }

    public void setCatelogs(Integer catelogs) {
        this.catelogs = catelogs;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}