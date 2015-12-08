package com.ckx.api.core.picture;

import com.ckx.api.persist.entity.Picture;
import com.ckx.api.persist.entity.PictureMap;

import java.util.List;

/**
 * Created by 95 on 2015/12/5.
 */
public interface PictureService {

     List<PictureMap> albumList(int page, int size);

     List<Picture> picList(int mapId);

}
