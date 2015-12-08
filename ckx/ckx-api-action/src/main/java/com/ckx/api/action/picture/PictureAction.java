package com.ckx.api.action.picture;

import com.ckx.api.action.base.BaseAction;
import com.ckx.api.core.location.LocationService;
import com.ckx.api.core.picture.PictureService;
import com.ckx.api.persist.entity.PictureMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by 95 on 2015/12/5.
 */
@RequestMapping(value = "/pic")
@Controller
public class PictureAction extends BaseAction {

    private @Autowired
    PictureService pictureService;


    /**
     * 获取相册List
     */
    @ResponseBody
    @RequestMapping(value = "/album/{page}/{size}", method = RequestMethod.GET)
    public Object albumList(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> result = getResultMap();
        try {
            List<PictureMap> list = pictureService.albumList(page, size);
            result.put(E, 0);
            result.put(M, "success");
            result.put(DATA, list);
        } catch (Exception e) {
            result.put(E, 0);
            result.put(M, "系统异常，上报失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

}
