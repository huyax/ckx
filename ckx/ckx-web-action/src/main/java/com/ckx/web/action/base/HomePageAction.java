package com.ckx.web.action.base;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageAction extends BaseAction {

    @RequestMapping(value = "/menus/home.html", method = RequestMethod.GET)
    public String index(ModelMap model) {

        return ADMIN + "home";
    }

}
