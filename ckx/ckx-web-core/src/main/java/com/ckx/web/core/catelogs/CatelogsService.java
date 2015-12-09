package com.ckx.web.core.catelogs;


import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.Catelogs;

public interface CatelogsService {

void paginate(Pager pager);

boolean add(Catelogs catelogs);

boolean updateByPk(Catelogs catelogs);

int deleteById(Integer id);

int batchDelete(String ids);

}
