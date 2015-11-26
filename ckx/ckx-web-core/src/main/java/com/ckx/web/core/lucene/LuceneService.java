package com.ckx.web.core.lucene;

import java.util.List;

import com.ckx.web.core.bean.SearchBean;

public interface LuceneService {

	public boolean createIndex();

	public boolean removeIndex();

	public boolean updateIndex();

	public List<SearchBean> search(String q) throws Exception;

}
