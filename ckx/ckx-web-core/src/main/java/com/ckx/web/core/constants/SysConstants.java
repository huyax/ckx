package com.ckx.web.core.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SysConstants {

    /**
     * Session保存登录用户信息key
     */
    public final static String SESSION_ADMIN_USER = "_SESSION_ADMIN_USER_";

    /**
     * session保存验证码信息key
     */
    public final static String SESSION_KEY_AUTHCODE = "_SESSION_KEY_AUTHCODE_";

    /**
     * 默认用户密码
     */
    public final static String DEFAULT_PASSWORD = "111111";

    /**
     * DEBUG模式
     */
    public static boolean DEBUG = true;

    /**
     * 是否压缩HTML代码
     */
    public static boolean HTML_IS_COMPRESS = false;

    /**
     * 文件上传路径
     */
    public static String UPLOAD_PATH_PARENT;

    /**
     * 超级管理员
     */
    public static final int POST_SUPER_ADMIN = 1;

    public static Map<String, Object> POST_MAPPERS = new HashMap<String, Object>();

    static {
        POST_MAPPERS.put("SUPER_ADMIN", POST_SUPER_ADMIN);
    }

    @Value("${path.upload}")
    public void setUPLOAD_PATH_PARENT(String upload_path_parent) {
        UPLOAD_PATH_PARENT = upload_path_parent;
    }

}
