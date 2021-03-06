package com.ckx.web.action.catelogs;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.action.base.BaseAction;
import com.ckx.web.core.catelogs.CatelogsService;
import com.ckx.web.persist.entity.Catelogs;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping(value = "/catelogs")
@Controller
public class CatelogsAction extends BaseAction {

    private
    @Autowired
    CatelogsService catelogsService;

    /**
     * 菜单入口
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return ADMIN + "catelogs/index";
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
        catelogsService.paginate(pager);
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
    public Object post(Catelogs vo, HttpServletRequest request, ModelMap model) {
        Map
                <String, Object> result = getResultMap();
        try {
            if (catelogsService.add(vo)) {
                result.put(R, true);
                result.put(M, "新增成功！");
            } else {
                result.put(R, false);
                result.put(M, "新增失败！");
            }
        } catch (Exception e) {
            result.put(R, false);
            result.put(M, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 修改
     *
     * @param vo
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Object put(Catelogs vo, HttpServletRequest request, ModelMap model) {
        Map
                <String, Object> result = getResultMap();
        try {
            if (vo.getId() == null) {
                result.put(R, false);
                result.put(M, "提交数据不完整！");
            } else if (catelogsService.updateByPk(vo)) {
                result.put(R, true);
                result.put(M, "修改成功！");
            } else {
                result.put(R, false);
                result.put(M, "修改失败！");
            }
        } catch (Exception e) {
            result.put(R, false);
            result.put(M, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param id
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id, HttpServletRequest request, ModelMap model) {
        Map
                <String, Object> result = getResultMap();
        try {
            if (id != null && catelogsService.deleteById(id) > 0) {
                result.put(R, true);
                result.put(M, "删除成功！");
            } else {
                result.put(R, false);
                result.put(M, "删除失败！");
            }
        } catch (Exception e) {
            result.put(R, false);
            result.put(M, "系统异常，操作失败！");
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
        Map
                <String, Object> result = getResultMap();
        try {
            if (!StringUtils.isBlank(ids) && catelogsService.batchDelete(ids) > 0) {
                result.put(R, true);
                result.put(M, "删除成功！");
            } else {
                result.put(R, false);
                result.put(M, "删除失败！");
            }
        } catch (Exception e) {
            result.put(R, false);
            result.put(M, "系统异常，操作失败！");
            getLog(this).error(e.getMessage(), e);
        }
        return result;
    }

}
