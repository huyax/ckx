package com.ckx.web.freemarker;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.ckx.web.action.base.BaseAction;
import com.ckx.web.core.constants.SysConstants;

public class MVCFreeMarckerView extends FreeMarkerView {

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        super.exposeHelpers(model, request);
        // 静态资源版本（缓存控制）
        model.put("v", "?v=" + resCacheVersion());
        model.put("base", request.getContextPath());
        model.put("url", request.getRequestURI().replaceAll(request.getContextPath(), ""));
        model.put("debug", SysConstants.DEBUG);
        model.put("isCompress", SysConstants.HTML_IS_COMPRESS);
        // 用户信息
        model.put("user", BaseAction.getUser());
        // JS，CSS文件自动引入
        model.put("jsPath", getResJsPath(this.getBeanName()));
        model.put("cssPath", getResCssPath(this.getBeanName()));
    }

    /**
     * 获取web应用的根路径
     *
     * @return
     */
    private String getRootPath() {
        String rootPath = null, url = this.getClass().getResource("").getPath().replaceAll("%20", " ");
        int index = url.indexOf("WEB-INF");
        if (index == -1) {// 正对Maven本地调试
            index = url.indexOf("target");
            if (index != -1) {
                rootPath = url.substring(0, index) + "/src/main/webapp/";
            }
        } else {
            rootPath = url.substring(0, index);
        }
        return rootPath;
    }

    /**
     * 获取静态资源文件缓存版本
     *
     * @return
     */
    private static String resCacheVersion() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 根据view名称获取对应JS文件的路径
     *
     * @param beanName view名称
     * @return
     */
    private String getResJsPath(String beanName) {
        String resPath = getRootPath() + "assets/";
        int index = beanName.indexOf("/");
        String start = index != -1 ? beanName.substring(0, index) : "";
        String end = index != -1 ? beanName.substring(beanName.indexOf("/")) : "/" + beanName;
        String jsPath = start + "/js" + end + ".js";
        if (new File(resPath + jsPath).exists()) {
            return jsPath;
        }
        return null;
    }


    /**
     * 根据view名称获取对应CSS文件的路径
     *
     * @param beanName view名称
     * @return
     */
    private String getResCssPath(String beanName) {
        String resPath = getRootPath() + "assets/";
        int index = beanName.indexOf("/");
        String start = index != -1 ? beanName.substring(0, index) : "";
        String end = index != -1 ? beanName.substring(beanName.indexOf("/")) : "/" + beanName;
        String cssPath = start + "/css" + end + ".css";
        if (new File(resPath + cssPath.replace('/', File.separatorChar)).exists()) {
            return cssPath;
        }
        return null;
    }
}
