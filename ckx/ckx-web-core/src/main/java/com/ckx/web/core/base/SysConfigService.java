package com.ckx.web.core.base;

import java.util.Map;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.SysConfig;

public interface SysConfigService {

	SysConfig getConfigByParam(Map<String, Object> param);

	void updateConfig(Map<String, Object> param);

	void paginate(Pager pager);

	boolean updateByPk(SysConfig config);

	boolean addConfig(SysConfig config);

	int batchDelete(String ids);

	int delete(Integer id);

}
