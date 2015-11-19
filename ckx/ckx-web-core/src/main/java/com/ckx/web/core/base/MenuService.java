package com.ckx.web.core.base;

import java.util.List;

import com.ckx.web.persist.entity.SysMenus;

public interface MenuService {

    /**
     * 查询所有的菜单
     */
    public List<SysMenus> getAllMenus();

    /**
     * 修改菜单
     */
    public int updateMenu(SysMenus menu);

    /**
     * 添加菜单
     */
    public int insertMenu(SysMenus menu);

    /**
     * 删除菜单
     */
    public int deleteMenu(int menuId);

}
