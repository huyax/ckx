package com.ckx.web.persist.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysUsers implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	private Integer				userId;
	
	private String				nick;
	
	private String				username;
	
	private @JsonIgnore String	password;
	
	private String				email;
	
	private Integer				status;
	
	private Date				updateDate;
	
	private Date				createDate;
	
	private List<SysRoles>		roles;
	
	private List<SysMenus>		menus;
	
	private List<SysPosts>		posts;
	
	private String				channleNames;
	
	private String				channelIds;
	
	private Long   qq;
	
	private String telephone;
	
	
	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getUserId() {
		return userId;
	}
	


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick == null ? null : nick.trim();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<SysRoles> getRoles() {
		return roles;
	}
	
	public void setRoles(List<SysRoles> roles) {
		this.roles = roles;
	}
	
	public List<SysMenus> getMenus() {
		return menus;
	}
	
	public void setMenus(List<SysMenus> menus) {
		this.menus = menus;
	}
	
	public List<SysPosts> getPosts() {
		return posts;
	}
	
	public void setPosts(List<SysPosts> posts) {
		this.posts = posts;
	}
	
	/**
	 * 获取用户的角色编号
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:47:56
	 * @return
	 */
	public Integer getRoleId() {
		if (roles != null && roles.size() > 0) {
			return roles.get(0).getRoleId();
		}
		return null;
	}
	
	/**
	 * 获取用户的岗位编号
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午5:44:21
	 * @return
	 */
	public Integer getPostId() {
		if (posts != null && posts.size() > 0) {
			return posts.get(0).getPostId();
		}
		return null;
	}
	
	/**
	 * 获取用户的上级角色
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午5:53:36
	 * @return
	 */
	public Integer getParentRole() {
		if (roles != null && roles.size() > 0) {
			return roles.get(0).getParentRole();
		}
		return null;
	}
	
	public String getChannleNames() {
		return channleNames;
	}
	
	public void setChannleNames(String channleNames) {
		this.channleNames = channleNames;
	}
	
	public String getChannelIds() {
		return channelIds;
	}
	
	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}
	
}
