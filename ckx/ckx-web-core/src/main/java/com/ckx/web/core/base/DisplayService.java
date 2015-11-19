package com.ckx.web.core.base;

import java.util.Map;

public interface DisplayService {

	/**
	 * 翻译用户信息
	 * 
	 * @author 吴尚云
	 * @date 2014-3-4 下午1:09:42
	 * @return
	 */
	public Map<Object, String> displayUser();

	/**
	 * 翻译角色信息
	 * 
	 * @author 吴尚云
	 * @date 2014-3-4 下午1:15:41
	 * @return
	 */
	public Map<Object, String> displayRole();

	/**
	 * 翻译岗位信息
	 * 
	 * @author 吴尚云
	 * @date 2014-3-4 下午1:15:55
	 * @return
	 */
	public Map<Object, String> displayPost();

}
