package com.ckx.web.persist.entity;

import java.util.Date;

public class SysConfig {
    private Long id;

    private String key;

    private String value;

    private String configId;

    private String remark;

    private String name;

    private Date updateTime;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getConfigId() {
        return configId;
    }

    public String getRemark() {
        return remark;
    }

    public String getName() {
        return name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCreateDate(Date createTime) {
        this.createTime = createTime;
    }

}