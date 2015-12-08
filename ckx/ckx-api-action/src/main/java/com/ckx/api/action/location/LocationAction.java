package com.ckx.api.action.location;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckx.api.action.base.BaseAction;
import com.ckx.api.core.location.LocationService;
/**
 * Created by 寒远黛 on 2015/12/5.
 */
@RequestMapping(value = "/location")
@Controller
public class LocationAction extends BaseAction {

    private @Autowired LocationService locationService;


    /**
     * 坐标上报
     */
    @ResponseBody
    @RequestMapping(value = "/{uid}/{longitude}/{latitude}", method = RequestMethod.GET)
    public Object post(@PathVariable Integer uid, @PathVariable String longitude, @PathVariable String latitude) {
        Map<String, Object> result;
        try {
            if (locationService.post(uid, longitude, latitude)) {
                result = getResult(true,null);
            } else {
                result = getResult(false,null);
            }
        } catch (Exception e) {
            result = getResult(false,null);
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

}
