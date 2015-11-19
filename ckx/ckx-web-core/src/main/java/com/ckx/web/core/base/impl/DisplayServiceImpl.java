package com.ckx.web.core.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cache.Cache;
import org.mybatis.caches.ehcache.EhcacheCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ckx.web.core.base.DisplayService;
import com.ckx.web.persist.entity.SysPosts;
import com.ckx.web.persist.entity.SysRoles;
import com.ckx.web.persist.entity.SysUsers;
import com.ckx.web.persist.mapper.SysPostsMapper;
import com.ckx.web.persist.mapper.SysRolesMapper;
import com.ckx.web.persist.mapper.SysUsersMapper;

@Service
public class DisplayServiceImpl implements DisplayService {

    private String DISPLAY_CACHE_KEY = "DISPLAY_CACHE_KEY";
    private @Autowired SysUsersMapper usersMapper;
    private @Autowired SysRolesMapper rolesMapper;
    private @Autowired SysPostsMapper postsMapper;
 

    /**
     * 获取Cache对象
     * 
     * @author 吴尚云
     * @date 2014-3-4 下午1:16:34
     * @param id
     * @return
     */
    private Cache getCache(String id) {
        return new EhcacheCache(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, String> displayUser() {
        Map<Object, String> result = null;
        // 获取缓存对象
        Cache cache = getCache(SysUsersMapper.class.getName());
        String key = DISPLAY_CACHE_KEY + "_DISPLAY_USER";
        Object cacheResult = cache.getObject(key);
        if (cacheResult != null) {
            result = (Map<Object, String>) cacheResult;
        } else {
            result = new HashMap<Object, String>();
            List<SysUsers> users = usersMapper.getAllUsers();
            for (SysUsers user : users) {
                result.put(user.getUserId(), user.getNick());
            }
            cache.putObject(key, result);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, String> displayRole() {
        Map<Object, String> result = null;
        // 获取缓存对象
        Cache cache = getCache(SysUsersMapper.class.getName());
        String key = DISPLAY_CACHE_KEY + "_DISPLAY_ROLE";
        Object cacheResult = cache.getObject(key);
        if (cacheResult != null) {
            result = (Map<Object, String>) cacheResult;
        } else {
            result = new HashMap<Object, String>();
            List<SysRoles> roles = rolesMapper.getAllRoles();
            for (SysRoles role : roles) {
                result.put(role.getRoleId(), role.getRoleName());
            }
            cache.putObject(key, result);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, String> displayPost() {
        Map<Object, String> result = null;
        // 获取缓存对象
        Cache cache = getCache(SysUsersMapper.class.getName());
        String key = DISPLAY_CACHE_KEY + "_DISPLAY_POST";
        Object cacheResult = cache.getObject(key);
        if (cacheResult != null) {
            result = (Map<Object, String>) cacheResult;
        } else {
            result = new HashMap<Object, String>();
            List<SysPosts> posts = postsMapper.getAllPosts();
            for (SysPosts post : posts) {
                result.put(post.getPostId(), post.getPostName());
            }
            cache.putObject(key, result);
        }
        return result;
    }

   
  
}
