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

public class BaseAction {

    public static org.apache.commons.logging.Log log = LogFactory.getLog(BaseAction.class);
    protected final String DATA = "d";
    protected final String E = "e";
    protected final String R = "r";
    protected final String M = "m";

    private int defaultPageSize = 40;

    protected String redirect(String url) {
        return "redirect:" + url;
    }

    protected String forward(String url) {
        return "forward:" + url;
    }

    /**
     * 创建分页对象
     *
     * @param pageSize
     * @param pageNo
     * @return
     */
    public Pager createPager(int pageSize, int pageNo) {
        Pager pager = null;
        if (pageSize <= 0) {
            pageSize = defaultPageSize;
        }
        if (pageNo <= 0) {
            pageNo = 1;
        }
        pager = new Pager(pageSize, pageNo);
        return pager;
    }

    /**
     * 创建分页对象
     *
     * @param request
     * @return
     */
    public Pager createPager(HttpServletRequest request) {
        Pager pager = null;
        String pageSize = request.getParameter("rows");
        String pageNo = request.getParameter("page");
        if (StringUtils.isBlank(pageSize)) {
            pageSize = String.valueOf(defaultPageSize);
        }
        if (StringUtils.isBlank(pageNo)) {
            pageNo = "1";
        }
        pager = new Pager(Integer.valueOf(pageSize), Integer.valueOf(pageNo));
        return pager;
    }

    /**
     * 获取result Map对象
     *
     * @return
     */
    protected Map<String, Object> getResultMap() {
        return new HashMap<String, Object>();
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
     * 生成表格数据格式
     *
     * @param pager
     * @return
     */
    public Map<String, Object> getGridData(Pager pager) {
        Map<String, Object> result = getResultMap();
        result.put("total", pager.getTotalRecord());
        result.put("rows", pager.getResult());
        return result;
    }

    /**
     * 获取客户端IP地址
     *
     * @param request
     * @return
     * @author 吴尚云 2014-2-25 下午5:44:12
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
    public static String getdDomain(HttpServletRequest request) {
        String path = request.getContextPath();
        String domain = request.getScheme() + "://";
        domain += request.getServerName();
        domain += request.getServerPort() == 80 ? "" : (":" + request.getServerPort());
        domain += path;
        return domain;
    }

    /**
     * 打印qequest中的请求参数
     *
     * @param request
     * @param log
     * @Author lx 2014-9-28 下午5:35:43
     */
    public void debugParameter(HttpServletRequest request, Log log) {
        if (log.isDebugEnabled()) {
            Map<String, String[]> map = request.getParameterMap();
            Set<Entry<String, String[]>> keSet = map.entrySet();
            String uri = request.getRequestURI();
            log.debug("URI=" + uri);
            for (Iterator<Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext(); ) {
                @SuppressWarnings("rawtypes")
                Map.Entry me = (Map.Entry) itr.next();
                Object ok = me.getKey();
                Object ov = me.getValue();
                String[] value = new String[1];
                if (ov instanceof String[]) {
                    value = (String[]) ov;
                } else {
                    value[0] = ov.toString();
                }
                for (int k = 0; k < value.length; k++) {
                    log.debug(uri + ":" + ok + "=" + value[k]);
                }
            }
        }
    }
}
