package com.ckx.web.action.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckx.web.core.constants.SysConstants;
import com.ckx.web.persist.entity.SysUsers;

@Controller
public class LoginAction extends BaseAction {
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(HttpServletRequest request, ModelMap model) {
		return AD_HTML + "login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String _login() {
		return AD_HTML + "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public Object _login(String username, String password, String authcode, Boolean rememberMe,
							HttpServletRequest request, ModelMap model) {
		Map<String, Object> result = getResultMap();
		if (StringUtils.isBlank(username)) {
			result.put(ERROR, "用户名不能为空！");
			return result;
		}
		if (StringUtils.isBlank(password)) {
			result.put(ERROR, "密码不能为空！");
			return result;
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(rememberMe == null ? false : rememberMe);
		try {
			if (subject.isAuthenticated()) {// 重复登录
				SysUsers user = getUser();
				if (!user.getUsername().equals(username)) {// 如果登录名不同
					subject.logout();
					subject.login(token);
				}
			} else {
				subject.login(token);
			}
			result.put(RESULT, true);
		} catch (AuthenticationException e) {
			result.put(ERROR, "用户名或密码错误！");
			token.clear();
			getLog(this).error("登录失败错误信息:" + e);
		} catch (NullPointerException e) {
			result.put(ERROR, "用户未分配岗位！");
			token.clear();
			getLog(this).error("登录失败错误信息:" + e);
		}
		return result;
	}
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public void logout(HttpServletResponse response) {
		SecurityUtils.getSubject().logout();
		try {
			response.sendRedirect(SysConstants.UPLOAD_PATH_PARENT + "/login.html");
		} catch (IOException e) {
			log.error("", e);
		}
	}
	
	@RequestMapping(value = "/main.html", method = RequestMethod.GET)
	public String main(HttpServletRequest request, ModelMap model) {
		SysUsers user = getUser();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		model.put("sysDate", format.format(Calendar.getInstance().getTime()));
		model.put("menus", user.getMenus());
		model.put("nick", user.getNick());
		
		return AD_HTML + "main";
	}
}
