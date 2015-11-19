package com.ckx.web.persist.entity;

import java.io.Serializable;
import java.util.List;

public class SysMenus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer menuId;

    private String menuName;

    private String showName;

    private String remark;

    private Boolean isParent;

    private Integer parentId;

    private String url;

    private Integer status;

    private Integer sort;

    private List<SysMenus> childrens;
    

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SysMenus> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<SysMenus> childrens) {
        this.childrens = childrens;
    }
	

}
