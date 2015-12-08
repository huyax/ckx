package com.ckx.api.action.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ckx.lang.mybatis.Pager;
/**
 * Created by 寒远黛 on 2015/12/5.
 */
public class BaseAction {

    public static org.apache.commons.logging.Log log = LogFactory.getLog(BaseAction.class);
    protected final String DATA = "date";
    protected final String ERROR = "error";
    protected final String MESSAGE = "message";

    protected String redirect(String url) {
        return "redirect:" + url;
    }

    protected String forward(String url) {
        return "forward:" + url;
    }



    /**
     * 获取result Map对象
     *
     * @return
     */
    protected Map<String, Object> getResult(boolean isSuccess,Object data) {
        Map<String,Object> result = new HashMap<>(3);
        if(isSuccess){
            result.put(ERROR,0);
            result.put(MESSAGE,"success");
        }else{
            result.put(ERROR,1);
            result.put(MESSAGE,"fail");
        }
        if(data != null){
            result.put(DATA,data);
        }

        return result;
    }


    /**
     * 获取日志输出对象
     *
     * @param object
     * @return
     */
    public Log getLog(Object object) {
        return LogFactory.getLog(object.getClass());
    }


    /**
     * 获取客户端IP地址
     */
    public String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求域名
     *
     * @param request
     * @return
     */
    @Deprecated
    public static String getDomain(HttpServletRequest request) {
        String path = request.getContextPath();
        String domain = request.getScheme() + "://";
        domain += request.getServerName();
        domain += request.getServerPort() == 80 ? "" : (":" + request.getServerPort());
        domain += path;
        return domain;
    }

}
