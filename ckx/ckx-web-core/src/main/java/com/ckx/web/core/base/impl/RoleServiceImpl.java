package com.ckx.web.core.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.core.base.RoleService;
import com.ckx.web.persist.entity.SysRoleMenuKey;
import com.ckx.web.persist.entity.SysRoles;
import com.ckx.web.persist.mapper.SysRoleMenuMapper;
import com.ckx.web.persist.mapper.SysRolesMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class RoleServiceImpl implements RoleService {

    private
    @Autowired
    SysRolesMapper roleDao;
    private
    @Autowired
    SysRoleMenuMapper roleMenuDao;

    public List<SysRoles> getAllRoles() {
        return roleDao.getAllRoles();
    }

    public void getAllRoles(Pager pager) {
        pager.setResult(roleDao.getRoles(pager));
    }

    public int addRole(SysRoles role) {
        return roleDao.insert(role);
    }

    public int updRole(SysRoles role) {
        return roleDao.updateByPrimaryKey(role);
    }

    public int delRole(Integer roleId) {
        return roleDao.deleteByPrimaryKey(roleId);
    }

    public List<SysRoleMenuKey> getRoleMenus(Integer roleId) {
        return roleMenuDao.selectByRoleId(roleId);
    }

    public int changeRoleMenu(SysRoleMenuKey roleMenu, boolean checked) {
        if (checked) {
            return roleMenuDao.insert(roleMenu);
        } else {
            return roleMenuDao.deleteByPrimaryKey(roleMenu);
        }
    }

    @Override
    public List<SysRoles> getRoleByParent(Integer roleId) {
        return roleDao.selectRoleByParent(getRoleChild(roleId));
    }

    /**
     * 查询角色对应的所有子角色
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午5:07:31
     */
    public static String getRoleChild(int roleId) {
        return BeansManager.getBean(SysRolesMapper.class).getRoleChild(roleId);
    }

    /**
     * 查询角色对应的所有父角色
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午5:07:51
     */
    public static String getRoleParent(int roleId) {
        return BeansManager.getBean(SysRolesMapper.class).getRoleParent(roleId);
    }

    /**
     * 查询角色所有的子父角色
     *
     * @param roleId
     * @return
     * @author 吴尚云
     * @date 2014-3-3 下午5:08:08
     */
    public static String getRoleTree(int roleId) {
        return BeansManager.getBean(SysRolesMapper.class).getRoleTree(roleId);
    }

    @Override
    public Integer selectIdByName() {
        return roleDao.selectIdByName();
    }

}
