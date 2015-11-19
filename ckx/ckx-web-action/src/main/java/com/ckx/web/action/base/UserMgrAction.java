package com.ckx.web.action.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckx.lang.Pager;
import com.ckx.lang.util.MD5Encrypt;
import com.ckx.web.core.base.PostService;
import com.ckx.web.core.base.RoleService;
import com.ckx.web.core.base.UserService;
import com.ckx.web.core.base.impl.RoleServiceImpl;
import com.ckx.web.core.constants.SysConstants;
import com.ckx.web.persist.entity.SysUserRoleKey;
import com.ckx.web.persist.entity.SysUsers;

@Controller
public class UserMgrAction extends BaseAction {
	
	private @Autowired UserService	userSv;
	private @Autowired PostService	postSv;
	private @Autowired RoleService roleSv;
	
	@RequestMapping(value = "/menus/user_mgr.html", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.put("posts", postSv.getAllPost());
		model.put("allRole", roleSv.getAllRoles());
		return AD_HTML + "base/user_mgr";
	}
	
	@RequestMapping(value = "/user/message.html", method = RequestMethod.GET)
	public String _index(ModelMap model) {
		model.put("user", userSv.getByUserId(getUser().getUserId()));
		return AD_HTML + "base/user_message";
	}
	
	//廖江洪 添加快捷创建用户界面
	@RequestMapping(value = "/menus/create_new_user.html", method = RequestMethod.GET)
	public String index_user(ModelMap model) {
		model.put("posts", postSv.getAllPost());
		@SuppressWarnings("unused")
		SysUsers user = getUser();
		//model.put("showAllPosts", PostUtils.createAllUsers.contains(user.getPostId()));//查看所有用户,除了超级管理员
		model.put("superPost", SysConstants.POST_SUPER_ADMIN);//超级管理员
		
		return AD_HTML + "base/create_new_user";
	}
	
	@ResponseBody
	@RequestMapping(value = "/updpass.html", method = RequestMethod.POST)
	public Object alterPassword(String oldPassword, String newPassword, String entPassword,
								HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(oldPassword)) {
			result.put(ERROR, "旧密码不能为空！");
			return result;
		}
		if (StringUtils.isBlank(newPassword)) {
			result.put(ERROR, "新密码不能为空！");
			return result;
		}
		if (StringUtils.isBlank(entPassword)) {
			result.put(ERROR, "确认密码不能为空！");
			return result;
		}
		if (!newPassword.equals(entPassword)) {
			result.put(ERROR, "两次密码不相同！");
			return result;
		}
		
		SysUsers user = getUser();
		try {
			String oldPass = MD5Encrypt.MD5(oldPassword);
			if (!user.getPassword().equals(oldPass)) {
				result.put(ERROR, "旧密码错误！");
				return result;
			} else {
				user.setPassword(MD5Encrypt.MD5(newPassword));
				if (userSv.updateUser(user)) {
					result.put(RESULT, true);
					result.put(MESSAGE, "密码修改成功！");
				} else {
					result.put(RESULT, false);
					result.put(ERROR, "密码修改失败！");
					return result;
				}
			}
		} catch (Exception e) {
			result.put(RESULT, false);
			result.put(ERROR, "系统异常，修改密码失败！");
			getLog(this).error(e.getMessage(), e);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/list.html", method = RequestMethod.POST)
	public Object list(HttpServletRequest request, ModelMap model) {
		SysUsers user = getUser();
		Pager pager = createPager(request);
		pager.addParam("childRole", RoleServiceImpl.getRoleChild(user.getRoleId()));
		pager.addParam("username", getRequestParams(String.class, request, "username"));
		pager.addParam("status", getRequestParams(String.class, request, "status"));
		pager.addParam("roleId", getRequestParams(Integer.class, request, "roleId"));
		pager.addParam("nick", getRequestParams(String.class, request, "nick"));
		pager.addParam("role", user.getRoleId());
		userSv.getAllUser(pager);
		return getGridData(pager);
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/add.html", method = RequestMethod.POST)
	public Object add(SysUsers user, Integer postId, String roleId, HttpServletRequest request,
						ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (postId == null || roleId == null || StringUtils.isBlank(user.getUsername())) {
				result.put(RESULT, false);
				result.put(MESSAGE, "提交信息不完整！");
			} else if (userSv.getByName(user.getUsername()) != null) {
				result.put(RESULT, false);
				result.put(MESSAGE, "用户已存在！");
			} else if (userSv.addUser(user, postId, roleId) > 0) {
				result.put(RESULT, true);
				result.put(MESSAGE, "新增成功！");
			} else {
				result.put(RESULT, false);
				result.put(MESSAGE, "新增失败！");
			}
		} catch (Exception e) {
			result.put(RESULT, false);
			result.put(MESSAGE, "系统异常，操作失败！");
			getLog(this).error(e.getMessage(), e);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/upd.html", method = RequestMethod.POST)
	public Object upd(SysUsers user, Integer postId, String roleId, HttpServletRequest request,
						ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (user.getUserId() == null || postId == null || roleId == null) {
				result.put(RESULT, false);
				result.put(MESSAGE, "提交数据不完整！");
			} else if (userSv.updateUser(user, postId, roleId)) {
				result.put(RESULT, true);
				result.put(MESSAGE, "修改成功！");
			} else {
				result.put(RESULT, false);
				result.put(MESSAGE, "修改失败！");
			}
		} catch (Exception e) {
			result.put(RESULT, false);
			result.put(MESSAGE, "系统异常，操作失败！");
			getLog(this).error(e.getMessage(), e);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/upd_msg.html", method = RequestMethod.POST)
	public Object updMsg(SysUsers user, HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (user.getUserId() == null) {
				result.put(RESULT, false);
				result.put(MESSAGE, "提交数据不完整！");
			} else if (userSv.updateUser(user)) {
				SysUsers _user = getUser();
				_user.setEmail(user.getEmail());
				_user.setNick(user.getNick());
				result.put(RESULT, true);
				result.put(MESSAGE, "修改成功！");
			} else {
				result.put(RESULT, false);
				result.put(MESSAGE, "修改失败！");
			}
		} catch (Exception e) {
			result.put(RESULT, false);
			result.put(MESSAGE, "系统异常，操作失败！");
			getLog(this).error(e.getMessage(), e);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/del.html", method = RequestMethod.POST)
	public Object del(Integer userId, HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (userId != null && userSv.deleteUser(userId) > 0) {
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
	@RequestMapping(value = "/user/role_checked.html", method = RequestMethod.POST)
	public Object userRoles(SysUserRoleKey userRole, Boolean checked, HttpServletRequest request,
							ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (checked != null && userRole.getRoleId() != null && userRole.getUserId() != null) {
				result.put(RESULT, true);
				userSv.changeUserRole(userRole, checked);
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
	
	@ResponseBody
	@RequestMapping(value = "/user/user_roles.html", method = RequestMethod.POST)
	public Object searchUserRoles(Integer userId, HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (userId != null) {
				result.put(RESULT, true);
				result.put(DATA, userSv.getAllRole(userId));
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
	@RequestMapping(value = "/user/batch_del.html", method = RequestMethod.POST)
	public Object batchDel(String userIds, HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (!StringUtils.isBlank(userIds) && userSv.batchDeleteUser(userIds) > 0) {
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
	
	/**
	 * 密码重置
	 * 
	 * @param userId
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user/resetPassword.html", method = RequestMethod.POST)
	public Object resetPassword(Integer userId, HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		try {
			if (userId != null && userSv.resetPassword(userId)) {
				result.put(RESULT, true);
				result.put(MESSAGE, "密码重置成功！");
			} else {
				result.put(RESULT, false);
				result.put(MESSAGE, "密码重置失败！");
			}
		} catch (Exception e) {
			result.put(RESULT, false);
			result.put(MESSAGE, "系统异常，操作失败！");
			getLog(this).error(e.getMessage(), e);
		}
		return result;
	}
	
}
