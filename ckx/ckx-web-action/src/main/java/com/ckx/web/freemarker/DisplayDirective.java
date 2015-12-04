package com.ckx.web.freemarker;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ckx.web.core.base.DisplayService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * freemarker 自定义标签 用于翻译静态数据
 */
public class DisplayDirective implements TemplateDirectiveModel {

    private static Log log = LogFactory.getLog(DisplayDirective.class);

    private
    @Autowired
    DisplayService displayMgr;

    @SuppressWarnings("rawtypes")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        Object typeObject = params.get("type");
        Object keyObject = params.get("key");
        String type = typeObject == null ? null : typeObject.toString();
        String key = keyObject == null ? null : keyObject.toString();
        Map<Object, String> display = null;
        try {
            if (type == null) {
                env.getOut().write("");
            } else if ("user".equals(type.toLowerCase())) {// 翻译用户
                display = displayMgr.displayUser();
            } else if ("role".equals(type.toLowerCase())) {// 翻译角色
                display = displayMgr.displayRole();
            } else if ("post".equals(type.toLowerCase())) {// 翻译岗位
                display = displayMgr.displayPost();
            }

            if (display != null) {
                if (key == null) {
                    env.getOut().write(JSON.toJSONString(display));
                } else {
                    String name = display.get(Integer.valueOf(key));
                    env.getOut().write(name == null ? "无" : name);
                }
            } else {
                env.getOut().write("{}");
            }
        } catch (Exception e) {
            env.getOut().write("{}");
            log.error("Freemarker翻译异常！");
            e.printStackTrace();
        }
    }

}
