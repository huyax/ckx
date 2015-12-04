package com.ckx.web.core.base.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ckx.lang.mybatis.Pager;
import com.ckx.lang.util.MD5Encrypt;
import com.ckx.web.core.base.UserService;
import com.ckx.web.core.constants.SysConstants;
import com.ckx.web.persist.entity.SysUserPostKey;
import com.ckx.web.persist.entity.SysUserRoleKey;
import com.ckx.web.persist.entity.SysUsers;
import com.ckx.web.persist.mapper.SysUserPostMapper;
import com.ckx.web.persist.mapper.SysUserRoleMapper;
import com.ckx.web.persist.mapper.SysUsersMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUsersMapper userDao;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    public int addUser(SysUsers user, Integer postId, String roleId) {
        // 设置默认密码
        user.setPassword(MD5Encrypt.MD5(SysConstants.DEFAULT_PASSWORD));
        user.setCreateDate(new Date());
        userDao.insert(user);
        String[] strs = roleId.split(",");
        for (String rid : strs) {
            // 分配角色
            SysUserRoleKey userRole = new SysUserRoleKey();
            userRole.setRoleId(Integer.parseInt(rid));
            userRole.setUserId(user.getUserId());
            userRoleMapper.insert(userRole);
        }

        // 分配岗位
        SysUserPostKey userPost = new SysUserPostKey();
        userPost.setPostId(postId);
        userPost.setUserId(user.getUserId());
        userPostMapper.insert(userPost);
        return 1;
    }

    public int addNewUser(SysUsers user, Integer postId, Integer roleId, String pwkey) {
        // 设置默认密码
        user.setPassword(MD5Encrypt.MD5(pwkey));
        user.setCreateDate(new Date());
        userDao.insert(user);
        // 分配角色
        SysUserRoleKey userRole = new SysUserRoleKey();
        userRole.setRoleId(roleId);
        userRole.setUserId(user.getUserId());
        userRoleMapper.insert(userRole);
        // 分配岗位
        if (postId != null) {
            SysUserPostKey userPost = new SysUserPostKey();
            userPost.setPostId(postId);
            userPost.setUserId(user.getUserId());
            userPostMapper.insert(userPost);
        }
        return 1;
    }

    public SysUsers getByName(String username) {
        return userDao.selectByUsername(username);
    }

    public SysUsers getByUserId(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean updateUser(SysUsers user) {
        if (user.getPassword() == null) {
            user.setUpdateDate(new Date());
        }
        int r = userDao.updateByPrimaryKeySelective(user);
        return r > 0;
    }

    public boolean updateUser(SysUsers user, Integer postId, String roleId) {
        userRoleMapper.deleteByUserId(user.getUserId());
        userPostMapper.deleteByUserId(user.getUserId());

        // 分配角色
        String[] strs = roleId.split(",");
        for (String rid : strs) {
            // 分配角色
            SysUserRoleKey userRole = new SysUserRoleKey();
            userRole.setRoleId(Integer.parseInt(rid));
            userRole.setUserId(user.getUserId());
            userRoleMapper.insert(userRole);
        }
//		SysUserRoleKey userRole = new SysUserRoleKey();
//		userRole.setRoleId(roleId);
//		userRole.setUserId(user.getUserId());
//		userRoleMapper.insert(userRole);
        // 分配岗位
        SysUserPostKey userPost = new SysUserPostKey();
        userPost.setPostId(postId);
        userPost.setUserId(user.getUserId());
        userPostMapper.insert(userPost);

        user.setUpdateDate(new Date());
        int r = userDao.updateByPrimaryKeySelective(user);
        return r > 0;
    }

    public void getAllUser(Pager pager) {
        pager.setResult(userDao.selectPager(pager.getParamsMap()));
        pager.setTotalRecord(userDao.selectPagerCount(pager.getParamsMap()));
    }

    public int deleteUser(Integer userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    public int batchDeleteUser(String userIds) {
        List<String> ids = Arrays.asList(userIds.split("\\|"));
        return userDao.batchDeleteUser(ids);
    }

    public int changeUserRole(SysUserRoleKey userRole, Boolean checked) {
        if (checked) {
            return userRoleMapper.insert(userRole);
        } else {
            return userRoleMapper.deleteByPrimaryKey(userRole);
        }
    }

    public List<SysUserRoleKey> getAllRole(Integer userId) {
        return userRoleMapper.selectByUserId(userId);
    }

    public boolean resetPassword(Integer userId) {
        SysUsers user = new SysUsers();
        user.setUserId(userId);
        user.setPassword(MD5Encrypt.MD5(SysConstants.DEFAULT_PASSWORD));
        // user.setUpdateDate(new Date());
        int count = userDao.updateByPrimaryKeySelective(user);
        return count == 1 ? true : false;
    }

    @Override
    public List<SysUsers> selectRoleChild(Integer roleId) {
        return userDao.selectRoleChild(RoleServiceImpl.getRoleChild(roleId), roleId);
    }
}
