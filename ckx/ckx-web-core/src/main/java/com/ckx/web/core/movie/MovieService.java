package com.ckx.web.core.movie;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.Movie;

public interface MovieService {

	void paginate(Pager pager);
	
	boolean add(Movie movie);
	
	boolean updateByPk(Movie movie);
	
	int deleteById(Integer id);
	
	int batchDelete(String ids);

}
