package com.${project}.web.core.${module};

import com.${project}.lang.Pager;
import com.${project}.web.persist.entity.${name};

public interface ${name}Service {

	void paginate(Pager pager);
	
	boolean add(${name} ${nameLow});
	
	boolean updateByPk(${name} ${nameLow});
	
	int deleteById(Integer id);
	
	int batchDelete(String ids);

}
