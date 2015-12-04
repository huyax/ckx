package com.ckx.web.core.base;

import java.util.List;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.SysRoleMenuKey;
import com.ckx.web.persist.entity.SysRoles;

public interface RoleService {

    /**
     * 查询所有的角色（列表）
     *
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:07:47
     */
    public List<SysRoles> getAllRoles();

    /**
     * 查询所有的角色（分页）
     *
     * @param pager
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:10
     */
    public void getAllRoles(Pager pager);

    /**
     * 添加角色
     *
     * @param role
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:26
     */
    public int addRole(SysRoles role);

    /**
     * 修改角色
     *
     * @param role
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:32
     */
    public int updRole(SysRoles role);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:42
     */
    public int delRole(Integer roleId);

    /**
     * 查询角色对应菜单
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:08:48
     */
    public List<SysRoleMenuKey> getRoleMenus(Integer roleId);

    /**
     * 设置角色拥有的菜单
     *
     * @param roleMenu
     * @param checked
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午4:09:34
     */
    public int changeRoleMenu(SysRoleMenuKey roleMenu, boolean checked);

    /**
     * 查询指定角色下的所有子角色
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午5:27:09
     */
    public List<SysRoles> getRoleByParent(Integer roleId);

    /**
     * 廖江红添加通过名字查询对应id
     * 2014-8-28
     */
    public Integer selectIdByName();

}
