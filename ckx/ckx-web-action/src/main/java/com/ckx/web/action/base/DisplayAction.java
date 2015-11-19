package com.ckx.web.action.base;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面基础数据翻译
 */
@Controller
public class DisplayAction extends BaseAction {

    @RequestMapping(value = "/display.js", method = RequestMethod.GET)
    public String index(String type, HttpServletResponse response, HttpServletRequest request, ModelMap model) {
        response.setContentType("application/javascript; charset=UTF-8");
        if (!StringUtils.isBlank(type)) {
            List<String> arr = Arrays.asList(type.split(","));
            model.put("types", arr);
        }
        return "common/display";
    }
}
