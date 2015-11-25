package com.ckx.web.core.location;

import com.ckx.lang.mybatis.Pager;
import com.ckx.web.persist.entity.Location;

public interface LocationService {

	void paginate(Pager pager);
	
	boolean add(Location location);
	
	boolean updateByPk(Location location);
	
	int deleteById(Integer id);
	
	int batchDelete(String ids);

	void scanQueue();

}
