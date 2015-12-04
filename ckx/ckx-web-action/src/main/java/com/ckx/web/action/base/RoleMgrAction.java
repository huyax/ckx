package com.ckx.web.action.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ckx.web.core.base.MenuService;
import com.ckx.web.core.base.RoleService;
import com.ckx.web.persist.entity.SysRoleMenuKey;
import com.ckx.web.persist.entity.SysRoles;

@Controller
public class RoleMgrAction extends BaseAction {

    private
    @Autowired
    MenuService menuSv;
    private
    @Autowired
    RoleService roleSv;

    @RequestMapping(value = "/menus/role_mgr.html", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        try {
            model.put("menusJson", JSON.toJSONString(menuSv.getAllMenus()));
            model.put("rolesJson", JSON.toJSONString(roleSv.getAllRoles()));
        } catch (Exception e) {
            getLog(this).error(e.getMessage(), e);
        }
        return AD_HTML + "base/role_mgr";
    }

    @ResponseBody
    @RequestMapping(value = "/role/get_roles.html", method = RequestMethod.POST)
    public Object list(HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            result.put(RESULT, true);
            result.put(DATA, roleSv.getRoleByParent(getUser().getRoleId()));
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，获取数据失败！");
            getLog(this).error("系统异常，获取数据失败！", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/add.html", method = RequestMethod.POST)
    public Object add(SysRoles role, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (role.getParentRole() == null || StringUtils.isBlank(role.getRoleName())) {
                result.put(RESULT, false);
                result.put(MESSAGE, "提交数据无效！");
            } else {
                if (roleSv.addRole(role) > 0) {
                    result.put(RESULT, true);
                    result.put("roleId", role.getRoleId());
                    result.put(MESSAGE, "新增成功！");
                } else {
                    result.put(RESULT, false);
                    result.put(MESSAGE, "新增失败！");
                }
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/upd.html", method = RequestMethod.POST)
    public Object upd(SysRoles role, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (role.getRoleId() == null || StringUtils.isBlank(role.getRoleName())) {
                result.put(RESULT, false);
                result.put(MESSAGE, "修改失败！");
            } else {
                if (roleSv.updRole(role) > 0) {
                    result.put(RESULT, true);
                    result.put(MESSAGE, "修改成功！");
                } else {
                    result.put(RESULT, false);
                    result.put(MESSAGE, "修改失败！");
                }
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/del.html", method = RequestMethod.POST)
    public Object del(Integer roleId, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (roleId != null && roleSv.delRole(roleId) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "删除成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "删除失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/role_menus.html", method = RequestMethod.POST)
    public Object roleMenus(Integer roleId, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (roleId != null) {
                result.put(RESULT, true);
                result.put(DATA, roleSv.getRoleMenus(roleId));
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "参数错误，获取数据失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，获取数据失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/role/menu_checked.html", method = RequestMethod.POST)
    public Object roleMenus(SysRoleMenuKey roleMenu, Boolean checked, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (checked != null && roleMenu.getRoleId() != null && roleMenu.getMenuId() != null) {
                result.put(RESULT, true);
                roleSv.changeRoleMenu(roleMenu, checked);
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "参数错误，获取数据失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，获取数据失败！");
        }
        return result;
    }
}
