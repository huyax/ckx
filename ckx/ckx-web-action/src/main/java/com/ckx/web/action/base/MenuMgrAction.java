package com.ckx.web.action.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ckx.web.core.base.MenuService;
import com.ckx.web.persist.entity.SysMenus;

@Controller
public class MenuMgrAction extends BaseAction {

    private @Autowired MenuService menuSv;

    @RequestMapping(value = "/menus/menu_mgr.html", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        model.put("menus", menuSv.getAllMenus());
        try {
            model.put("menusJson", JSON.toJSONString(model.get("menus")));
        } catch (Exception e) {
            getLog(this).error(e.getMessage(), e);
        }
        return AD_HTML + "base/menu_mgr";
    }

    @ResponseBody
    @RequestMapping(value = "/menu/add.html", method = RequestMethod.POST)
    public Object addMenu(SysMenus menu, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
        
            if (menuSv.insertMenu(menu) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "新增成功！");
                result.put("menuId", menu.getMenuId());
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "新增失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常处理失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/upd.html", method = RequestMethod.POST)
    public Object updMenu(SysMenus menu, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (menu.getMenuId() != null && menuSv.updateMenu(menu) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "更新成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "更新失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常处理失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/del.html", method = RequestMethod.POST)
    public Object delMenu(Integer menuId, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (menuId != null && menuSv.deleteMenu(menuId) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "删除成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "删除失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常处理失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }
}
