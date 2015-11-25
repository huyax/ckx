package com.ckx.web.core.base;

import java.util.List;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.SysRoleMenuKey;
import com.ckx.web.persist.entity.SysRoles;

public interface RoleService {

    /**
     * 查询所有的角色（列表）
     * @author 吴尚云
     * @date 2014-3-3 下午4:07:47
     * @return
     */
    public List<SysRoles> getAllRoles();

    /**
     * 查询所有的角色（分页）
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:10
     * @param pager
     */
    public void getAllRoles(Pager pager);

    /**
     * 添加角色
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:26
     * @param role
     * @return
     */
    public int addRole(SysRoles role);

    /**
     * 修改角色
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:32
     * @param role
     * @return
     */
    public int updRole(SysRoles role);

    /**
     * 删除角色
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:42
     * @param roleId
     * @return
     */
    public int delRole(Integer roleId);

    /**
     * 查询角色对应菜单
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:48
     * @param roleId
     * @return
     */
    public List<SysRoleMenuKey> getRoleMenus(Integer roleId);

    /**
     * 设置角色拥有的菜单
     * @author 吴尚云
     * @date 2014-3-3 下午4:09:34
     * @param roleMenu
     * @param checked
     * @return
     */
    public int changeRoleMenu(SysRoleMenuKey roleMenu, boolean checked);

    /**
     * 查询指定角色下的所有子角色
     * @author 吴尚云
     * @date 2014-3-3 下午5:27:09
     * @param roleId
     * @return
     */
    public List<SysRoles> getRoleByParent(Integer roleId);
    
    /**
     * 廖江红添加通过名字查询对应id
     * 2014-8-28
     */
    public Integer selectIdByName();
    
}
