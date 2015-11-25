package com.ckx.web.core.base;

import java.util.List;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.SysUserRoleKey;
import com.ckx.web.persist.entity.SysUsers;

public interface UserService {
	
	/**
	 * 新增用户，同时分配角色和岗位
	 * 
	 * @param user 用户
	 * @param postId 岗位编号
	 * @param roleId 角色编号
	 * @return
	 */
	public int addUser(SysUsers user, Integer postId, String roleId);
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param username 用户名称
	 * @return
	 */
	public SysUsers getByName(String username);
	
	/**
	 * 根据用户编号查询用户
	 * 
	 * @param userId 用户编号
	 * @return
	 */
	public SysUsers getByUserId(Integer userId);
	
	/**
	 * 修改用户信息
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:10:55
	 * @param user 用户
	 * @return
	 */
	public boolean updateUser(SysUsers user);
	
	/**
	 * 修改用户角色和岗位
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:11:02
	 * @param user 用户
	 * @param postId 岗位编号
	 * @param roleId 角色编号
	 * @return
	 */
	public boolean updateUser(SysUsers user, Integer postId, String roleId);
	
	/**
	 * 查询所有用户（分页）
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:14:19
	 * @param pager
	 */
	public void getAllUser(Pager pager);
	
	/**
	 * 删除指定编号用户
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:14:33
	 * @param userId
	 * @return
	 */
	public int deleteUser(Integer userId);
	
	/**
	 * 分配用户角色
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:14:49
	 * @param userRole
	 * @param checked
	 * @return
	 */
	public int changeUserRole(SysUserRoleKey userRole, Boolean checked);
	
	/**
	 * 查询用户所拥有的角色
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:15:19
	 * @param userId
	 * @return
	 */
	public List<SysUserRoleKey> getAllRole(Integer userId);
	
	/**
	 * 批量删除用户
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午4:17:22
	 * @param userIds 用户编号，多个帐号用‘|’分割
	 * @return
	 */
	public int batchDeleteUser(String userIds);
	
	/**
	 * 重置密码
	 * 
	 * @author 吴尚云
	 * @date 2014-3-3 下午2:49:53
	 * @param userId 用户编号
	 * @return
	 */
	boolean resetPassword(Integer userId);
	
	/**
	 * 查询指定角色下的所有用户
	 * @author 吴尚云
	 * @date 2014-3-4 下午4:03:26
	 * @param roleId
	 * @return
	 */
	public List<SysUsers> selectRoleChild(Integer roleId);
	
  public int addNewUser(SysUsers user, Integer postId, Integer roleId,String pwkey);
}
