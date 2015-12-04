package com.ckx.web.core.base;

import java.util.Map;

public interface DisplayService {

    /**
     * 翻译用户信息
     *
     * @return
     * @author 吴尚云
     * @date 2014-3-4 下午1:09:42
     */
    public Map<Object, String> displayUser();

    /**
     * 翻译角色信息
     *
     * @return
     * @author 吴尚云
     * @date 2014-3-4 下午1:15:41
     */
    public Map<Object, String> displayRole();

    /**
     * 翻译岗位信息
     *
     * @return
     * @author 吴尚云
     * @date 2014-3-4 下午1:15:55
     */
    public Map<Object, String> displayPost();

}
