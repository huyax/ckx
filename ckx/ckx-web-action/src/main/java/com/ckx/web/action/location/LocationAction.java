package com.ckx.web.action.location;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.action.base.BaseAction;
import com.ckx.web.core.location.LocationService;
import com.ckx.web.persist.entity.Location;

@RequestMapping(value = "/location")
@Controller
public class LocationAction extends BaseAction {

    private
    @Autowired
    LocationService locationService;

    /**
     * 菜单入口
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return AD_HTML + "location/index";
    }

    /**
     * 列表
     *
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Object list(HttpServletRequest request, ModelMap model) {
        Pager pager = createPager(request);
        pager.addParam("id", getRequestParams(String.class, request, "id"));
        locationService.paginate(pager);
        return getGridData(pager);
    }

    /**
     * 新增
     *
     * @param vo
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Object post(Location vo, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (locationService.add(vo)) {
                result.put(RESULT, true);
                result.put(MESSAGE, "新增成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "新增失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 修改
     *
     * @param config
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Object put(Location vo, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (vo.getId() == null) {
                result.put(RESULT, false);
                result.put(MESSAGE, "提交数据不完整！");
            } else if (locationService.updateByPk(vo)) {
                result.put(RESULT, true);
                result.put(MESSAGE, "修改成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "修改失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param id
     * @param ids
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (id != null && locationService.deleteById(id) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "删除成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "删除失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 批量操作（删除）
     *
     * @param ids
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    public Object batchDel(String ids, HttpServletRequest request, ModelMap model) {
        Map<String, Object> result = getResultMap();
        try {
            if (!StringUtils.isBlank(ids) && locationService.batchDelete(ids) > 0) {
                result.put(RESULT, true);
                result.put(MESSAGE, "删除成功！");
            } else {
                result.put(RESULT, false);
                result.put(MESSAGE, "删除失败！");
            }
        } catch (Exception e) {
            result.put(RESULT, false);
            result.put(MESSAGE, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

}
