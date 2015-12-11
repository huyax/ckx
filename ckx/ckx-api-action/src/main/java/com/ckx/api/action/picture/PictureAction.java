package com.ckx.api.action.picture;

import com.ckx.api.action.base.BaseAction;
import com.ckx.api.core.picture.PictureService;
import com.ckx.api.persist.entity.Picture;
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
 * Created by 寒远黛 on 2015/12/5.
 */
@Controller
public class PictureAction extends BaseAction {

    private @Autowired
    PictureService pictureService;


    /**
     * 获取相册
     */
    @ResponseBody
    @RequestMapping(value = "/album/{page}/{size}", method = RequestMethod.GET)
    public Object albumList(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> result;
        try {
            List<PictureMap> list = pictureService.albumList(page, size);
            result = getResult(true,list);
        } catch (Exception e) {
            result = getResult(false,null);
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 获取相册图片
     */
    @ResponseBody
    @RequestMapping(value = "/pic/{mapId}", method = RequestMethod.GET)
    public Object picList(@PathVariable Integer mapId) {
        Map<String, Object> result;
        try {
            List<Picture> list = pictureService.picList(mapId);
            result = getResult(true,list);
        } catch (Exception e) {
            result = getResult(false,null);
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

}
